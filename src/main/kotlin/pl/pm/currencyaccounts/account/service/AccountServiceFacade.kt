package pl.pm.currencyaccounts.account.service

import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.account.model.SubAccount
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.exchange.model.Exchange
import java.math.BigDecimal

@Service
class AccountServiceFacade(
    private val registrationService: AccountRegistrationService,
    private val downloadService: AccountDownloadService,
    private val updateService: AccountUpdateService
) {

    fun getAccountByPersonalId(personalId: String) =
        downloadService.getAccountByPersonalId(personalId)

    fun registerAccount(account: AccountDTO) =
        registrationService.registerAccount(account)

    fun updateBalance(subAccounts: List<SubAccount>) =
        updateService.updateAccountBalance(subAccounts)
}