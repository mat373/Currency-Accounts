package pl.pm.currencyaccounts.account.service

import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.exchange.model.Exchange
import java.math.BigDecimal

@Service
class AccountUpdateService(
    private val daoService: AccountDaoService
) {

    fun updateAccountBalance(exchange: Exchange, balance: BigDecimal) =
        daoService.updateSubAccount(exchange, balance)
}