package com.wavesplatform.api.http.requests

import com.wavesplatform.account.KeyPair
import com.wavesplatform.common.utils.EitherExt2
import com.wavesplatform.transaction.transfer.TransferTransaction
import com.wavesplatform.{NoShrink, TransactionGen}
import org.scalacheck.Gen
import org.scalatest.{FreeSpec, Matchers, OptionValues}
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import play.api.libs.json._

class RequestsSpec extends FreeSpec with Matchers with OptionValues with ScalaCheckPropertyChecks with TransactionGen with NoShrink {
  private def transferRequestGen(version: Int): Gen[(KeyPair, JsObject)] =
    (for {
      sender    <- accountGen
      recipient <- accountGen
      proofs    <- proofsGen
    } yield (
      sender,
      Json.obj(
        "type"            -> 4,
        "version"         -> version,
        "senderPublicKey" -> sender.publicKey.toString,
        "assetId"         -> JsNull,
        "attachment"      -> "",
        "feeAssetId"      -> JsNull,
        "timestamp"       -> System.currentTimeMillis(),
        "fee"             -> 100000,
        "amount"          -> 10000,
        "recipient"       -> recipient.publicKey.toAddress.stringRepr,
        "proofs"          -> JsArray(proofs.proofs.map(p => JsString(p.toString)))
      )
    )).label(s"Transfer Request v$version")

  "TransferRequest" - {
    "accepts proofs for version >= 2" in {
      TransferTransaction.supportedVersions.filter(_ >= 2).foreach { version =>
        forAll(transferRequestGen(version)) {
          case (sender, json) =>
            val request = json.as[TransferRequest]
            val tx      = request.toTxFrom(sender.publicKey).explicitGet()

            request.proofs.value should be(tx.proofs)
        }
      }

    }
  }
}
