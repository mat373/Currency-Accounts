package pl.pm.currencyaccounts.account.validation

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.core.exception.AccountRegistrationException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

@Component
class AccountValidator(private val validations: List<AccountValidation>) {
    fun validate(account: AccountDTO) =
        validations
            .toFlux()
            .all { it(account) }
            .flatMap { mapIfTrue(it, account) }
            .switchIfEmpty { Mono.error { AccountRegistrationException(account) } }

    private fun mapIfTrue(it: Boolean?, account: AccountDTO) =
        if (it == true) account.toMono() else Mono.empty()
}
