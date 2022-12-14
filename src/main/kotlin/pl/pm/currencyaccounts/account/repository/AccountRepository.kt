package pl.pm.currencyaccounts.account.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import pl.pm.currencyaccounts.account.model.Account
import reactor.core.publisher.Mono

@Repository
interface AccountRepository : R2dbcRepository<Account, Long> {

    fun findByPersonalId(personalId: String): Mono<Account>
}