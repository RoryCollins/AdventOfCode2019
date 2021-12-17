package day05

import java.io.File

fun main() {
    val instructions = File("src/main/kotlin/day05/input.txt")
        .readText()
    solve(instructions)
}

fun solve(input: String) {
    val instructions = input
        .split(",")
        .map { it.toInt() }
    println("Part One:")
    parse(1, instructions, 0)
    print("Part Two: ")
    parse(5, instructions, 0)
}


fun parse(input: Int, instructions: List<Int>, index: Int) {
    val rest = instructions.toMutableList()
    var nextIndex: Int

    val instruction = instructions[index].toString().padStart(5, '0')
    when (instruction.slice(3..4).toInt()) {
        1 -> {
            val firstParameter = getParameter(instructions, instruction[2], index+1)
            val secondParameter = getParameter(instructions, instruction[1], index+2)
            val thirdParameter = instructions[index + 3]

            rest[thirdParameter] = firstParameter + secondParameter
            parse(input, rest, index+4)
        }
        2 -> {
            val firstParameter = getParameter(instructions, instruction[2], index+1)
            val secondParameter = getParameter(instructions, instruction[1], index+2)
            val thirdParameter = instructions[index + 3]

            rest[thirdParameter] = firstParameter * secondParameter
            parse(input, rest, index+4)
        }
        3 -> {
            val firstParameter = instructions[index + 1]
            rest[firstParameter] = input
            parse(input, rest, index+2)
        }
        4 -> {
            val firstParameter = instructions[index + 1]
            println(rest[firstParameter])
            parse(input, rest, index+2)
        }
        5 -> {
            val firstParameter = getParameter(instructions, instruction[2], index+1)
            val secondParameter = getParameter(instructions, instruction[1], index+2)
            val newIndex = if (firstParameter == 0) index+3 else secondParameter
            parse(input,rest, newIndex)
        }
        6 -> {
            val firstParameter = getParameter(instructions, instruction[2], index+1)
            val secondParameter = getParameter(instructions, instruction[1], index+2)
            val newIndex = if (firstParameter == 0) secondParameter else index+3
            parse(input,rest, newIndex)
        }
        7 -> {
            val firstParameter = getParameter(instructions, instruction[2], index+1)
            val secondParameter = getParameter(instructions, instruction[1], index+2)
            val thirdParameter = instructions[index + 3]

            rest[thirdParameter] = if(firstParameter < secondParameter) 1 else 0
            parse(input, rest, index+4)
        }
        8 -> {
            val firstParameter = getParameter(instructions, instruction[2], index+1)
            val secondParameter = getParameter(instructions, instruction[1], index+2)
            val thirdParameter = instructions[index + 3]

            rest[thirdParameter] = if(firstParameter == secondParameter) 1 else 0
            parse(input, rest, index+4)
        }
        99 -> {
            return
        }
        else -> throw IllegalArgumentException()
    }
}

fun getParameter(instructions: List<Int>, type: Char, index: Int): Int{
    return if (type == '1') instructions[index]
    else instructions[instructions[index]]
}