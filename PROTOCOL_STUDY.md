# Protocol Study

1. Communiction Protocol: http?? https://nodes.wavesplatform.com/api-docs/index.html#/blocks/first_1
1. [Algorithm Keys](https://docs.wavesplatform.com/en/blockchain/binary-format/address-binary-format)
1. [Algorithm Address](https://docs.wavesplatform.com/en/blockchain/binary-format/address-binary-format)
1. Consensus Protocol:
1. How to register to the Network:
1. How to setup the Database (blockchain history):
1. Message:
  1. Type (JSON?):
  1. Body Structure:
    1. [All Binary Formats List](https://docs.wavesplatform.com/en/blockchain/binary-format/)
    1. [Genesis Format](https://docs.wavesplatform.com/en/blockchain/binary-format/transaction-binary-format/genesis-transaction-binary-format)
    1. [Transfer Format](https://docs.wavesplatform.com/en/blockchain/binary-format/transaction-binary-format/transfer-transaction-binary-format#transaction-version-2)
    1. [Order Format](https://docs.wavesplatform.com/en/blockchain/binary-format/order-binary-format#binary-format-version-3)
  1. [__CHANGING NETWORK & KEYS ALGORITHM__](https://docs.wavesplatform.com/en/blockchain/binary-format/address-binary-format)

## Meanning
1. Accounts unambiguously (errorless) correlate transactions and orders with their senders
1.

## Steps to soft fork
### Running Standalone Environment (node/server + explore + client)
1. [Node (1.1.9)](https://github.com/TonGarcia/Waves)
1. [Explore (2.14.1)](https://github.com/TonGarcia/WavesExplorerLite)
1. [Client/DEX (2.0.0)](https://github.com/TonGarcia/dex)



# Explore
1. Explore Site (blockchain.info like): https://wavesexplorer.com/
1. How to get currencies at the testnet (faucet): https://wavesexplorer.com/testnet/faucet
1. Networks Types:
  1. Stagenet:
    - meaning: runs the same software release & history as Mainnet, but it coin has no value
    - goal: to test something that will come alive in the mainnet like smart contract
    - setup: download mainnet history and run as node
  1. Testnet:
    - meaning: is where the bleeding-edge development takes places (coin, not token, is developed)
    - goal: test development features using fake & small history
    - setup: compile it yourself
  1. Mainnet:
    - meaning: the real life blockchain, with real money
    - goal: production version
    - setup: just run it as a client master node


# Definitions


# Features Study
1. Stablecoin with Swap Service: https://beta.neutrino.at
1. Leasing/Loan service: https://lombardini.io/
1. New DEX & new fees: https://waves.exchange/
