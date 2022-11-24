package pl.pm.currencyaccounts.account.service

import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.account.model.SubAccount

@Service
class AccountUpdateService(
    private val daoService: AccountDaoService
) {

    fun updateAccountBalance(subAccounts: List<SubAccount>) =
        daoService.updateSubAccount(subAccounts)
}