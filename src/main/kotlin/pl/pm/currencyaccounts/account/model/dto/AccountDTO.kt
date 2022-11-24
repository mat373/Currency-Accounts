package pl.pm.currencyaccounts.account.model.dto

import pl.pm.currencyaccounts.core.util.PersonalId
import java.math.BigDecimal

data class AccountDTO(
    val firstName: String,
    val lastName: String,
    val balance: BigDecimal,
    val personalId: PersonalId
)