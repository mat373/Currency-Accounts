package pl.pm.currencyaccounts.account.service

import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.account.model.SubAccount
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.exchange.model.Exchange
import java.math.BigDecimal

@Service
class AccountUpdateService(
    private val daoService: AccountDaoService
) {
    fun updateAccountBalance(subAccounts: List<SubAccount>) =
        daoService.updateSubAccount(subAccounts)
}