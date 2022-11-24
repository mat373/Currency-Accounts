# Currency Accounts

Currency Account - exchange money between sub-accounts in different currencies.

## API Reference

#### Register new account

`personalId` (PESEL) is unique 

`startingBalanceInPLN` should be > 0

```http
  POST localhost:8023/api/account/register
```
Example request body:
```json
{
"firstName": "John",
"lastName": "Doe",
"personalId": "88030246447",
"balance": 1000
}
```
Response:
```json
{
  "account": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "personalId": "88030246447"
  },
  "subAccounts": [
    {
      "id": 1,
      "accountId": 1,
      "currency": "USD",
      "balance": 0
    },
    {
      "id": 2,
      "accountId": 1,
      "currency": "PLN",
      "balance": 1000
    }
  ]
}
```

#### Get account by personal Id

```http
  GET localhost:8023/api/account/{personalId}
```

Response:
```json
{
  "account": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "personalId": "88030246447"
  },
  "subAccounts": [
    {
      "id": 1,
      "accountId": 1,
      "currency": "USD",
      "balance": 0.0
    },
    {
      "id": 2,
      "accountId": 1,
      "currency": "PLN",
      "balance": 100.0
    }
  ]
}
```

#### Exchange money between sub-accounts

```http
  POST localhost:8023/api/exchange
```
Example request body (Exchange PLN to USD):
```json
{
  "personalId": "88030246447",
  "currencyFrom": "PLN",
  "currencyTo": "USD",
  "amountFrom": 10
}
```
Response:
```json
{
  "id": 2,
  "accountId": 1,
  "currency": "PLN",
  "balance": 90.0
},
{
  "id": 1,
  "accountId": 1,
  "currency": "USD",
  "balance": 2.20
}
```
Api makes available the date on purchase and sales courses (ask and bid), so the balance will be different when swapping again

Example request body (Exchange USD to PLN):
```json
{
  "personalId": "88030246447",
  "currencyFrom": "USD",
  "currencyTo": "PLN",
  "amountFrom": 2.20
}
```
Response:
```json
{
  "id": 1,
  "accountId": 1,
  "currency": "USD",
  "balance": 0.00
},
{
  "id": 2,
  "accountId": 1,
  "currency": "PLN",
  "balance": 99.8780
}
```