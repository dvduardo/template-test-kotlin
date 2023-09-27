package utils

import java.io.File

object FileProperties {
    private val path = "src/test/resources/"
    fun createFile(fileName: String, data: String) {
        val file = File("$path$fileName.txt")
        file.writeText(data)
    }

    fun readFile(fileName: String): String {
        return File("$path$fileName.txt").inputStream().readBytes().toString(Charsets.UTF_8)
    }
}