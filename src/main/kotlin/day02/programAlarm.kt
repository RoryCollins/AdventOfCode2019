package day02

import java.io.File
import java.lang.IllegalArgumentException

val instructions = File("src/main/kotlin/day02/input.txt")
    .readText()
    .split(",")
    .map { it.toInt() }

fun parseInstruction(index: Int, instructions: List<Int>): List<Int> {
    if (instructions[index] == 99) return instructions

    val (opcode, input1, input2, targetIndex) = instructions.slice(index until index + 4)

    val result = when (opcode) {
        1 -> instructions[input1] + instructions[input2]
        2 -> instructions[input1] * instructions[input2]
        else -> throw IllegalArgumentException()
    }

    val newInstructions =
        instructions.slice(0 until targetIndex) + result + instructions.slice(targetIndex + 1 until instructions.size)

    return parseInstruction(index + 4, newInstructions)
}


fun main() {
    println(parseInstruction(0, instructions)[0])
    (0 until 100).map { first ->
        (0 until 100).map { second ->
            val testInstructions =
                listOf<Int>(instructions[0], first, second) + instructions.slice(3 until instructions.size)
            if (parseInstruction(0, testInstructions)[0] == 19690720) {
                println("$first$second")
                return
            }
        }
    }
}