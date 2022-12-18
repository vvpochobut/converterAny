package converter
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.pow
import java.math.RoundingMode

fun main() {

    var work = true
    while (work) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val choice = readln().split(' ')
        if(choice[0] == "10"){  //десятичной системы в выбраную систему счисления
            tenInTwo(choice[0].toInt(),choice[1].toInt())
        } else if(choice[0] == "/exit"){ //точка выхода из цикла программы
            work = false
        } else if(choice[1] == "10"){ //перевод в десятичную систему систему счисления
            twoInTen(choice[0].toDouble(),choice[1])
        }else { //перевод любой системы счисления в выбранную
            any(choice[0].toDouble(),choice[1].toInt())
                /* Можно модернизировать и оставить всего 2 ветки,но во первых остальные две ветки служат для более быстрого
                * выполнения программы по готовому сценарию,во вторых учебный проект,выполнялся последовательно,где изначальное требование было
                * перевода из десятичной системы в двоичную и обратно */
        }
    }
}


fun tenInTwo(systemSchisleniyua:Int,systemNumber:Int) {
    var work = true  //Включаем цикл,для перевода нескольких значений
    while(work){
        print("Enter number in base $systemSchisleniyua to convert to base $systemNumber (To go back type /back) ")
        val comand = readln()
        if(comand != "/back"){
            val massivIntAndDrob = comand.split('.',',') //Отделяем целую часть от дробной
            val intString = tenInTwoDrob((massivIntAndDrob[0]), systemNumber).reversed()
            if(massivIntAndDrob.size > 1) {
                val ostString = drob(massivIntAndDrob[1], systemNumber)
                println("Conversion result: ${intString.toBigDecimal() + ostString.toBigDecimal()}")
            } else{
                println("Conversion result: ${intString.toBigDecimal()}")
            }
        } else{
            work = false
        }
    }
}

fun tenInTwoDrob(comand: String, systemNumber: Int): String {
    if(comand != "0"){ //Проверяем не является ли число 0,т.к. 0 будет являться 0 в любой системе счисления
        var number = comand.toBigInteger()
        var result = ""
        while (number != 0.toBigInteger()) {
            if (number > 0.toBigInteger()) {
                val r = number % systemNumber.toBigInteger()
                result += if (r > 9.toBigInteger()) {
                    when (r.toInt()) {
                        10 -> 'A'
                        11 -> 'B'
                        12 -> 'C'
                        13 -> "D"
                        14 -> "E"
                        15 -> "F"
                        16 -> "G"
                        17 -> "H"
                        18 -> "I"
                        19 -> "J"
                        20 -> "K"
                        21 -> "L"
                        22 -> "M"
                        23 -> "N"
                        24 -> "O"
                        25 -> "P"
                        26 -> "Q"
                        27 -> "R"
                        28 -> "S"
                        29 -> "T"
                        30 -> "U"
                        31 -> "V"
                        32 -> "W"
                        33 -> "X"
                        34 -> "Y"
                        35 -> "Z"
                        else -> "Fails"
                    }
                } else {
                    "$r"
                }
                number /= systemNumber.toBigInteger()
            }
        }
        return result
    } else{
        return "0"
    }
}

fun twoInTen(scale:Double,base:String) {
    var work = true //Цикл перевода десятичной системы в выбранную
    while (work) {
        print("Enter number in base ${scale.toInt()} to convert to base $base (To go back type /back) ")
        val comand = readln()
        if (comand != "/back") {
            val massivIntAndDrob = comand.split('.',',')
            if(massivIntAndDrob.size > 1) {
                val intString = twoInTenInt(massivIntAndDrob[0], scale)
                val drobString = twoInTenDrob(("0.${massivIntAndDrob[1]}"), scale)
                println("Conversion result: ${intString + drobString}")
            } else{
                println("Conversion result: ${twoInTenInt(massivIntAndDrob[0], scale)}")
            }
        } else {
            work = false
        }
    }
}

fun twoInTenInt(comand: String, scale: Double): BigDecimal {
    val number = comand.reversed()
    var result = 0.toBigDecimal()
    var stepen = 0.0
    for (i in number) {
        val vremen: String = when (i) {
            'A', 'a' -> "10"
            'B', 'b' -> "11"
            'C', 'c' -> "12"
            'D', 'd' -> "13"
            'E', 'e' -> "14"
            'F', 'f' -> "15"
            'G', 'g' -> "16"
            'H', 'h' -> "17"
            'I', 'i' -> "18"
            'J', 'j' -> "19"
            'K', 'k' -> "20"
            'L', 'l' -> "21"
            'M', 'm' -> "22"
            'N', 'n' -> "23"
            'O', 'o' -> "24"
            'P', 'p' -> "25"
            'Q', 'q' -> "26"
            'R', 'r' -> "27"
            'S', 's' -> "28"
            'T', 't' -> "29"
            'U', 'u' -> "30"
            'V', 'v' -> "31"
            'W', 'w' -> "32"
            'X', 'x' -> "33"
            'Y', 'y' -> "34"
            'Z', 'z' -> "35"
            '.' -> "."
            else -> i.toString()
        }
        result += vremen.toInt().toBigDecimal() * scale.pow(stepen).toBigDecimal()
        stepen++
    }
    return result
}
fun twoInTenDrob(comand: String, scale: Double):BigDecimal {
    var result = 0.toBigDecimal()
    var stepen = 1.0
    for (i in comand.substring(2)) {
        val vremen: String = when (i) {
            'A', 'a' -> "10"
            'B', 'b' -> "11"
            'C', 'c' -> "12"
            'D', 'd' -> "13"
            'E', 'e' -> "14"
            'F', 'f' -> "15"
            'G', 'g' -> "16"
            'H', 'h' -> "17"
            'I', 'i' -> "18"
            'J', 'j' -> "19"
            'K', 'k' -> "20"
            'L', 'l' -> "21"
            'M', 'm' -> "22"
            'N', 'n' -> "23"
            'O', 'o' -> "24"
            'P', 'p' -> "25"
            'Q', 'q' -> "26"
            'R', 'r' -> "27"
            'S', 's' -> "28"
            'T', 't' -> "29"
            'U', 'u' -> "30"
            'V', 'v' -> "31"
            'W', 'w' -> "32"
            'X', 'x' -> "33"
            'Y', 'y' -> "34"
            'Z', 'z' -> "35"
            else -> i.toString()
        }
        result += vremen.toBigDecimal() * (1.0.toFloat() / scale.pow(stepen).toFloat()).toBigDecimal()
        stepen++
    }
    return result
}

fun any(sourseBase:Double,targetBase:Int){
    var work = true
    while (work) {
        print("Enter number in base ${sourseBase.toInt()} to convert to base $targetBase (To go back type /back) ")
        val comand = readln()
        if (comand != "/back") {
            val massivIntAndDrob = comand.split('.',',')
            if(massivIntAndDrob.size > 1) {
                val intString = twoInTenInt(massivIntAndDrob[0], sourseBase)
                val drobString = twoInTenDrob(("0.${massivIntAndDrob[1]}"), sourseBase)
                val prom = (intString + drobString).toString().split('.')
                val inTarget = tenInTwoDrob(prom[0], targetBase).reversed()
                val ostString = drob(prom[1], targetBase)
                val res = "$inTarget.${ostString.substring(2)}"
                println("Conversion result: $res")

            } else{
                val intString = twoInTenInt(massivIntAndDrob[0], sourseBase).toString().split('.')
                val inTarget = tenInTwoDrob(intString[0], targetBase).reversed()
                println("Conversion result: $inTarget")
            }
        } else {
            work = false
        }
    }
}




fun drob(str:String,systemNumber: Int):String {
    if(str.toBigInteger() != 0.toBigInteger()){
        var ost = "0.$str".toBigDecimal()
        var ostS = "0."
        while(ost.setScale(2,RoundingMode.DOWN) % 1.toBigDecimal() != 0.00.toBigDecimal() && ostS.length < 7) {
            ost *= systemNumber.toBigDecimal()
            ostS += when (ost.setScale(0, RoundingMode.DOWN).toInt()) {
                10 -> 'A'
                11 -> 'B'
                12 -> 'C'
                13 -> "D"
                14 -> "E"
                15 -> "F"
                16 -> "G"
                17 -> "H"
                18 -> "I"
                19 -> "J"
                20 -> "K"
                21 -> "L"
                22 -> "M"
                23 -> "N"
                24 -> "O"
                25 -> "P"
                26 -> "Q"
                27 -> "R"
                28 -> "S"
                29 -> "T"
                30 -> "U"
                31 -> "V"
                32 -> "W"
                33 -> "X"
                34 -> "Y"
                35 -> "Z"
                else -> ost.setScale(0, RoundingMode.DOWN).toInt()
            }
            if (ost >= 1.toBigDecimal()) {
                ost -= ost.setScale(0,RoundingMode.DOWN)
            }
        }
        while(ostS.length<7){
            ostS+=0
        }
        return ostS
    } else {
        return "0000000"
    }
}
