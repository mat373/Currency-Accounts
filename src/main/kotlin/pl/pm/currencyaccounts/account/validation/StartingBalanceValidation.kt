package pl.pm.currencyaccounts.account.validation

import org.springframework.stereotype.Component
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import java.math.BigDecimal

@Component
class StartingBalanceValidation : AccountValidation {
    override fun invoke(account: AccountDTO): Boolean =
        account.balance >= BigDecimal.ZERO
}