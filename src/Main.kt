fun main(array: Array<String>) {

    val generator = Generator
    val encoder = Encode()
    val decoder = Decode()
    val message = "Hello Niggas! Ez Clap LOOOL xD"
    val cryptoText = encoder.generate(message, generator.generateKey().first)
    decoder.decode(cryptoText, generator.generateKey().second)

    println("\n\n----------------------------------------------------------------------")
    println("            Realization of Goldwasser-Micali cryptosystem")
    println("----------------------------------------------------------------------")
    println("Input message: $message")
    println("----------------------------------------------------------------------")
    println("Keys was generated!\nBit Length: ${generator.bitLength}\nPublic key: (${generator.n}, ${generator.a}) || Private key: (${generator.p}, ${generator.q})")
    println("----------------------------------------------------------------------")
    println("Binary input string:  ${encoder.toBinary(message)}")
    print("Binary output string: ")
    decoder.output.forEach { if (it == 1) print(1) else print(0) }
    println("\n----------------------------------------------------------------------")
    println("Decoded message: ${decoder.outputStr}")
    println("----------------------------------------------------------------------")

}