package utils

enum class ApiPath(val path: String) {
    GET_USERS("/usuarios"),
    CREATE_USER("/usuarios"),
    DELETE_USER("/usuarios/{id}")
}