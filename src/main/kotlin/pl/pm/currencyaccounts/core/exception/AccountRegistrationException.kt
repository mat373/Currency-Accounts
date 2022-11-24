package pl.pm.currencyaccounts.core.exception

import pl.pm.currencyaccounts.account.model.dto.AccountDTO

class AccountRegistrationException(account: AccountDTO) : Exception("Cannot create account ${account.personalId}")