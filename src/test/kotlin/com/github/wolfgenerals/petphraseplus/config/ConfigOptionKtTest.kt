package com.github.wolfgenerals.petphraseplus.config

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Gen
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next
import io.kotest.property.arbitrary.string
import io.kotest.property.forAll

class ConfigOptionKtTest : FunSpec({

    test("encode") {
        forAll(10000,configGen){
            it == it.encode().decodeConfig()
        }
    }


})



private val stringArb = Arb.string()
private val stringListArb = Arb.list(stringArb)
var configGen: Arb<Config> = arbitrary { Config(
    enabled = it.random.nextBoolean(),
    mark = stringArb.next(),
    endInner = stringArb.next(),
    endOuter = stringArb.next(),
    stringListArb.next(),
) }
