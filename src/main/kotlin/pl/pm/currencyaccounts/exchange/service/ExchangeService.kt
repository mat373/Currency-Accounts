package pl.pm.currencyaccounts.exchange.service

import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.account.service.AccountServiceFacade
import pl.pm.currencyaccounts.integration.service.ExchangeRatesService

@Service
class ExchangeService(
    private val accountService: AccountServiceFacade,
    private val exchangeRatesService: ExchangeRatesService
) {

}