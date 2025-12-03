enum class Direction {
    Left,
    Right
}

data class Rotation(val direction: Direction, val amount: Int)

fun main() {
    val START_DIAL = 50
    val MIN_DIAL = 0
    val MAX_DIAL = 100


    fun part1(input: List<String>): Int {
        var dialPosition = START_DIAL
        var numberOfZeroes = 0
        input.forEach {
            val rotation = Rotation( if(it[0] == 'L') Direction.Left else Direction.Right, it.drop(1).toInt())

            when(rotation.direction) {
                Direction.Left -> {
                    dialPosition -= (rotation.amount % MAX_DIAL)
                    if (dialPosition < MIN_DIAL) dialPosition += MAX_DIAL
                }

                Direction.Right -> {
                    dialPosition += (rotation.amount % MAX_DIAL)
                    if (dialPosition >= MAX_DIAL) dialPosition -= MAX_DIAL
                }
            }

            if(dialPosition == MIN_DIAL) {
                numberOfZeroes++
            }
        }

        return numberOfZeroes
    }

    fun part2(input: List<String>): Int {
        var dialPosition = START_DIAL
        var numberOfZeroes = 0
        input.forEach {
            val rotation = Rotation( if(it[0] == 'L') Direction.Left else Direction.Right, it.drop(1).toInt())

            val fullLoops = rotation.amount / MAX_DIAL
            val remainingDistance = rotation.amount % MAX_DIAL
            numberOfZeroes += fullLoops

            if(remainingDistance > MIN_DIAL) {
                when(rotation.direction) {
                    Direction.Left -> {
                        val previousDialPosition = dialPosition
                        dialPosition -= (remainingDistance)
                        if (dialPosition <= MIN_DIAL) {
                            if(previousDialPosition != MIN_DIAL) {
                                numberOfZeroes++
                            }
                            if(dialPosition < MIN_DIAL) {
                                dialPosition += MAX_DIAL
                            }
                        }
                    }

                    Direction.Right -> {
                        dialPosition += remainingDistance
                        if(dialPosition >= MAX_DIAL) {
                            dialPosition -= MAX_DIAL
                            numberOfZeroes++
                        }
                    }
                }
            }
        }

        return numberOfZeroes
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82")) == 3)

    // Or read a large test input from the `src/Day01.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
