@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson2.task1.sqr
import java.lang.Math


fun main(args: Array<String>) {

}

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int = if (n in -9..9) 1 else 1 + digitNumber(n / 10)

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var x = 1
    var y = 1
    for (i in 3..n) {
        if (i % 2 == 0) x += y
        if (i % 2 == 1) y += x
    }
    return Math.max(x, y)
} //if (n > 2) fib(n - 1) + fib(n - 2) else 1

fun gcd(m: Int, n: Int): Int {
    var minDivX = minDivisor(m)
    var minDivY = minDivisor(n)
    var x = m / minDivX
    var y = n / minDivY
    var max = 1
    do {
        when {
            minDivX > minDivY -> {
                minDivY = minDivisor(y)
                y /= minDivY
            }
            minDivX < minDivY -> {
                minDivX = minDivisor(x)
                x /= minDivX
            }
            else -> {
                max = minDivX
                minDivY = minDivisor(y)
                y /= minDivY
                minDivX = minDivisor(x)
                x /= minDivX
            }
        }
    } while (!isCoPrime(x, y))
    return max
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = Math.abs(m * n) / gcd(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) if (n % i == 0) return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var num_1 = m
    while (num_1 > 1) {
        val minDivisor_1 = minDivisor(num_1)
        var num_2 = n
        while (num_2 > 1) {
            val minDivisor_2 = minDivisor(num_2)
            num_2 /= minDivisor_2
            if (minDivisor_1 == minDivisor_2) return false
            if (minDivisor_1 < minDivisor_2) break
        }
        num_1 /= minDivisor_1
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = sqr(Math.sqrt(n.toDouble()).toInt()) >= m


fun pow(num: Double, power: Int): Double {
    if (power == 0) return 1.0
    var x = num
    for (i in 1 until power)
        x *= num
    return x
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var number = x
    while (number >= 2 * Math.PI)
        number -= 2 * Math.PI
    var t: Double
    var sum = 0.0
    var k = 0
    do {
        t = pow(-1.0, k) * pow(number, 2 * k + 1) / factorial(2 * k + 1)
        sum += t
        k++
    } while (Math.abs(t) > eps)
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var number = x
    while (number >= 2 * Math.PI)
        number -= 2 * Math.PI
    var t: Double
    var sum = 0.0
    var k = 0
    do {
        t = pow(-1.0, k) * pow(number, 2 * k) / factorial(2 * k)
        sum += t
        k++
    } while (Math.abs(t) > eps)
    return sum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var num = n
    var result = 0
    while (num > 0) {
        result = (result * 10) + (num % 10)
        num /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    return if (n > 99 && n % 10 == n.toString()[0].toString().toInt())
        isPalindrome(n.toString().removeRange(0, 1).toInt() / 10)
    else if (n in 10..99 && n % 11 == 0) true
    else n in 0..9
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    while (num > 9) {
        if (num % 10 != num % 100 / 10) return true
        else num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var str = ""
    while (str.length <= n) {
        str += sqr(i).toString()
        i++
    }
    return str[n - 1].toString().toInt()
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var row = ""
    var i = 1
    while (row.length <= n) {
        row += fib(i).toString()
        i++
    }
    return row[n - 1].toString().toInt()
}
