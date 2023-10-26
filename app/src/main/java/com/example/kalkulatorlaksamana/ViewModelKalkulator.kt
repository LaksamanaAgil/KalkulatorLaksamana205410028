package com.example.kalkulatorlaksamana

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class ViewModelKalkulator: ViewModel() { //TODO: KELAS VIEWMODEL
    val expression = mutableStateOf("")
    fun clear() { //TODO: FUNGSI UNTUK TOMBOL AC (MENGHAPUS SEMUA PERHITUNGAN)
        expression.value = ""
    }

    fun append(char: String) { //TODO: FUNGSI UNTUK TOMBOL ANGKA DAN PERHITUNGAN
        Log.d("append", "$char Expression Value:${expression.value}")
        if (char in "0123456789") { //TODO: UNTUK TOMBOL ANGKA MEMASUKKAN ANGKA YANG SESUAI
            expression.value += char
        }else if(char in "+-×÷") { //TODO: UNTUK TOMBOL ANGKA MEMULAI AKSI PERHITUNGAN YANG SESUAI
            if (expression.value.isNotEmpty()) {
                val lastChar = expression.value.last()

                //TODO: JIKA MASUKAN SEBELUMNYA MERUPAKAN MASUKAN PERHITUNGAN ( +,-,:,DSB), MAKA DIGANTI DENGAN MASUKAN PERHITUNGAN YANG BARU DIMASUKKAN
                if (lastChar in "+-×÷") {
                    expression.value = expression.value.dropLast(1)
                }
            }
            expression.value += char
        }else if(char == ".") {//TODO: UNTUK ANGKA DESIMAL
            if (expression.value.isNotEmpty()) {
                val lastChar = expression.value.last()
                if (lastChar!='.') {
                    //TODO: JIKA MASUKAN SEBELUMNYA MERUPAKAN MASUKAN PERHITUNGAN( +,-,:,DSB), MAKA SEBELUM . DITAMBAHKAN 0 (MISAL 2 + 0.)
                    if (lastChar in "+-×÷") {
                        expression.value += "0"
                    }
                    expression.value += char
                }
            }

        }else if(char =="("){ //TODO: FUNGSI KURUNG BUKA
            if (expression.value.isNotEmpty()) {
                val lastChar = expression.value.last()
                // TODO: JIKA MASUKAN SEBELUMNYA MERUPAKAN MASUKAN SELAIN PERHITUNGAN(MISALNYA MASUKAN ANGKA), MASUKAN SEBELUMNYA DAN MASUKAN DIDALAM KURUNG AKAN DIKALI
                if (lastChar !in "+-×÷") {
                    expression.value += "×"
                }
            }
            expression.value += char
        }else if(char ==")"){
            expression.value += char
        }
    }

    fun delete() { //todo: FUNGSI PENGHAPUSAN SATU DIGIT
        if (expression.value.isNotEmpty()) {
            expression.value = expression.value.dropLast(1)
        }
    }

    fun evaluate() { //TODO: FUNGSI EVALUASI /SAMA-DENGAN
        expression.value = try {
            val result = evaluate(expression.value)
            result.toString()
        } catch (e: Exception) { //TODO: JIKA OUTPUT TIDAK BENAR/ERROR
            "Error"
        }
    }
}