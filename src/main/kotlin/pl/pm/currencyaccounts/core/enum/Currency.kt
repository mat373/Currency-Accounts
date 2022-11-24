package pl.pm.currencyaccounts.core.enum

import pl.pm.currencyaccounts.core.util.getDefault

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