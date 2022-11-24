package pl.pm.currencyaccounts.core.exception

import pl.pm.currencyaccounts.core.enum.Currency

class SubAccountNotFoundException(
    personalId: String,
    currency: Currency
) : Exception("SubAccount $currency does not exist for $personalId")