package pl.pm.currencyaccounts.account.service

import org.springframework.stereotype.Service
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.account.validation.AccountValidator
import reactor.kotlin.core.publisher.toFlux
@Service
class AccountRegistrationService(
    private val validator: AccountValidator,
    private val daoService: AccountDaoService
) {
    fun registerAccount(account: AccountDTO) =
        validator
            .validate(account)
            .toFlux()
            .flatMap(daoService::save)
}