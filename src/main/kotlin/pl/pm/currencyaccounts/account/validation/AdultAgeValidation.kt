package pl.pm.currencyaccounts.account.validation

import org.springframework.stereotype.Component
import pl.foltak.polishidnumbers.pesel.Pesel
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.core.util.isAdult
import java.time.LocalDate

@Component
class AdultAgeValidation : AccountValidation {
    override fun invoke(account: AccountDTO): Boolean =
        Pesel(account.personalId)
            .birthDate
            ?.let(LocalDate::isAdult)
            ?:false
}