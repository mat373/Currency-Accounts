package pl.pm.currencyaccounts.account.validation

import org.springframework.stereotype.Component
import pl.foltak.polishidnumbers.pesel.PeselValidator
import pl.pm.currencyaccounts.account.model.dto.AccountDTO

@Component
class PersonalNumberValidation : AccountValidation {

    override fun invoke(account: AccountDTO): Boolean =
        PeselValidator().isValid(account.personalId)
}