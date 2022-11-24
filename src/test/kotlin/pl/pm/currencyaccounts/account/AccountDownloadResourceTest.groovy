package pl.pm.currencyaccounts.account

import pl.pm.currencyaccounts.AbstractResourceTest

import pl.pm.currencyaccounts.account.model.dto.AccountView
import pl.pm.currencyaccounts.util.AccountUtil
import reactor.test.StepVerifier

class AccountDownloadResourceTest extends AbstractResourceTest {

    def 'should correctly register account'() {
        given:
        def receiveRequest = webTestClient.post()
                .uri("/api/account/register")
                .bodyValue(AccountUtil.getAccountDTO("02300916999"))

        when:
        def receiveResponse = receiveRequest
                .exchange()
                .returnResult(AccountView.class)
                .getResponseBody()

        then:
        StepVerifier.create(receiveResponse)
                .expectSubscription()
                .expectNextMatches {
                    assert it.account.firstName == "firstName"
                    assert it.account.lastName == "lastName"
                    true
                }
                .thenCancel()
                .verify()
    }

    def 'should throw InvalidPeselException'() {
        given:
        def receiveRequest = webTestClient.post()
                .uri("/api/account/register")
                .bodyValue(AccountUtil.getAccountDTO("02300916912"))

        when:
        def receiveResponse = receiveRequest
                .exchange()
                .returnResult(AccountView.class)
                .getResponseBody()

        then:
        StepVerifier.create(receiveResponse)
                .expectSubscription()
                .expectError()
                .verify()
    }

    def 'should throw DuplicateUserException'() {
        given:
        def personalId = "02300916912"
        accountRepository.save(AccountUtil.getAccount(personalId)).subscribe()
        def receiveRequest = webTestClient.post()
                .uri("/api/account/register")
                .bodyValue(AccountUtil.getAccountDTO(personalId))

        when:
        def receiveResponse = receiveRequest
                .exchange()
                .returnResult(AccountView.class)
                .getResponseBody()

        then:
        StepVerifier.create(receiveResponse)
                .expectSubscription()
                .expectError()
                .verify()
    }

    def 'should get user by personalId'() {
        given:
        def personalId = "88030246447"
        accountRepository.save(AccountUtil.getAccount(personalId)).subscribe()
        subAccountRepository.save(AccountUtil.getSubAccountPLN()).subscribe()

        def receiveRequest = webTestClient.get()
                .uri("/api/account/" + personalId)

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
