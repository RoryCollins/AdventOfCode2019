package day03

import java.io.File
import kotlin.math.abs

private val wires = File("src/main/kotlin/day03/input.txt")
    .readLines()
    .map { it.split(",") }

data class Point(val X: Int, val Y: Int)

private fun parseInstruction(instruction: String, history: List<Point>): List<Point> {
    val origin = history.last()
    val direction = instruction.first()
    val distance = instruction.drop(1).toInt()

    return history + (1..distance).map {
        when (direction) {
            'R' -> Point(origin.X + it, origin.Y)
            'L' -> Point(origin.X - it, origin.Y)
            'U' -> Point(origin.X, origin.Y + it)
            else -> Point(origin.X, origin.Y - it)
        }
    }
}

private fun doIt(instructions: List<String>): List<Point> {
    val origin = Point(0,0)
    var current = origin
    val points = mutableListOf<Point>()
    for (instruction in instructions){
        val direction = instruction.first()
        val steps = instruction.drop(1).toInt()
        for (i in (1..steps)) {
            val next = when (direction) {
                'R' -> Point(current.X + 1, current.Y)
                'L' -> Point(current.X - 1, current.Y)
                'U' -> Point(current.X, current.Y + 1)
                'D' -> Point(current.X, current.Y - 1)
                else -> throw IllegalArgumentException()
            }
            points.add(next)
            current = next
        }
    }
    return points
}

fun main() {
    val points =wires.map{doIt(it)}
    val result = points[0].intersect(points[1].toSet())

    println("Part One: ${result.minOf { abs(it.X) + abs(it.Y) }}")
    println("Part Two: ${result.minOf { points[0].indexOf(it) + points[1].indexOf(it) + 2 }}")
}