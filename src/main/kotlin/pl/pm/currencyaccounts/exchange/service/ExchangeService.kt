package pl.pm.currencyaccounts.exchange.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.pm.currencyaccounts.account.model.dto.AccountView
import pl.pm.currencyaccounts.account.service.AccountServiceFacade
import pl.pm.currencyaccounts.core.exception.TooLowBalanceException
import pl.pm.currencyaccounts.exchange.model.Exchange
import pl.pm.currencyaccounts.integration.service.ExchangeRatesService
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class ExchangeService(
    private val accountService: AccountServiceFacade,
    private val exchangeRatesService: ExchangeRatesService
) {

    @Transactional
    fun exchange(exchange: Exchange) =
        getExchangeValue(exchange)
            .map { subAccounts -> subAccounts.toList() }
            .flatMapMany(accountService::updateBalance)
            .collectList()

    private fun getExchangeValue(exchange: Exchange) =
        accountService.getAccountByPersonalId(exchange.personalId)
            .flatMap { account -> updateBalance(exchange, account) }

    private fun updateBalance(exchange: Exchange, account: AccountView) =
        exchangeRatesService.getCurrencyExchangeRate(exchange.currencyFrom, exchange.currencyTo)
            .map { rate -> exchange.amountFrom * rate }
            .flatMap { amountTo -> updateBalance(account, exchange, amountTo) }

    private fun updateBalance(account: AccountView, exchange: Exchange, amountTo: BigDecimal) =
        getSubAccounts(account, exchange)
            .let { (from, to) ->
                val fromBalance = from.balance - exchange.amountFrom
                when (fromBalance < BigDecimal.ZERO) {
                    true -> Mono.error(TooLowBalanceException(exchange))
                    else -> Mono.just(
                        from.copy(balance = fromBalance) to
                                to.copy(balance = to.balance + amountTo)
                    )
                }
            }

    private fun getSubAccounts(account: AccountView, exchange: Exchange) =
        account.getSubAccount(exchange.currencyFrom) to account.getSubAccount(exchange.currencyTo)
}