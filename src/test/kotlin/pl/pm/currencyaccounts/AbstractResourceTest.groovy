package pl.pm.currencyaccounts

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import pl.pm.currencyaccounts.account.repository.AccountRepository
import pl.pm.currencyaccounts.account.repository.SubAccountRepository
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient(timeout = "PT60S")
class AbstractResourceTest extends Specification {

    @Autowired
    protected WebTestClient webTestClient

    @Autowired
    protected
    AccountRepository accountRepository

    @Autowired
    protected
    SubAccountRepository subAccountRepository
}
