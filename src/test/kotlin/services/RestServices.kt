package services

import io.restassured.http.ContentType
import utils.ApiPath
import utils.ApiUrlBase
import utils.GetProperties
import utils.RestMethods
object RestServices {

    fun post(apiPath: String) {
        RestMethods.createRequest()
        RestMethods.setPath(ApiUrlBase.BASE_URL.path, ApiPath.valueOf(apiPath).path)
        RestMethods.setHeader(setHeaders())
        RestMethods.postRequest()
        RestMethods.log()
    }

    fun postWithBody(apiPath: String, body: String) {
        RestMethods.createRequest()
        RestMethods.setPath(ApiUrlBase.BASE_URL.path, ApiPath.valueOf(apiPath).path)
        RestMethods.setHeader(setHeaders())
        RestMethods.setBody(body)
        RestMethods.postRequest()
        RestMethods.log()
    }

    fun get(apiPath: String, pathParameters: MutableMap<String, String>) {
        RestMethods.createRequest()
        RestMethods.setPath(ApiUrlBase.BASE_URL.path, ApiPath.valueOf(apiPath).path)
        RestMethods.setPathParams(pathParameters)
        RestMethods.setHeader(setHeaders())
        RestMethods.getRequest()
        RestMethods.log()
    }

    private fun setHeaders(): MutableMap<String, String> {
        val headers: MutableMap<String, String> = HashMap()
        headers["Content-Type"] = ContentType.JSON.toString()
        headers["x"] = GetProperties().getProp("header.x")
        headers["y"] = GetProperties().getProp("header.y")
        return headers
    }
}