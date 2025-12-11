fun main() {
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
        val inventory = parseInput(input)

        val sortedRange = inventory.ranges.sortedBy { it.first }
        val mergedRanges = mutableListOf<LongRange>()
        var currentMergedRange = sortedRange[0]

        for (i in 1 until sortedRange.size) {
            val nextRange = sortedRange[i]
            if(currentMergedRange.last >= nextRange.first - 1) {
                currentMergedRange = currentMergedRange.first..maxOf(currentMergedRange.last, nextRange.last)
            } else {
                // We have a new range
                mergedRanges.add(currentMergedRange)
                currentMergedRange = nextRange
            }
        }

        if(!currentMergedRange.isEmpty()) {
            mergedRanges.add(currentMergedRange)
        }

        val totalFreshIngredients = mergedRanges
            .map{ (it.last+1) - it.first }
            .reduce { acc, next -> acc + next }

        return totalFreshIngredients
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3L)
    check(part2(testInput) == 14L)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class FoodInventory(val ranges: List<LongRange>, val ingredients: List<Long>)


fun <T> List<T>.chunkedBy(selector:(T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { accumulator, item ->
        if(selector(item)) {
            accumulator.add(mutableListOf())
        } else {
            accumulator.last().add(item)
        }
        accumulator
    }