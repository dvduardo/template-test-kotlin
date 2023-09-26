package utils

enum class ApiUrlBase(val path: String) {
    BASE_URL(GetProperties().getProp("base_url"))
}