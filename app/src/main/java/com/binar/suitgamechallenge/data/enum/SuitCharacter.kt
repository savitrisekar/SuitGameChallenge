package com.binar.suitgamechallenge.data.enum

enum class SuitCharacter(val value: Int) {
    IDLE(-1),
    ROCK(0),
    PAPER(1),
    SCISSOR(2);

    companion object {
        fun getByValue(value: Int) = values().first { it.value == value }
    }
}