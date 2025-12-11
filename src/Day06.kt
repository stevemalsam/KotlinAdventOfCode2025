fun main() {
    fun parseInput(input: List<String>): List<MathColumn> {
        var numberColumns = mutableListOf<MutableList<String>>()
        var operationColumns = mutableListOf<MathColumn>()

        for(line in input) {
            var parsedLine = line.trim().split("\\s+".toRegex())
            numberColumns.add(parsedLine as MutableList<String>)
        }

        for(i in 0 until numberColumns[0].size) {
            val problemNumbers = mutableListOf<Long>()
            var operand: Operation = Operation.ADDITION
            for(j in 0 until numberColumns.size) {
                when(val character = numberColumns[j][i]) {
                    "*","+" -> operand = Operation.fromString(numberColumns[j][i])
                    else -> {
                        character.toLongOrNull()?.let {
                            problemNumbers.add(it)
                        }
                    }
                }
            }

            operationColumns.add(MathColumn(problemNumbers, operand))
        }

        return operationColumns
    }

    fun part1(input: List<String>): Long {
        val problems = parseInput(input)
        var resultSum = 0L

        for(problem in problems) {
            val solution = problem.numbers.reduce{ acc, next ->
                when(problem.operator) {
                    Operation.ADDITION -> acc + next
                    Operation.MULTIPLICATION -> acc * next
                }
            }
            resultSum += solution
        }

        return resultSum
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)

    val input = readInput("Day06")
    println(part1(input))
//    println(part2(input))
}

enum class Operation {
    ADDITION,
    MULTIPLICATION;

    companion object {
        fun fromString(operator: String): Operation {
            return when(operator) {
                "+" -> ADDITION
                "*" -> MULTIPLICATION
                else -> throw IllegalArgumentException("Unsupported operator")
            }
        }
    }
}

data class MathColumn(val numbers: List<Long>, val operator: Operation)