package pl.pm.currencyaccounts.account.model.dto

import pl.pm.currencyaccounts.account.model.Account
import pl.pm.currencyaccounts.account.model.SubAccount
import pl.pm.currencyaccounts.core.enum.Currency

data class AccountView(
    val account: Account,
    val subAccounts: List<SubAccount>
) {

    fun getSubAccount(currency: Currency) =
        subAccounts
            .first { subAccount -> subAccount.currency == currency }
}