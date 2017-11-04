@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.minDivisor

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (i in v) sum += sqr(i)
    return Math.sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.sum() / list.count() else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val mean = mean(list)
        for (i in 0 until list.count())
            list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var product = 0.0
    for (i in 0 until a.count())
        product += a[i] * b[i]
    return product
}

fun pow(num: Double, power: Int): Double {
    if (power == 0) return 1.0
    var x = num
    for (i in 1 until power)
        x *= num
    return x
}

fun pow(num: Int, power: Int): Int {
    if (power == 0) return 1
    var x = num
    for (i in 1 until power)
        x *= num
    return x
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var sum = 0.0
    for (i in 0 until p.count())
        sum += p[i] * pow(x, i)
    return sum
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty())
        for (i in 1 until list.count()) list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var num = n
    val list: MutableList<Int> = mutableListOf()
    while (num > 1) {
        list.add(minDivisor(num))
        num /= list.last()
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    var row = ""
    for (i in 0 until list.count()) {
        row += list[i]
        if (i < list.count() - 1)
            row += "*"
    }
    return row
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list: MutableList<Int> = mutableListOf()
    var num = n
    var power = 0
    while (pow(base, power + 1) <= n)
        if (pow(base, power) <= n) power++
    for (i in power downTo 0) {
        list.add(num / pow(base, i))
        num -= list.last() * pow(base, i)
    }
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    val map: Map<Int, String> = mapOf(0 to "0", 1 to "1", 2 to "2", 3 to "3", 4 to "4", 5 to "5", 6 to "6",
            7 to "7", 8 to "8", 9 to "9", 10 to "a", 11 to "b", 12 to "c", 13 to "d", 14 to "e", 15 to "f", 16 to "g",
            17 to "h", 18 to "i", 19 to "j", 20 to "k", 21 to "l", 22 to "m", 23 to "n", 24 to "o", 25 to "p", 26 to "q",
            27 to "r", 28 to "s", 29 to "t", 30 to "u", 31 to "v", 32 to "w", 33 to "x", 34 to "y", 35 to "z")
    var row = ""
    for (i in list) row += map[i]
    return row
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = 0
    for (i in 0 until digits.count())
        sum += digits[i] * pow(base, digits.count() - i - 1)
    return sum
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val list: MutableList<Int> = mutableListOf()
    val map: Map<String, Int> = mapOf("0" to 0, "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6,
            "7" to 7, "8" to 8, "9" to 9, "a" to 10, "b" to 11, "c" to 12, "d" to 13, "e" to 14, "f" to 15, "g" to 16,
            "h" to 17, "i" to 18, "j" to 19, "k" to 20, "l" to 21, "m" to 22, "n" to 23, "o" to 24, "p" to 25, "q" to 26,
            "r" to 27, "s" to 28, "t" to 29, "u" to 30, "v" to 31, "w" to 32, "x" to 33, "y" to 34, "z" to 35)
    for (i in str) list.add(map.getValue(i.toString()))
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val arab_dict: List<Int> = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    val rom_dict: List<String> = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    var arab = n
    var rom = ""
    var i = arab_dict.count() - 1
    while (arab > 0) {
        if (arab >= arab_dict[i]) {
            rom += rom_dict[i]
            arab -= arab_dict[i]
        } else i--
    }
    return rom
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val hundredsMap: Map<String, String> = mapOf("1" to "сто", "2" to "двести", "3" to "триста",
            "4" to "четыреста", "5" to "пятьсот", "6" to "шестьсот", "7" to "семьсот",
            "8" to "восемьсот", "9" to "девятьсот")
    val decadesMap: Map<String, String> = mapOf("2" to "двадцать", "3" to "тридцать",
            "4" to "сорок", "5" to "пятьдесят", "6" to "шестьдесят", "7" to "семьдесят",
            "8" to "восемьдесят", "9" to "девяносто")
    val tensMap: Map<String, String> = mapOf("10" to "десять", "11" to "одиннадцать", "12" to "двенадцать",
            "13" to "тринадцать", "14" to "четырнадцать", "15" to "пятнадцать", "16" to "шестнадцать",
            "17" to "семнадцать", "18" to "восемнадцать", "19" to "девятнадцать")
    val unitsMap: Map<String, String> = mapOf("1" to "один", "2" to "два", "3" to "три",
            "4" to "четыре", "5" to "пять", "6" to "шесть", "7" to "семь", "8" to "восемь",
            "9" to "девять")
    val thousandUnitsMap: Map<String, String> = mapOf("1" to "одна", "2" to "две", "3" to "три",
            "4" to "четыре", "5" to "пять", "6" to "шесть", "7" to "семь", "8" to "восемь",
            "9" to "девять")
    var rus = ""
    if (n == 0) rus += "ноль"
    if (n >= 1000) {
        val largerPart = n / 1000
        if (largerPart / 100 in 1..9) {
            if (rus.isNotEmpty()) rus += " "
            rus += "${hundredsMap[(largerPart / 100).toString()]}"
        }
        if ((largerPart % 100) / 10 == 1) {
            if (rus.isNotEmpty()) rus += " "
            rus += "${tensMap[(largerPart % 100).toString()]}"
        }
        else {
            if ((largerPart % 100) / 10 in 2..9) {
                if (rus.isNotEmpty()) rus += " "
                rus += "${decadesMap[((largerPart % 100) / 10).toString()]}"
            }
            if (largerPart % 10 in 1..9) {
                if (rus.isNotEmpty()) rus += " "
                rus += "${thousandUnitsMap[(largerPart % 10).toString()]}"
            }
        }
        if (largerPart % 10 == 1) rus += " тысяча"
        else if (largerPart % 10 in 2..4) rus += " тысячи"
        else rus += " тысяч"
    }
    val lessPart = n % 1000
    if (lessPart / 100 in 1..9) {
        if (rus.isNotEmpty()) rus += " "
        rus += "${hundredsMap[(lessPart / 100).toString()]}"
    }
    if ((lessPart % 100) / 10 == 1) {
        if (rus.isNotEmpty()) rus += " "
        rus += "${tensMap[(lessPart % 100).toString()]}"
    }
    else {
        if ((lessPart % 100) / 10 in 2..9) {
            if (rus.isNotEmpty()) rus += " "
            rus += "${decadesMap[((lessPart % 100) / 10).toString()]}"
        }
        if (lessPart % 10 in 1..9) {
            if (rus.isNotEmpty()) rus += " "
            rus += "${unitsMap[(lessPart % 10).toString()]}"
        }
    }
    return rus
}