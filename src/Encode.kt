import java.math.BigInteger
import java.util.*

class Encode {

    private fun String.extend(): String {
        var bin = this
        while (bin.length < 8) bin = StringBuilder(bin).insert(0, '0').toString()
        return bin
    }

    internal fun toBinary(str: String): String {
        val binary = StringBuffer()
        str.forEach {
            binary.append(Integer.toBinaryString(it.toInt()).extend())
        }

        return binary.toString()
    }

    fun generate(message: String, publicKey: Pair<BigInteger, BigInteger>): ArrayList<BigInteger> {


        val binaryMessage = toBinary(message)

        val output = arrayListOf<BigInteger>()

        val bits = Random().nextInt(Generator.bitLength * 2) + 1
        val x = BigInteger(bits, Random())

        val one = (x.pow(2).multiply(publicKey.second)).mod(publicKey.first)
        val zero = x.pow(2).mod(publicKey.first)

        binaryMessage.forEach {
            if (it == '1') output.add(one)
            else output.add(zero)
        }

        return output

    }
}