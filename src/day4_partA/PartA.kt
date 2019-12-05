package day4_partA

val input = IntRange(136760, 595730)
fun main() {
    println("Number of password meeting criteria: ${countPasswordsMeetingCriteria(input)}")
}

fun countPasswordsMeetingCriteria(range: IntRange): Int {
    return range.filter { testPassword(it.toString()) }.count()
}

fun testPassword(password: String): Boolean {
    return digitsNeverDecrease(password) && hasTwoAdjacentNumbers(password)
}

fun digitsNeverDecrease(password: String): Boolean {
    var passed = true
    for (index in 1 until password.length) {
        if(password[index] < password[index - 1])
            passed = false
    }
    return passed
}

fun hasTwoAdjacentNumbers(password: String): Boolean {
    var passed = false
    for (index in 1 until password.length) {
        if(password[index] == password[index - 1])
            passed = true
    }
    return passed
}
