package aoc2021

object Math {
    fun List<Int>.median(): Double =
        if (this.size % 2 == 0)
            (this.sorted()[this.size/2] + this.sorted()[this.size/2 - 1]) / 2.toDouble()
        else
            this.sorted()[this.size/2].toDouble()
}
