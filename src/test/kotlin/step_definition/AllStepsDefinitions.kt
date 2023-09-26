package step_definition

import io.cucumber.java.pt.Quando
import services.RestServices

class AllStepsDefinitions {

    @Quando("GET no endpoint {string}")
    fun `metodo GET sensibilizado`(endpoint: String, pathParameters: MutableMap<String, String>) {
        RestServices.get(endpoint, pathParameters)
    }
}