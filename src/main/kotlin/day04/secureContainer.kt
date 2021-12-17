package day04

val passwords = (172930..683082)
    .map { it.toString() }

fun main(){
    println(passwords.count{ isValidPassword(it) })
}

fun isValidPassword(password: String): Boolean {
    return password.windowed(2).all{ it.last() >= it.first() }
            && password.any{ num -> password.count{it == num} == 2 }
}

