package pl.pm.currencyaccounts.core.enum

import pl.pm.currencyaccounts.core.util.ignoreDefault

enum class Currency {
    PLN,
    USD;

    companion object {
        fun exists(currency: String) =
            values()
                .filter { ignoreDefault(it) }
                .any { it.name == currency }
    }
}