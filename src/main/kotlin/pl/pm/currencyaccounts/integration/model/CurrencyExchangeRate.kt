package pl.pm.currencyaccounts.integration.model

import pl.pm.currencyaccounts.core.enum.Currency
internal data class CurrencyExchangeRate(
    val code: Currency,
    val rates: List<CurrencyRate>
)