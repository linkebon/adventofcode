package day3_partB

import java.io.File

/*
What is the Manhattan distance from the central port to the closest intersection?
https://adventofcode.com/2019/day/3
 */

val centralPort = Pair(2, 2)
val wireA = File("src/day3_partB/input_wireA.txt").readText().split(",")
val wireB = File("src/day3_partB/input_wireB.txt").readText().split(",")

fun main() {
    val intersections = findIntersections(
        calculateWiresAllVisitedCoordinates(wireA),
        calculateWiresAllVisitedCoordinates(wireB)
    )

    println("Intersection with lowest steps: ${findLowestNumberOfStepsToReachIntersection(intersections)}")

}

fun findIntersections(
    coordinatesWireA: List<Pair<Int, Int>>,
    coordinatesWireB: List<Pair<Int, Int>>
): Set<Pair<Int, Int>> {
    return coordinatesWireA.intersect(coordinatesWireB)
}

fun findLowestNumberOfStepsToReachIntersection(intersections: Set<Pair<Int, Int>>): Int {
    val lowestStepsInterSectionCoordinate: Pair<Int, Int> =
        intersections
            .reduce { first, second ->

                val firstStepsForWireA = calculateStepsUntilIntersection(wireA, first)
                val firstStepsForWireB = calculateStepsUntilIntersection(wireB, first)

                val secondStepsForWireA = calculateStepsUntilIntersection(wireA, second)
                val secondStepsForWireB = calculateStepsUntilIntersection(wireB, second)

                if (firstStepsForWireA + firstStepsForWireB < secondStepsForWireA + secondStepsForWireB)
                    first
                else
                    second
            }

    return calculateStepsUntilIntersection(wireA, lowestStepsInterSectionCoordinate) +
            calculateStepsUntilIntersection(wireB, lowestStepsInterSectionCoordinate)
}

fun calculateStepsUntilIntersection(path: List<String>, destination: Pair<Int, Int>): Int {
    return calculateWiresAllVisitedCoordinates(path).indexOf(destination) + 1
}

fun calculateWiresAllVisitedCoordinates(path: List<String>): List<Pair<Int, Int>> {
    val coordinates = mutableListOf<Pair<Int, Int>>()
    var latestCoordinate = centralPort

    path.forEach { direction ->
        val visited = calculateVisitedCoordinatesForDirection(latestCoordinate, direction)
        latestCoordinate = visited.last()
        coordinates.addAll(visited)
    }
    return coordinates
}

// current is after function list.last()
fun calculateVisitedCoordinatesForDirection(current: Pair<Int, Int>, direction: String): List<Pair<Int, Int>> {
    val steps = direction.drop(1).toInt()
    val visitedCoordinates = mutableListOf<Pair<Int, Int>>()
    when (direction.first()) {
        'R' -> {
            IntRange(1, steps).forEach { i ->
                visitedCoordinates.add(Pair(current.first + i, current.second))
            }
            return visitedCoordinates
        }
        'L' -> {
            IntRange(1, steps).forEach { i ->
                visitedCoordinates.add(Pair(current.first - i, current.second))
            }
            return visitedCoordinates
        }
        'U' -> {
            IntRange(1, steps).forEach { i ->
                visitedCoordinates.add(Pair(current.first, current.second + i))
            }
            return visitedCoordinates
        }
        'D' -> {
            IntRange(1, steps).forEach { i ->
                visitedCoordinates.add(Pair(current.first, current.second - i))
            }
            return visitedCoordinates
        }
        else -> throw Exception("invalid direction")
    }
}
