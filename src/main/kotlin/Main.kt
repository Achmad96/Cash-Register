import java.text.NumberFormat
import java.util.*

const val APPLICATION = "KOTLIN-APP"
const val VERSION = 1.0
fun main() {
    println("$APPLICATION: $VERSION")
    val foodMenus = arrayOf(
        arrayOf("fried chicken", "burger", "french fries"),
        arrayOf(25000, 18000, 20000)
    )
    var prices = 0
    do {
        for ( i in 0..foodMenus.size){
            println("${i+1}. ${(foodMenus[0][i].toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })} = ${foodMenus[1][i].toString().toCurrencyFormat()}")
        }
        println("'confirm' to confirm your order.")
        print(": ")
        val menusChoose = readlnOrNull().toString().lowercase()
        if (menusChoose in foodMenus[0]) {
            prices += foodMenus[1][foodMenus[0].indexOf(menusChoose)].toString().toInt()
        } else if (menusChoose !in foodMenus[0] && menusChoose != "confirm"){
            println("Sorry, your order is not in the list menu.")
        }
    } while (menusChoose != "confirm")
    println("Prices: ${prices.toString().toCurrencyFormat()}")
}
fun String.toCurrencyFormat(): String {
    val localeID = Locale("in", "ID")
    val doubleValue = this.toDoubleOrNull() ?: return this
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(doubleValue)
}