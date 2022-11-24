package pl.pm.currencyaccounts.core.exception

import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.core.util.PersonalId

class SubAccountNotFoundException(
    personalId: PersonalId,
    currency: Currency
) : Exception("SubAccount $currency does not exist for $personalId")