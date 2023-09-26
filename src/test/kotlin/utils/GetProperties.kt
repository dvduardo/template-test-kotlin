package utils

import utils.Environments.environments
import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

class GetProperties {
    private val path = "src/test/resources/"
    private var properties: Properties? = null

    fun getProp(valor: String?): String {
        try {
            if (properties.isNullOrEmpty()) {
                properties = Properties()
                properties!!.load(FileInputStream("$path$environments.properties"))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return properties!!.getProperty(valor)
    }
}