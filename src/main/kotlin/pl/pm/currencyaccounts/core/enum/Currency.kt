package pl.pm.currencyaccounts.core.enum

import pl.pm.currencyaccounts.core.util.getDefault
import pl.pm.currencyaccounts.core.util.ignoreDefault

enum class Currency {
    PLN,
    USD;

    companion object {
        fun getWithoutDefault() =
            Currency
                .values()
                .filter { it != getDefault() }
    }
}