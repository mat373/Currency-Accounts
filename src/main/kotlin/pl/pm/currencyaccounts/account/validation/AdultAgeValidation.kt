package pl.pm.currencyaccounts.account.validation

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.core.util.isAdult
import java.time.LocalDate

@Component
class AdultAgeValidation : AccountValidation {

    override fun invoke(account: AccountDTO): Boolean =
        LocalDate.of(
            getBirthYear(account.personalId),
            getBirthMonth(account.personalId),
            getBirthDay(account.personalId)
        )
            .let(LocalDate::isAdult)

    private fun getBirthYear(str: String) =
        str.substring(0, 2).toInt()

    private fun getBirthMonth(str: String) =
        str.substring(2, 4).toInt()

    private fun getBirthDay(str: String) =
        str.substring(4, 6).toInt()

}