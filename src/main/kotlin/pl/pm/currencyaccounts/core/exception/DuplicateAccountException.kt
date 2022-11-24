package pl.pm.currencyaccounts.core.exception

import pl.pm.currencyaccounts.core.util.PersonalId
class DuplicateAccountException(
    personalId: PersonalId
) : Exception("User $personalId already exists")