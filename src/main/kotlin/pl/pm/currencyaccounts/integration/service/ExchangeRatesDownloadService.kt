package pl.pm.currencyaccounts.integration.service

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.cache.ExchangeRatesCache
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.zip
import reactor.kotlin.core.publisher.toMono
import java.math.BigDecimal
import java.math.RoundingMode

import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
interface ExchangeRatesService {

    fun getCurrencyExchangeRate(currencyFrom: Currency, currencyTo: Currency): Mono<BigDecimal>
}
@Component
internal class ExchangeRatesClientService(private val cache: ExchangeRatesCache) : ExchangeRatesService {

    override fun getCurrencyExchangeRate(currencyFrom: Currency, currencyTo: Currency): Mono<BigDecimal> =
        zip(
            getRate(currencyFrom),
            getRate(currencyTo)
        )
            .map { (rateFrom, rateTo) -> rateFrom / rateTo }

    private fun getRate(currencyTo: Currency): Mono<BigDecimal> =
        when (currencyTo) {
            Currency.PLN -> BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP).toMono()
            else -> cache.getActualRate(currencyTo)
                .map { (currencyRate, _) -> currencyRate.bid.setScale(2, RoundingMode.HALF_UP) }
        }
}
