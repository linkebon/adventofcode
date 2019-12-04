package day3_partA

import java.io.File
import kotlin.math.abs

/*
https://adventofcode.com/2019/day/3
 */

/*
o (2,2)
x (5,5)
|5 - 2| + |5 - 2| = |3| + |3| = 6
 */
fun main() {
    println("Closest distance: ${findClosestDistance(calculateWiresAllTraversedCoordinates(wireA), calculateWiresAllTraversedCoordinates(wireB))}")
}

val centralPort = Pair(2, 2)
val wireA = File("src/day3_partA/input_wireA.txt").readText().split(",")
val wireB = File("src/day3_partA/input_wireB.txt").readText().split(",")

fun findClosestDistance(coordinatesWireA: List<Pair<Int, Int>>, coordinatesWireB: List<Pair<Int, Int>>): Int? {
    return coordinatesWireA.intersect(coordinatesWireB)
        .map { abs(it.first - centralPort.first) + abs(it.second - centralPort.second) }
        .min()
}

fun calculateWiresAllTraversedCoordinates(path: List<String>): List<Pair<Int, Int>> {
    val coordinates = mutableListOf<Pair<Int, Int>>()
    var latestCoordinate = centralPort

    path.forEach { direction ->
        val traversed = calculateTraversedCoordinatesForDirection(latestCoordinate, direction)
        latestCoordinate = traversed.last()
        coordinates.addAll(traversed)
    }
    return coordinates
}

// current is after function list.last()
private fun calculateTraversedCoordinatesForDirection(
    current: Pair<Int, Int>,
    direction: String
): List<Pair<Int, Int>> {
    val steps = direction.drop(1).toInt()
    val traversedCoordinates = mutableListOf<Pair<Int, Int>>()
    when (direction.first()) {
        'R' -> {
            IntRange(1, steps).forEach { i ->
                traversedCoordinates.add(Pair(current.first + i, current.second))
            }
            return traversedCoordinates
        }
        'L' -> {
            IntRange(1, steps).forEach { i ->
                traversedCoordinates.add(Pair(current.first - i, current.second))
            }
            return traversedCoordinates
        }
        'U' -> {
            IntRange(1, steps).forEach { i ->
                traversedCoordinates.add(Pair(current.first, current.second + i))
            }
            return traversedCoordinates
        }
        'D' -> {
            IntRange(1, steps).forEach { i ->
                traversedCoordinates.add(Pair(current.first, current.second - i))
            }
            return traversedCoordinates
        }
        else -> throw Exception("invalid direction")
    }
}
