package pl.pm.currencyaccounts.core.util

import pl.pm.currencyaccounts.core.enum.Currency
import java.util.*

fun ignoreDefault(it: Currency) =
    it != Currency.valueOf(java.util.Currency.getInstance(Locale.getDefault()).currencyCode)
fun getDefault() =
    Currency.valueOf(java.util.Currency.getInstance(Locale.getDefault()).currencyCode)