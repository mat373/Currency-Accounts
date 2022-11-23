package pl.pm.currencyaccounts.account.validation

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.account.model.dto.AccountDTO

@Component
class PersonalNumberValidation : AccountValidation {

    companion object {
        private const val PERSONAL_ID_MAX_LENGTH = 11;
    }

    override fun invoke(account: AccountDTO): Boolean =
        account.personalId.length <= PERSONAL_ID_MAX_LENGTH
}