package PrimeGenerator

import java.math.BigInteger
import java.util.*

class PrimeGenerator(bitLength: Int, rounds: Int) {
    var number: BigInteger

    init {
        do {
            number = BigInteger(bitLength, Random()).or(BigInteger.ONE)!!
            val x = TestRabinMiller(number, rounds).isPrime()
        } while (!x)
    }
}