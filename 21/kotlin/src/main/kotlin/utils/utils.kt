package utils

import java.io.File

fun readFileByLine(filePath: String): List<String> {
    return File(filePath).readLines()
}