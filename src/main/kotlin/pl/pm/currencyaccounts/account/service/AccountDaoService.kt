package pl.pm.currencyaccounts.account.service

import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.account.model.Account
import pl.pm.currencyaccounts.account.model.dto.AccountView
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.account.model.SubAccount
import pl.pm.currencyaccounts.account.repository.AccountRepository
import pl.pm.currencyaccounts.account.repository.SubAccountRepository
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.core.exception.DuplicateAccountException
import pl.pm.currencyaccounts.core.util.getDefault
import java.math.BigDecimal

@Service
class AccountDaoService(
    private val accountRepository: AccountRepository,
    private val subAccountRepository: SubAccountRepository
) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    fun save(account: AccountDTO) = accountRepository
            .save(Account.of(account))
            .onErrorMap(DataIntegrityViolationException::class.java) { DuplicateAccountException(account.personalId) }
            .doOnSuccess { log.info("Account saved: ${it.personalId}") }
            .doOnError { log.error("Account not saved: ${it.message}", it) }
            .flatMap { createAccount(it, account.balance) }

    private fun createAccount(account: Account, balance: BigDecimal) = subAccountRepository
        .saveAll(mapSubAccounts(account, balance))
        .collectList()
        .map { AccountView(account, it) }

    private fun mapSubAccounts(account: Account, balance: BigDecimal) = Currency
        .values()
        .filter { Currency.exists(it.name) }
        .map { SubAccount(accountId = account.id, currency = it, balance = BigDecimal.ZERO) }
        .plus(SubAccount(accountId = account.id, currency = getDefault(), balance = balance))
}