package pl.pm.currencyaccounts.exchange.web.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.pm.currencyaccounts.exchange.model.Exchange
import pl.pm.currencyaccounts.exchange.service.ExchangeService

@RestController
@RequestMapping("/api/exchange")
class ExchangeResource(private val exchangeService: ExchangeService) {

    @PostMapping
    fun exchange(@RequestBody exchange: Exchange) =
       TODO()
}