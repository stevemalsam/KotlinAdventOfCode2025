fun main() {
    fun part1(input: List<String>): Int {
        var outputVoltage = 0

        for(batteryBank in input) {
            var leftIndex = 0
            var rightIndex = 1
            var largestCharge = 0

            while(leftIndex != batteryBank.length - 1) {
                while(rightIndex != batteryBank.length) {
                    val potentialBattery = "${batteryBank[leftIndex]}${batteryBank[rightIndex]}".toInt()

                    if(potentialBattery > largestCharge) {
                        largestCharge = potentialBattery
                    }

                    rightIndex++
                }

                leftIndex++
                rightIndex = leftIndex + 1
            }

            outputVoltage += largestCharge
        }

        return outputVoltage
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}