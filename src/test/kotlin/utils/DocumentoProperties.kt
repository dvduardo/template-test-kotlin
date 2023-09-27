package utils

import java.util.*

object DocumentoProperties {

    fun geraCPF(): String {
        val soma1: Int
        val soma2: Int

        val cpf = StringBuilder()
        val random = Random()

        val digitos = IntArray(11)
        for (i in 0..8) {
            digitos[i] = random.nextInt(10)
        }

        soma1 =
            digitos[8] * 2 + digitos[7] * 3 + digitos[6] * 4 + digitos[5] * 5 + digitos[4] * 6 + digitos[3] * 7 + digitos[2] * 8 + digitos[1] * 9 + digitos[0] * 10
        val resto1: Int = soma1 % 11

        digitos[9] = if (resto1 < 2) 0 else 11 - resto1

        soma2 =
            digitos[9] * 2 + digitos[8] * 3 + digitos[7] * 4 + digitos[6] * 5 + digitos[5] * 6 + digitos[4] * 7 + digitos[3] * 8 + digitos[2] * 9 + digitos[1] * 10 + digitos[0] * 11
        val resto2: Int = soma2 % 11

        digitos[10] = if (resto2 < 2) 0 else 11 - resto2

        cpf.append(digitos[0]).append(digitos[1]).append(digitos[2]).append(".")
            .append(digitos[3]).append(digitos[4]).append(digitos[5]).append(".")
            .append(digitos[6]).append(digitos[7]).append(digitos[8]).append("-")
            .append(digitos[9]).append(digitos[10])

        if (ValidadorCPF().validaCPF(cpf.toString()))
            return cpf.toString()
        else
            throw Exception("CPF invalido")
    }


    class ValidadorCPF {
        private val PESO = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
        private fun calcularDigito(str: String, peso: IntArray): Int {
            var soma = 0
            var indice = str.length - 1
            var digito: Int
            while (indice >= 0) {
                digito = str.substring(indice, indice + 1).toInt()
                soma += digito * peso[peso.size - str.length + indice]
                indice--
            }
            soma = 11 - soma % 11
            return if (soma > 9) 0 else soma
        }

        fun validaCPF(cpf: String?): Boolean {
            var cpf = cpf ?: return false
            cpf = cpf.replace("\\D".toRegex(), "")
            if (cpf.length != 11) {
                return false
            }
            val digito1 = calcularDigito(cpf.substring(0, 9), PESO)
            val digito2 = calcularDigito(cpf.substring(0, 9) + digito1, PESO)
            return cpf == cpf.substring(0, 9) + digito1 + digito2
        }
    }
}