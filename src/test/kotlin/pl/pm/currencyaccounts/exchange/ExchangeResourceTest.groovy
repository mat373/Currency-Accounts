package pl.pm.currencyaccounts.exchange

import pl.pm.currencyaccounts.AbstractResourceTest
import pl.pm.currencyaccounts.account.model.dto.AccountView
import pl.pm.currencyaccounts.exchange.model.Exchange
import pl.pm.currencyaccounts.util.AccountUtil
import reactor.test.StepVerifier

class ExchangeResourceTest extends AbstractResourceTest {

    def 'should get user by personalId'() {
        given:
        def personalId = "88030246447"
        accountRepository.save(AccountUtil.getAccount(personalId)).subscribe()
        subAccountRepository.save(AccountUtil.getSubAccountPLN()).subscribe()
        subAccountRepository.save(AccountUtil.getSubAccountUSD()).subscribe()

        def receiveRequest = webTestClient.post()
                .uri("/api/exchange/" + personalId)
                .bodyValue(new Exchange (
                        personalId,
                        pl.pm.currencyaccounts.core.enum.Currency.PLN,
                        BigDecimal.TEN,
                        pl.pm.currencyaccounts.core.enum.Currency.USD, )
                )

        when:
        def receiveResponse = receiveRequest
                .exchange()
                .returnResult(AccountView.class)
                .getResponseBody()

        then:
        StepVerifier.create(receiveResponse)
                .expectNextMatches {
                    it.account.firstName == "firstName"
                    it.account.lastName == "lastName"
                }
                .verifyComplete()
    }
}
