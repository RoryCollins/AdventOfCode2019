package day01

import java.io.File

val moduleMasses = File("src/main/kotlin/day01/input.txt")
    .readLines()
    .map{it.toInt()}

fun getFuelRequirement(mass:Int): Int{
    val fuelRequired = (mass / 3) - 2
    return when {
        fuelRequired <= 0 -> 0
        else -> fuelRequired + getFuelRequirement(fuelRequired)
    }
}

fun main(){
    print(moduleMasses.sumOf { getFuelRequirement(it) })
}