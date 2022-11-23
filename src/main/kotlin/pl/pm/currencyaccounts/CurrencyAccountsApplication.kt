package pl.pm.currencyaccounts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CurrencyAccountsApplication

fun main(args: Array<String>) {
    runApplication<CurrencyAccountsApplication>(*args)
}
