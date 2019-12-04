package day3

import kotlin.math.abs

/*
o (2,2)
x (5,5)
|5 - 2| + |5 - 2| = |3| + |3| = 6
 */
fun main() {
    val a = PartA()
    val coordinatesA = a.calculateWiresAllTraversedCoordinates(a.wireA);
    val coordinatesB = a.calculateWiresAllTraversedCoordinates(a.wireB);
    println("Closest distance: ${a.findClosestDistance(coordinatesA, coordinatesB)}")
}

class PartA {
    val centralPort = Pair(2, 2)
    /*val wireA = mutableListOf("R8", "U5", "L5", "D3")
    val wireB = mutableListOf("U7", "R6", "D4", "L4")*/
    /*val wireA = mutableListOf("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72")
    val wireB = mutableListOf("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")*/
    val wireA = mutableListOf("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51")
    val wireB = mutableListOf("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7")

    fun findClosestDistance(coordinatesWireA: List<Pair<Int, Int>>, coordinatesWireB: List<Pair<Int, Int>>): Int? {
        val intersections = coordinatesWireA.intersect(coordinatesWireB)

        return intersections.map {
            abs(it.first - centralPort.first) + abs(it.second - centralPort.second)
        }.min()
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
}