package utils

import java.io.File

object FileProperties {
    private const val PATH = "src/test/resources/"
    fun createFile(fileName: String, data: String) {
        val file = File("$PATH$fileName.txt")
        file.writeText(data)
    }

    fun readFile(fileName: String): String {
        return File("$PATH$fileName.txt").inputStream().readBytes().toString(Charsets.UTF_8)
    }
}