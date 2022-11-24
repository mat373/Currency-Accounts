package pl.pm.currencyaccounts.account.web.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.pm.currencyaccounts.account.model.dto.AccountDTO
import pl.pm.currencyaccounts.account.service.AccountServiceFacade

@RestController
@RequestMapping("/api/account")
class AccountResource(
    private val acconutService: AccountServiceFacade
) {

    @PostMapping("/register")
    fun registerAccount(@RequestBody account: AccountDTO) =
        acconutService.registerAccount(account)

    @GetMapping("{personalId}")
    fun getAccount(@PathVariable personalId: String) =
        acconutService.getAccountByPersonalId(personalId)
}