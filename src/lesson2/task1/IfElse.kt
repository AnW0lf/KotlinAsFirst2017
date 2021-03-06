@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import org.jetbrains.annotations.TestOnly

fun main(args: Array<String>) {
    println(timeForHalfWay(1.0, 4.0, 1.0, 1.0, 1.0, 1.0))
    println(timeForHalfWay(1.0, 1.0, 1.0, 2.0, 1.0, 1.0))
    println(timeForHalfWay(1.0, 1.0, 1.0, 1.0, 1.0, 4.0))
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if (age in 1..199) {
        return when {
            age % 10 == 0 || age % 10 in 5..9 || age % 100 in 11..19 -> "$age лет"
            age % 10 == 1 -> "$age год"
            else -> "$age года"
        }
    }
    return "age is out of range"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = s1 + s2 + s3
    return when {
        s / 2 <= s1 -> s / 2 / v1
        (s / 2 - s1) <= s2 -> t1 + (s / 2 - s1) / v2
        else -> t1 + t2 + (s / 2 - (s1 + s2)) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int = when {
    (rookX2 == kingX || rookY2 == kingY) && (rookX1 == kingX || rookY1 == kingY) -> 3
    (rookX1 == kingX || rookY1 == kingY) -> 1
    (rookX2 == kingX || rookY2 == kingY) -> 2
    else -> 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int = when {
    (Math.abs(bishopX - kingX) == Math.abs(bishopY - kingY)) && (rookX == kingX || rookY == kingY) -> 3
    (Math.abs(bishopX - kingX) == Math.abs(bishopY - kingY)) -> 2
    (rookX == kingX || rookY == kingY) -> 1
    else -> 0
}

fun sqr(x: Double): Double = x * x

fun sqr(x: Int): Int = x * x

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val maxEdge: Double = Math.max(Math.max(a, b), c)
    val minEdge: Double = Math.min(Math.min(a, b), c)
    val middleEdge = a + b + c - minEdge - maxEdge
    return when {
        maxEdge > minEdge + middleEdge -> -1
        sqr(minEdge) + sqr(middleEdge) > sqr(maxEdge) -> 0
        sqr(minEdge) + sqr(middleEdge) == sqr(maxEdge) -> 1
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    c in a..b && d >= b -> b - c
    a in c..d && b >= d -> d - a
    a in c..d && b in c..d -> b - a
    c in a..b && d in a..b -> d - c
    else -> -1
}
