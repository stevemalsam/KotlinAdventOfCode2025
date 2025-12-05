fun main() {
    fun part1(input: List<String>): Int {
        var outputVoltage = 0

        for(batteryBank in input) {
            var leftIndex = 0
            var rightIndex = 1
            var largestCharge = 0

            while(leftIndex < batteryBank.lastIndex) {
                while(rightIndex <= batteryBank.lastIndex) {
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



    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

data class DigitAtIndex(val digit: Char, val index: Int)

// Each line has 100 digits in it
fun part2(input: List<String>): Long {
    var outputJoltage: Long = 0
    for(line in input) {
        var lastIndex = 0
        var numberString = ""
        for(i in 12 downTo 1) {
            val biggestDigit = findLargestDigitWithAtLeastNDigitsRemaining(line, lastIndex, i-1)
            numberString += biggestDigit.digit
            lastIndex = biggestDigit.index + 1
        }

        outputJoltage += numberString.toLong()
    }
    return outputJoltage
}

fun findLargestDigitWithAtLeastNDigitsRemaining(digits: String, startIndex: Int, charactersRemaining: Int): DigitAtIndex {
    var largestDigit = 0
    var index = 0

    for(i in startIndex .. (digits.lastIndex - charactersRemaining) ) {
        if(digits[i].digitToInt() > largestDigit) {
            largestDigit = digits[i].digitToInt()
            index = i
        }
    }

    return DigitAtIndex(digits[index], index)
}