package pl.pm.currencyaccounts.core.exception

import pl.pm.currencyaccounts.exchange.model.Exchange

class TooLowBalanceException(exchange: Exchange) :
    Exception(
        "To low balance to exchange " +
                "${exchange.amountFrom}${exchange.currencyFrom} " +
                "to ${exchange.currencyTo} account"
    )