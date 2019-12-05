package day4_partB

val input = IntRange(136760, 595730)
fun main() {
    println("Number of password meeting criteria: ${countPasswordsMeetingCriteria(input)}")
}

fun countPasswordsMeetingCriteria(range: IntRange): Int {
    return range
        .filter { testPassword(it.toString()) }
        .count()
}

fun testPassword(password: String): Boolean {
    return digitsNeverDecrease(password) && hasMaxTwoAdjacentNumbers(password)
}

fun digitsNeverDecrease(password: String): Boolean {
    var passed = true
    for (index in 1 until password.length) {
        if (password[index].toInt() < password[index - 1].toInt())
            passed = false
    }
    return passed
}

fun hasMaxTwoAdjacentNumbers(password: String): Boolean {
    val holderList = mutableListOf<MutableList<Int>>()
    var tmpList = mutableListOf<Int>()
    var addedLast = false
    for (index in password.indices) {
        when {
            tmpList.isEmpty() -> {
                tmpList.add(password[index].toString().toInt())
                addedLast = true
            }
            tmpList.first() == password[index].toString().toInt() -> {
                tmpList.add((password[index].toString().toInt()))
                addedLast = true
            }
            else -> {
                holderList.add(tmpList)
                tmpList = mutableListOf()
                tmpList.add(password[index].toString().toInt())
                addedLast = false
            }

        }
    }
    if (addedLast) {
        holderList.add(tmpList)
    }

    return holderList.any { it.size == 2 }
}
