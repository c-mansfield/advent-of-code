package utils

data class Grid(val coordinates: MutableList<Coordinate>) {
    fun getAdjacentCoords(main: Coordinate, withDiagonals: Boolean): MutableList<Coordinate> {
        return mutableListOf<Coordinate>().apply {
            coordinates.find { main.x - 1 == it.x && main.y == it.y }?.let { this.add(it) }
            coordinates.find { main.x + 1 == it.x && main.y == it.y }?.let { this.add(it) }
            coordinates.find { main.x == it.x && main.y - 1 == it.y }?.let { this.add(it) }
            coordinates.find { main.x == it.x && main.y + 1 == it.y }?.let { this.add(it) }

            if(withDiagonals) {
                coordinates.find { main.x + 1 == it.x && main.y + 1 == it.y }?.let { this.add(it) }
                coordinates.find { main.x + 1 == it.x && main.y - 1 == it.y }?.let { this.add(it) }
                coordinates.find { main.x - 1 == it.x && main.y - 1 == it.y }?.let { this.add(it) }
                coordinates.find { main.x - 1 == it.x && main.y + 1 == it.y }?.let { this.add(it) }
            }
        }
    }

    fun getGridCoordinate(x: Int, y: Int) = coordinates.find { x == it.x && y == it.y }
}

data class Coordinate(val x: Int, val y: Int, var value: Int)

fun parseGrid(input: List<String>): Grid {
    return Grid(input.mapIndexed() { y, it ->
        it.toCharArray().mapIndexed { x, value ->
            Coordinate(x,input.size-1-y,value.toString().toInt())
        }
    }.flatten().toMutableList())
}