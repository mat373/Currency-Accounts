package pl.pm.currencyaccounts.integration.service

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.integration.api.ExchangeRatesClient
import pl.pm.currencyaccounts.integration.cache.ExchangeRatesCache
import reactor.kotlin.core.publisher.toFlux

@Component
@Profile("!test")
internal class ExchangeRatesUpdateService(
    private val client: ExchangeRatesClient,
    private val cache: ExchangeRatesCache
) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    init {
        refresh()
    }

    @Scheduled(cron = "* 5 * * * * ")
    private fun schedule() {
        refresh()
    }

    private fun refresh() =
        refreshCache()
            .subscribe { log.info("Currency update: $it") }

    private fun refreshCache() =
        Currency.values()
            .toFlux()
            .filter { Currency.exists(it.name) }
            .flatMap(client::getExchangeRates)
            .map{cache.put(it)}
}