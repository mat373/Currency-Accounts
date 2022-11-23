package pl.pm.currencyaccounts.integration.service

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.cache.ExchangeRatesCache
import pl.pm.currencyaccounts.integration.model.CurrencyRate

interface ExchangeRatesService {
    fun getCurrencyExchangeRate(currency: Currency): Pair<CurrencyRate, Currency>?
}

@Component
internal class ExchangeRatesClientService(private val cache: ExchangeRatesCache) : ExchangeRatesService {

    override fun getCurrencyExchangeRate(currency: Currency) =
        cache.findActual(currency)
}
