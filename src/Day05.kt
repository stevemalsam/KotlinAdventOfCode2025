fun main() {
    fun part1(input: List<String>): Long {
        val inventory = parseInput(input)
        var freshIngredients = 0L

        for(ingredient in inventory.ingredients) {
            for(range in inventory.ranges) {
                if(ingredient in range) {
                    freshIngredients += 1
                    break
                }
            }
        }

        return freshIngredients
    }

    fun part2(input: List<String>): Long {
        return 0
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3L)

    val input = readInput("Day05")
    println(part1(input))
//    println(part2(input))
}

data class FoodInventory(val ranges: List<LongRange>, val ingredients: List<Long>)

fun parseInput(input: List<String>): FoodInventory {
    val (rawRanges, ids) = input.chunkedBy { it.isBlank() }
    val ranges = rawRanges.map { line ->
        val (a, b) = line.split("-")
            .map { it.toLong() }
        a..b
    }

    val index = ids.map { it.toLong() }
    return FoodInventory(ranges, index)
}

fun <T> List<T>.chunkedBy(selector:(T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { accumulator, item ->
        if(selector(item)) {
            accumulator.add(mutableListOf())
        } else {
            accumulator.last().add(item)
        }
        accumulator
    }