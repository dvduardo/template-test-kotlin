package utils

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

object RestMethods {
    private var request: RequestSpecification? = null
    private var response: Response? = null
        private set

    fun log() {
        RestAssured.given().then().log().all()
    }

    fun createRequest() {
        RestAssured.useRelaxedHTTPSValidation()
        request = RestAssured.given().`when`().log().all()
    }

    fun setPath(baseUrl: String?, path: String?) {
        if (request!!.equals(null))
            createRequest()
        RestAssured.useRelaxedHTTPSValidation()
        request!!.baseUri(baseUrl)
        request!!.basePath(path)
    }

    fun setFormParams(params: Map<String, String>) {
        request!!.formParams(params)
    }

    fun setPathParams(params: Map<String, String>) {
        request!!.pathParams(params)
    }

    fun setHeader(content: Map<String, String>) {
        request!!.headers(content)
    }

    fun setBody(body: String) {
        request!!.body(body)
    }

    fun getRequest() {
        response = request!!.get()
    }

    fun postRequest() {
        response = request!!.post()
    }

    fun deleteRequest() {
        response = request!!.delete()
    }

    fun patchRequest() {
        response = request!!.patch()
    }

    fun putRequest() {
        response = request!!.put()
    }
}