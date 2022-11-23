package pl.pm.currencyaccounts.account.model.dto

import pl.pm.currencyaccounts.account.model.Account
import pl.pm.currencyaccounts.account.model.SubAccount

data class AccountView(
    val account: Account,
    val subAccounts: List<SubAccount>
)