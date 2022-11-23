package pl.pm.currencyaccounts.account.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import pl.pm.currencyaccounts.account.model.dto.AccountDTO

@Table(name = "accounts")
data class Account (
    @Id
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val personalId: String
) {

    companion object {
        fun of(account: AccountDTO) =
            Account(
                firstName = account.firstName,
                lastName = account.lastName,
                personalId = account.personalId
            )
    }
}