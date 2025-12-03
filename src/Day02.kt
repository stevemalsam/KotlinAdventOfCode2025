fun main() {
    fun parseInput(input: String): List<Pair<Long, Long>> {
        var output = mutableListOf<Pair<Long, Long>>()
        val values = input.split(",", "-")
        for(i in values.indices step 2) {
            val firstElement = values[i].toLong()
            val secondElement = values[i + 1].toLong()
            output.add(Pair(firstElement, secondElement))
        }

        return output.toList()
    }

    fun part1(input: List<String>): Long {
        val ranges = parseInput(input[0])
        var invalidIDTotal: Long = 0

        for(range in ranges) {
            for(i in range.first .. range.second) {
                val stringID = i.toString()
                if((stringID.length %2) != 0) {
                    continue
                }

                val firstHalf = stringID.take(stringID.length/2)
                val secondHalf = stringID.takeLast(stringID.length/2)
                if (firstHalf == secondHalf) {
                    invalidIDTotal += i
                }
            }
        }

        return invalidIDTotal
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)

    val input = readInput("Day02")
    println(part1(input))
//    println(part2(input))
}