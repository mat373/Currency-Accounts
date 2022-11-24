package pl.pm.currencyaccounts.core.util

import pl.pm.currencyaccounts.core.enum.Currency
import java.time.LocalDate

private const val TABLE = "C"
private const val EXCHANGE_RATES_URL = "/exchangerates/rates/$TABLE"
fun createUrl(currency: Currency, localDate: LocalDate) =
    "$EXCHANGE_RATES_URL/$currency/$localDate"
