package pl.pm.currencyaccounts.util

import pl.pm.currencyaccounts.account.model.Account
import pl.pm.currencyaccounts.account.model.SubAccount
import pl.pm.currencyaccounts.account.model.dto.AccountDTO

class AccountUtil {

    def static getAccount(String personalId) {
        new Account(null, "firstName", "lastName", personalId)
    }

    def static getSubAccountPLN() {
        new SubAccount(null, 1L, pl.pm.currencyaccounts.core.enum.Currency.PLN, BigDecimal.valueOf(1000))
    }

    def static getSubAccountUSD() {
        new SubAccount(null, 1L, pl.pm.currencyaccounts.core.enum.Currency.USD, BigDecimal.ZERO)
    }

    def static getAccountDTO(String personalId) {
        new AccountDTO(
                "firstName",
                "lastName",
                BigDecimal.TEN,
                personalId
        )
    }
}
