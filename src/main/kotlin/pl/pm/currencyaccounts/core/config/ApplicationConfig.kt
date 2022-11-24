package pl.pm.currencyaccounts.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.scheduling.annotation.EnableScheduling
@Configuration
@EnableScheduling
@EnableR2dbcRepositories
class ApplicationConfig {}