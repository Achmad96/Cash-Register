import java.text.NumberFormat
import java.util.*

const val APPLICATION = "CASH-REGISTER"
const val VERSION = 1.1
fun main() {
    println("$APPLICATION: $VERSION")
    val foodMenus = arrayOf(
        arrayOf("fried chicken", "burger", "french fries"),
        arrayOf(25000, 18000, 20000)
    )
    print("Your money: ")
    val selfMoney = readln()
    if (selfMoney.toIntOrNull() == null) return
    var money = selfMoney.toInt()
    var prices = 0
    do {
        for ( i in 0..foodMenus.size){
            println("${i+1}. ${(foodMenus[0][i].toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })} = ${foodMenus[1][i].toString().toCurrencyFormat()}")
        }
        println("'confirm' to confirm your order.")
        print(": ")
        var menusChoose = readlnOrNull().toString().lowercase()
        if (menusChoose in foodMenus[0] && money >= foodMenus[1][foodMenus[0].indexOf(menusChoose)].toString().toInt()) {
            prices += foodMenus[1][foodMenus[0].indexOf(menusChoose)].toString().toInt()
            money -= foodMenus[1][foodMenus[0].indexOf(menusChoose)].toString().toInt()
        } else if (menusChoose in foodMenus[0] && money < foodMenus[1][foodMenus[0].indexOf(menusChoose)].toString().toInt()){
            println("Sorry, your money doesn't enough.")
            menusChoose = "confirm"
        } else if (menusChoose !in foodMenus[0] && menusChoose != "confirm"){
            println("Sorry, your order is not in the list menu.")
        }
    } while (menusChoose != "confirm")
    println("Prices: ${prices.toString().toCurrencyFormat()}")
    println("Your change: ${money.toString().toCurrencyFormat()}")
}

fun String.toCurrencyFormat(): String {
    val localeID = Locale("in", "ID")
    val doubleValue = this.toDoubleOrNull() ?: return this
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(doubleValue)
}