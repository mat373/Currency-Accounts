package pl.pm.currencyaccounts.account.validation

import pl.pm.currencyaccounts.account.model.dto.AccountDTO

interface AccountValidation : (AccountDTO) -> Boolean{
}