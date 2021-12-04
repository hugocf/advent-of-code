package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle03.LifeSupport
import aoc2021.Puzzle03.PowerConsumption
import aoc2021.Puzzle03.calculateLifeSupport
import aoc2021.Puzzle03.calculatePowerConsumption
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Puzzle03Tests {
    private val exampleReport = listOf(
        "00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"
    )
    private val answerReport = readLinesFromResource("2021/puzzle03.txt")

    @Nested
    inner class CalculatePowerConsumption {
        @Test
        fun `calculate rates`() {
            val report = listOf("00111", "11100", "00000")
            assertEquals(PowerConsumption("00100".toInt(2), "11011".toInt(2)), calculatePowerConsumption(report))
        }

        @Test
        fun `calculate example`() {
            val result = calculatePowerConsumption(exampleReport)
            assertEquals(198, result.gammaRate * result.epsionRate)
        }

        @Test
        fun `calculate answer`() {
            val result = calculatePowerConsumption(answerReport)
            assertEquals(2583164, result.gammaRate * result.epsionRate)
        }
    }

    @Nested
    inner class CalculateLifeSupport {
        @Test
        fun `calculate life support rates with different frequencies`() {
            val report = listOf("00111", "11100", "00000")
            assertEquals(LifeSupport("00111".toInt(2), "11100".toInt(2)), calculateLifeSupport(report))
        }

        @Test
        fun `calculate life support rates with the same frequency`() {
            val report = listOf("01000", "01111")
            assertEquals(LifeSupport("01111".toInt(2), "01000".toInt(2)), calculateLifeSupport(report))
        }

        @Test
        fun `calculate life support example`() {
            val result = calculateLifeSupport(exampleReport)
            assertEquals(230, result.oxygenGeneratorRating * result.co2ScrubberRating)
        }

        @Test
        fun `calculate life support answer`() {
            val result = calculateLifeSupport(answerReport)
            assertEquals(2784375, result.oxygenGeneratorRating * result.co2ScrubberRating)
        }
    }
}
