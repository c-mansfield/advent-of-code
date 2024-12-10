package utils

data class Grid(val coordinates: List<Coordinate>) {
    fun getAdjacentCoords(coordinate: Coordinate, withDiagonals: Boolean = false): List<Coordinate> = mutableListOf<Coordinate>().apply {
            coordinates.find { coordinate.x - 1 == it.x && coordinate.y == it.y }?.let { this.add(it) }
            coordinates.find { coordinate.x + 1 == it.x && coordinate.y == it.y }?.let { this.add(it) }
            coordinates.find { coordinate.x == it.x && coordinate.y - 1 == it.y }?.let { this.add(it) }
            coordinates.find { coordinate.x == it.x && coordinate.y + 1 == it.y }?.let { this.add(it) }

            if (withDiagonals) getDiagonalCoords(coordinate).forEach { this.add(it) }
        }

    fun getDiagonalCoords(coordinate: Coordinate) = mutableListOf<Coordinate>().apply {
        coordinates.find { coordinate.x + 1 == it.x && coordinate.y + 1 == it.y }?.let { this.add(it) }
        coordinates.find { coordinate.x + 1 == it.x && coordinate.y - 1 == it.y }?.let { this.add(it) }
        coordinates.find { coordinate.x - 1 == it.x && coordinate.y - 1 == it.y }?.let { this.add(it) }
        coordinates.find { coordinate.x - 1 == it.x && coordinate.y + 1 == it.y }?.let { this.add(it) }
    }

    fun getGridCoordinate(x: Int, y: Int) = coordinates.find { x == it.x && y == it.y }
}

data class Coordinate(val x: Int, val y: Int, val value: Char)

data class Line(val x1: Int, val x2: Int, val y1: Int, val y2: Int, val value: Int)

fun parseGrid(input: List<String>): Grid = Grid(
    input.mapIndexed() { y, it ->
        it.toCharArray().mapIndexed { x, value ->
            Coordinate(x, y, value)
        }
    }.flatten()
)