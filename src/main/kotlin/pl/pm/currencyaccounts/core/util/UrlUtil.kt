package pl.pm.currencyaccounts.core.util

import pl.pm.currencyaccounts.core.enum.Currency

private const val TABLE = "C"
private const val EXCHANGE_RATES_URL = "/exchangerates/rates/$TABLE"
private const val LAST_ACTUAL_NOTICE = "/last/1"

fun createUrl(currency: Currency) =
    EXCHANGE_RATES_URL
        .plus("/${currency.name}")
        .plus(LAST_ACTUAL_NOTICE)
