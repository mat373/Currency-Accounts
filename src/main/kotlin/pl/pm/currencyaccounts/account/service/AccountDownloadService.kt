package pl.pm.currencyaccounts.account.service

import org.springframework.stereotype.Service

@Service
class AccountDownloadService(private val daoService: AccountDaoService) {

    fun getAccountByPersonalId(personalId: String) =
        daoService.getAccountByPersonalId(personalId)
}