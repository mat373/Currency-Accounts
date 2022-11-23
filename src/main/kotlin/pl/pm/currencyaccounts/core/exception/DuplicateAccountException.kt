package pl.pm.currencyaccounts.core.exception

class DuplicateAccountException(
    personalId: String
) : Exception("User $personalId already exists")