fun main() {
    fun part1(input: List<String>): Int {
        var movableRolls = 0

        for ((yIndex, y) in input.withIndex()) {
            for ((xIndex, x) in y.withIndex()) {
                if(x != '@') {
                    continue
                }
                val surroundingRolls = checkSurroundingsInGrid(input, xIndex, yIndex)
                if(surroundingRolls < 4) {
                    movableRolls += 1
                }
            }
        }

        return movableRolls
    }

    fun part2(input: List<String>): Int {
        var movableRolls = 0
        var grid = input.toMutableList()

        while(true) {
            var rollsToRemove = mutableListOf<Pair<Int, Int>>()

            for( (yIndex, y) in grid.withIndex()) {
                for( (xIndex, x) in grid[yIndex].withIndex()) {
                    if(x == '@') {
                        val surroundingRolls = checkSurroundingsInGrid(grid, xIndex, yIndex)
                        if(surroundingRolls < 4) {
                            rollsToRemove.add(Pair(xIndex, yIndex))
                        }
                    }
                }
            }

            movableRolls += rollsToRemove.count()
            if(rollsToRemove.isEmpty()) {
                break
            } else {
                for(roll in rollsToRemove) {
                    var string = grid[roll.second].toCharArray()
                    string[roll.first] = '.'
                    grid[roll.second] = String(string)
                }
            }
        }

        return movableRolls
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun checkSurroundingsInGrid(grid: List<String>, xIndex: Int, yIndex: Int): Int {
    var numberOfSurroundingRolls = 0

    val neighbors = findNeighbors(xIndex, yIndex, grid.count(), grid[0].count())

    for(neighbor in neighbors) {
        if(grid[neighbor.second][neighbor.first] == '@') {
            numberOfSurroundingRolls += 1
        }
    }

    return numberOfSurroundingRolls
}

fun findNeighbors(xIndex: Int, yIndex: Int, xMax: Int, yMax: Int): MutableList<Pair<Int, Int>> {
    var neighbors = mutableListOf<Pair<Int, Int>>()

    val cardinalOffsets = listOf(Offset(-1, 0),
        Offset(1, 0),
        Offset(0, -1),
        Offset(0, 1))

    val diagonalOffsets = listOf(Offset(-1, -1),
        Offset(-1, 1),
        Offset(1, -1),
        Offset(1, 1))

    val allOffsets = cardinalOffsets.toMutableList()
    allOffsets += diagonalOffsets

    for (offset in allOffsets) {
        val neighborX = xIndex + offset.dx
        val neighborY = yIndex + offset.dy

        if( neighborX in 0..<xMax && neighborY in 0 ..<yMax) {
            neighbors += Pair(neighborX, neighborY)
        }
    }

    return neighbors
}

data class Offset(val dx: Int, val dy: Int)