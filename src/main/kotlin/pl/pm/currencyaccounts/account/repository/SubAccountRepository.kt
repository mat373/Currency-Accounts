package pl.pm.currencyaccounts.account.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import pl.pm.currencyaccounts.account.model.SubAccount
import pl.pm.currencyaccounts.core.enum.Currency
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface SubAccountRepository : R2dbcRepository<SubAccount, Long> {

    fun findAllByAccountId(accountId: Long): Flux<SubAccount>
    fun findByAccountId(accountId: Long): Mono<SubAccount>
}