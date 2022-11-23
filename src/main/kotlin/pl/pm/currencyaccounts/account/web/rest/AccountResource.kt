package pl.pm.currencyaccounts.account.web.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.account.service.AccountRegistrationService

@RestController
@RequestMapping("/api/account")
class AccountResource(
    private val registerService: AccountRegistrationService
) {

    @PostMapping("/register")
    fun registerAccount(@RequestBody account: AccountDTO) =
        registerService.registerAccount(account)
}