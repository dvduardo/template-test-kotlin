package utils

import utils.Environments.environments
import java.io.FileInputStream
import java.io.IOException
import java.util.*

object GetProperties {
    private const val PATH = "src/test/resources/"
    private var properties: Properties? = null

    fun getProp(valor: String?): String {
        try {
            if (properties.isNullOrEmpty()) {
                properties = Properties()
                properties!!.load(FileInputStream("$PATH$environments.properties"))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return properties!!.getProperty(valor)
    }
}