package step_definition

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.github.serpro69.kfaker.Faker
import org.json.JSONObject
import services.RestServices
import utils.FileProperties
import utils.JsonProperties

class AllStepsDefinitions {
    private lateinit var responseBody: String

    @Quando("GET no endpoint {string}")
    fun `metodo GET sensibilizado`(endpoint: String) {
        RestServices.get(endpoint)
    }

    @Entao("validar resposta como {int}")
    fun `validar status da resposta`(status: Int) {
        RestServices.validateResponse(status)
    }

//    @Quando("solicitar o login")
//    fun `validar status da resposta`(datas:Map<String, String>){
//        var data = HashMap<String, String>()
//        data["email"] = datas.get("email").toString()
//        data["password"] = datas.get("password").toString()
//        RestServices.postWithBody("LOGIN",data)
//    }

    @Quando("solicitar cadastro de usuario com payload {string}")
    fun `solicitar cadastro`(payload: String) {
        val data = JsonProperties.generatePayload(payload)
        responseBody = RestServices.postWithBody(payload.uppercase(), data.toString())
    }

    @Quando("solicitar cadastro de usuario com payload {string} alterado")
    fun `solicitar cadastro payload alterado`(payload: String) {
        val data = JsonProperties.generatePayload(payload)
        responseBody = RestServices.postWithBody(payload.uppercase(), `modifica payload`(data))
    }

    private fun `modifica payload`(data: JSONObject): String {
        val faker = Faker()
        val jsonElement = Gson().fromJson(data.toString(), JsonElement::class.java).asJsonObject
        jsonElement.addProperty("nome", faker.name.firstName())
        jsonElement.addProperty("email", faker.internet.email())
        jsonElement.addProperty("password", faker.minecraft.items())
        return jsonElement.toString()
    }

    @Entao("validar mensagem de resposta")
    fun validar_mensagem_de_resposta(data: Map<String, String>) {
        val lastResponseData = JsonParser().parse(responseBody).asJsonObject
        for (key in data.keys)
            if (data[key].toString() != lastResponseData[key].asString)
                throw Exception("O campo [$key] não corresponde os valores.\nEsperado-->\t${data[key]} \nEncontrado-->\t${lastResponseData[key]}")
    }

    @Entao("salva id da resposta")
    fun salva_id_da_resposta() {
        val lastResponseData = JsonParser().parse(responseBody).asJsonObject
        FileProperties.createFile("newId", lastResponseData["_id"].asString)
    }

    @Quando("solicitar exclusao do usuario anteriormente criado")
    fun solicitar_exclusao_do_usuario_criado_anteriormente() {
        val param: MutableMap<String, String> = HashMap()
        param["id"] = FileProperties.readFile("newId")
        responseBody = RestServices.deleteWithParam("DELETE_USER", param)
    }

    @Quando("solicitar exclusao do usuario com id {string}")
    fun solicitar_exclusao_do_usuario(idValue: String) {
        val param: MutableMap<String, String> = HashMap()
        param["id"] = idValue
        responseBody = RestServices.deleteWithParam("DELETE_USER", param)
    }
}