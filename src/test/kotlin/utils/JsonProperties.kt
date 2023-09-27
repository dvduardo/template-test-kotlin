package utils

import org.json.JSONObject
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object JsonProperties {
    private const val path_file = "./src/test/resources/payload/"
    private lateinit var objeto: JSONObject

    fun generatePayload(path:String): JSONObject{
        return readJsonFile(Paths.get("$path_file$path.json"))
    }
    private fun readJsonFile(path: Path): JSONObject {
        val contentBuilder = StringBuilder()
        val lines = Files.readAllLines(path)
        for (line in lines)
            contentBuilder.append(line)
        objeto = JSONObject(contentBuilder.toString())
        return objeto
    }
}