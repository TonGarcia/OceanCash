package com

import com.wavesplatform.block.Block
import com.wavesplatform.common.state.ByteStr
import com.wavesplatform.lang.ValidationError
import com.wavesplatform.settings.WavesSettings
import com.wavesplatform.state.NG
import com.wavesplatform.transaction.TxValidationError.GenericError
import com.wavesplatform.transaction.BlockchainUpdater
import com.wavesplatform.utils.ScorexLogging

package object wavesplatform extends ScorexLogging {
  private def checkOrAppend(block: Block, blockchainUpdater: BlockchainUpdater with NG): Either[ValidationError, Unit] = {
    if (blockchainUpdater.isEmpty) {
      blockchainUpdater.processBlock(block, block.header.generationSignature).right.map { _ =>
        log.info(s"Genesis block ${blockchainUpdater.blockInfo(1).get.header} has been added to the state")
      }
    } else {
      val existingGenesisBlockId: Option[ByteStr] = blockchainUpdater.blockInfo(1).map(_.signature)
      Either.cond(existingGenesisBlockId.fold(false)(_ == block.uniqueId),
                  (),
                  GenericError("Mismatched genesis blocks in configuration and blockchain"))
    }
  }

  def checkGenesis(settings: WavesSettings, blockchainUpdater: BlockchainUpdater with NG): Unit = {
    Block
      .genesis(settings.blockchainSettings.genesisSettings)
      .flatMap { genesis =>
        log.debug(s"Genesis block: $genesis")
        log.debug(s"Genesis block json: ${genesis.json()}")
        checkOrAppend(genesis, blockchainUpdater)
      }
      .left
      .foreach { e =>
        log.error("INCORRECT NODE CONFIGURATION!!! NODE STOPPED BECAUSE OF THE FOLLOWING ERROR:")
        log.error(e.toString)
        com.wavesplatform.utils.forceStopApplication()
      }
  }
}
