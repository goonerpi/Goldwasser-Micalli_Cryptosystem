import PrimeGenerator.PrimeGenerator
import java.math.BigInteger
import java.math.BigInteger.*
import java.util.*


fun jacobi2(m: BigInteger, n: BigInteger): BigInteger {

    val m1 = m % n

    return when {
        m1 == ZERO -> ZERO
        m1 == ONE -> ONE
        m1.mod(TWO) == ZERO -> if (m1.mod(valueOf(8)).abs() == ONE) jacobi2(m1 / TWO, n)
        else -jacobi2(m1 / TWO, n)
        else -> if ((n.mod(valueOf(4)) == valueOf(3)) && (m1.mod(valueOf(3)) == valueOf(3))) -jacobi2(n, m1)
        else jacobi2(n, m1)
    }
}

object Generator {

    const val bitLength = 1024

    val p = PrimeGenerator(bitLength, 5).number
    val q: BigInteger by lazy {
        val generator = PrimeGenerator(bitLength, 5)
        var res: BigInteger
        do {
            res = generator.number
        } while (res == p)
        res
    }

    val n = p.multiply(q)

    lateinit var publicKey: Pair<BigInteger, BigInteger>
    lateinit var privateKey: Pair<BigInteger, BigInteger>

    val a = BigInteger(Random().nextInt(bitLength * 2) + 1, Random())


    private fun quadraticNonResidue(p: BigInteger): BigInteger {

        var a: BigInteger = ZERO

        while (jacobi2(a, p) != -ONE) {
            val bits = Random().nextInt(bitLength) + 1
            a = BigInteger(bits, Random())
        }

        return a
    }



    private fun chineseRemainder(a: Array<BigInteger>, n: Array<BigInteger>): BigInteger {
        val prod: BigInteger = n.reduce { acc, i -> acc.multiply(i) }
        var sum = ZERO

        n.forEachIndexed { i, bigInteger ->
            val ni = prod / bigInteger
            val mi = sum.modPow(bigInteger - TWO, bigInteger)
            sum += (a[i] * ni * mi).mod(bigInteger)
        }

        return sum
    }

    private fun pseudoRoot(p: BigInteger, q: BigInteger): BigInteger {
        val a = quadraticNonResidue(p)
        val b = quadraticNonResidue(q)

        return chineseRemainder(arrayOf(a, b), arrayOf(p, q))
    }

    fun generateKey(): Pair<Pair<BigInteger, BigInteger>, Pair<BigInteger, BigInteger>> {

        val y: BigInteger = pseudoRoot(p, q)

        publicKey = Pair(n, y)
        privateKey = Pair(p, q)

        return Pair(publicKey, privateKey)
    }


}


