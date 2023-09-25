package utils

object Environments {
    @JvmStatic
    val environments: String
        get() {
            var env = System.getProperty("environment")
            if (env.isNullOrEmpty())
                env = "local"
            return env
        }
}