package pl.pm.currencyaccounts.account.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import pl.pm.currencyaccounts.core.enum.Currency
import java.math.BigDecimal

@Table(name = "subaccounts")
data class SubAccount(
    @Id
    val id: Long? = null,
    val accountId: Long? = null,
    val currency: Currency,
    val balance: BigDecimal
)