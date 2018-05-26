import java.math.BigInteger

class Decode {

    val output = arrayListOf<Int>()
    var outputStr: String = ""


    private fun decodeChar(bits: MutableList<Int>): Char {

        val sb = StringBuffer()
        bits.forEach { sb.append(it.toString()) }

        return Integer.parseInt(sb.toString(), 2).toChar()
    }

    fun decode(array: ArrayList<BigInteger>, privateKey: Pair<BigInteger, BigInteger>) {

        val p = privateKey.first

        val sb = StringBuffer()
        array.forEach {
            val e = jacobi2(it, p).toInt()
            if (e == 0) output.add(1)
            else output.add(0)
        }

        for (i in 0..output.lastIndex step 8) {
            sb.append(decodeChar(output.subList(i, i + 8)))
        }

        outputStr = sb.toString()
    }
}