package pl.pm.currencyaccounts.account.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import pl.pm.currencyaccounts.account.model.SubAccount

@Repository
interface SubAccountRepository : R2dbcRepository<SubAccount, Long> {
}