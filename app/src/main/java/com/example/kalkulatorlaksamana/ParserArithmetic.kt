package com.example.kalkulatorlaksamana
//TODO: FILE INI DIGUNAKAN UNTUK PARSING ARITMATIKA AGAR HASIL PERHITUNGAN SESUAI ATURAN ARITMATIKA
//TODO: CLASS UNTUK TOKENIZER, YANG BERFUNGSI MEMBAGIKAN MASUKAN MENJADI BEBERAPA ARRAY TOKEN(DIGIT/ANGKA, OPERATOR(PERHITUNGAN +,:,-,x) DAN BRACE (KURUNG)
class Tokenizer(val input: String) {
    // TODO: MENYIMPAN POSISI MASUKAN
    var pos = 0

    // TODO: MENYIMPAN TOKEN DARI MASUKAN
    var token: String? = null

    //TODO: MELANJUTKAN KE ARRAY TOKEN BERIKUTNYA
    fun nextToken() {

        while (pos < input.length && input[pos].isWhitespace()) {
            pos++
        }

        if (pos == input.length) {
            token = null
            return
        }

        // TODO: JIKA KARAKTER YANG DIPARSING ADALAH DIGIT ATAU BILANGAN DESIMAL, MAKA DIPARSE SEBAGAI ANGKA
        if (input[pos].isDigit() || input[pos] == '.') {

            val sb = StringBuilder()
            while (pos < input.length && (input[pos].isDigit() || input[pos] == '.')) {
                sb.append(input[pos])
                pos++
            }
            token = sb.toString()
            return
        }

        if ("+-×÷()".contains(input[pos])) {

            token = input[pos].toString()
            pos++
            return
        }

        throw IllegalArgumentException("Invalid character: ${input[pos]}")
    }
}


fun evaluate(expression: String): Double { //TODO: TOKENIZER UNTUK EVALUASI/SAMA DENGAN

    val tokenizer = Tokenizer(expression)

    tokenizer.nextToken()

    return parseExpression(tokenizer)
}

// TODO: FUNGSI UNTUK MEMPARSING EXPRESSION, LALU MELAKUKAN OPERASI PENAMBAHAN DAN PENGURANGAN
fun parseExpression(tokenizer: Tokenizer): Double {

    var result = parseTerm(tokenizer)

    while (tokenizer.token in listOf("+", "-")) {

        val op = tokenizer.token!!

        tokenizer.nextToken()

        val term = parseTerm(tokenizer)

        result = when (op) {
            "+" -> result + term
            "-" -> result - term
            else -> throw IllegalStateException("Invalid operator: $op")
        }
    }

    return result
}

// TODO: FUNGSI UNTUK PARSING TERM DAN MELAKUKAN OPERASI PERKALIAN DAN PEMBAGIAN
fun parseTerm(tokenizer: Tokenizer): Double {

    var result = parseFactor(tokenizer)
    while (tokenizer.token in listOf("×", "÷")) {

        val op = tokenizer.token!!
        tokenizer.nextToken()
        val factor = parseFactor(tokenizer)

        result = when (op) {
            "×" -> result * factor
            "÷" -> result / factor
            else -> throw IllegalStateException("Invalid operator: $op")
        }
    }
    return result

}

// TODO: FUNGSI UNTUK MENGAMBIL NILAI FAKTOR and carry out unary operations and also to handle parenthesis
fun parseFactor(tokenizer: Tokenizer): Double {

    if (tokenizer.token == null) {
        throw IllegalArgumentException("Missing factor")
    }
    //TODO: MENGECEK APAKAH TOKEN SEBUAH ANGKA
    if (tokenizer.token!!.toDoubleOrNull() != null) {

        val value = tokenizer.token!!.toDouble()

        tokenizer.nextToken()

        return value
    }

    //TODO: MENGECEK APAKAH TOKEN MERUPAKAN OPERATOR PENAMBAHAN DAN PENGURANGAN)
    if (tokenizer.token in listOf("+", "-")) {

        val op = tokenizer.token!!

        tokenizer.nextToken()

        val factor = parseFactor(tokenizer)

        return when (op) {
            "+" -> +factor
            "-" -> -factor
            else -> throw IllegalStateException("Invalid operator: $op")
        }
    }

    //TODO: HANDLING KURUNGAN ()
    if (tokenizer.token == "(" && tokenizer.input.indexOf(")", tokenizer.pos) != -1) { // Added this condition

        tokenizer.nextToken()

        // TODO: PARSING EKSPRESI DIDALAM KURUNGAN
        val value = parseExpression(tokenizer)

        if (tokenizer.token == ")") {

            tokenizer.nextToken()

            return value
        } else {
            throw IllegalArgumentException("Missing closing parenthesis")
        }
    }
    else {
        throw IllegalArgumentException("Invalid factor: ${tokenizer.token}")
    }
}


