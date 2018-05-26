package Unused

import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

private fun isEven(x: BigInteger): Boolean {
    return x % BigInteger.TWO == BigInteger.ZERO
}

private fun jacobi(A: BigInteger, B: BigInteger): BigInteger {


    if (A == BigInteger.ZERO)
        return if (B == BigInteger.ONE) BigInteger.ONE else BigInteger.ZERO
    if (A == BigInteger.ONE) return BigInteger.ONE

    val e = BigInteger.ZERO
    val a1 = A
    var s = BigInteger.ZERO

    if (isEven(e)) s = BigInteger.ONE
    else if ((B.mod(BigInteger.valueOf(8)) == BigInteger.ONE) || (B.mod(BigInteger.valueOf(8)) == BigInteger.valueOf(7))) s = BigInteger.ONE
    else if ((B.mod(BigInteger.valueOf(8)) == BigInteger.valueOf(3)) || (B.mod(BigInteger.valueOf(8)) == BigInteger.valueOf(5))) s = BigInteger.ONE.negate()
    if ((B.mod(BigInteger.valueOf(4)) == BigInteger.valueOf(3)) && (a1.mod(BigInteger.valueOf(4)) == BigInteger.valueOf(3)))
        s = s.negate()
    val n1 = B.mod(a1)
    return if (a1 == BigInteger.ONE) s
    else s * jacobi(n1, a1)

}

private fun legendre(a: BigInteger, p: BigInteger): Int {

    val s = a.modPow((p - BigInteger.ONE) / BigInteger.TWO, p)

    if (a.mod(p) == BigInteger.ZERO) return 0
    return when (s) {
        BigInteger.ONE -> 1
        BigInteger.ONE.negate() -> -1
        else -> 2
    }
}

private fun multInv(a: BigInteger, b: BigInteger): BigInteger {
           if (b == ONE) return ONE
           var aa = a
           var bb = b
           var x0 = ZERO
           var x1 = ONE
           while (aa > ONE) {
               val q = aa.div(bb)
               var t = bb
               bb = aa.remainder(bb)
               aa = t
               t = x0
               x0 = x1.minus(q.multiply(x0))
               x1 = t
           }
           if (x1 < ZERO) x1 = x1.add(b)
           return x1
       }