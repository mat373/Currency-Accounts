package pl.pm.currencyaccounts.account.service

import org.slf4j.LoggerFactory
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
import reactor.kotlin.core.publisher.toMono
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
        .doOnSuccess { log.info("Account saved: ${it.personalId}") }
        .doOnError { throw DuplicateAccountException(account.personalId) }
        .flatMap { getAccount(it, account.balance) }

    fun updateSubAccount(subAccounts: List<SubAccount>) =
        subAccountRepository.saveAll(subAccounts)

    fun getAccountByPersonalId(personalId: String) = accountRepository
        .findByPersonalId(personalId)
        .flatMap(::getSubAccounts)

    private fun getAccount(account: Account, balance: BigDecimal) = subAccountRepository
        .saveAll(getSubAccountWithDefault(account, balance))
        .collectList()
        .map { AccountView(account, it) }

    private fun getSubAccounts(account: Account) =
        account
            .id
            .toMono()
            .flatMapMany(subAccountRepository::findAllByAccountId)
            .collectList()
            .map { AccountView(account, it) }

    private fun getSubAccountWithDefault(account: Account, balance: BigDecimal) =
        mapSubAccounts(account)
            .plus(SubAccount(accountId = account.id, currency = getDefault(), balance = balance))

    private fun mapSubAccounts(account: Account) = Currency
        .values()
        .filter { it != Currency.PLN }
        .map { SubAccount(accountId = account.id, currency = Currency.USD, balance = BigDecimal.ZERO) }
}