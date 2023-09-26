package step_definition

import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.restassured.RestAssured
import services.RestServices

class AllStepsDefinitions {

    @Quando("GET no endpoint {string}")
    fun `metodo GET sensibilizado`(endpoint: String) {
        RestServices.get(endpoint)
    }

    @Entao("validar resposta como {int}")
    fun `validar status da resposta`(status:Int){
        RestServices.validateResponse(status)
    }
}