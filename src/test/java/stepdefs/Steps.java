package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Steps {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    private static String ENDPOINT_CADASTRAR_EMPREGADO = "https://inm-api-test.herokuapp.com/empregado/cadastrar";
    private static String ENDPOINT_DELETAR_EMPREGADO = "https://inm-api-test.herokuapp.com/empregado/deletar/";


    @Given("que eu informe os dados de cadastro \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", (\\d+), \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void informarDataAdmissao(String dataAdmissao, String cargo, String comissao, String cpf, Integer departamento, String nome, String salario, String sexo, String tipoContratacao){
        request = given()
                .contentType("application/json")
                .auth().basic("inmetrics","automacao")
                .body("{" +
                        "\"admissao\": \""+dataAdmissao+"\"," +
                        " \"cargo\": \""+cargo+"\"," +
                        "\"comissao\": \""+comissao+"\"," +
                        "\"cpf\": \""+cpf+"\"," +
                        "\"departamentoId\": \""+departamento.toString()+"\"," +
                        "\"nome\": \""+nome+"\"," +
                        "\"salario\": \""+salario+"\"," +
                        "\"sexo\": \""+sexo+"\"," +
                        "\"tipoContratacao\": \""+tipoContratacao+"\"" +
                        "}");
    }

    @When("submeto o cadastro")
    public void submeterCadastroEmpregado(){
        response = request.when()
                .post(ENDPOINT_CADASTRAR_EMPREGADO);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("o cadastro é realizado com sucesso (.*?)$")
    public void statusDoCadastro(Integer statusCode){
        response.then().statusCode(statusCode);
        System.out.println(response.statusCode());

    }

    @Given("^que eu informe o id do empregado$")
    public void informarIdEmpregado(){
        request = given()
                .contentType("application/json")
                .auth().basic("inmetrics","automacao");
    }

    @When("submeto a requisição para o id (.*?)$")
    public void submeterExclusaoEmpregado(Integer idEmpregado){
        response = request.when()
                .delete("https://inm-api-test.herokuapp.com/empregado/deletar/"+idEmpregado.toString());
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("o empregado é deletado com sucesso (.*?)$")
    public void statusDaExclusao(Integer statusCode){
        response.then().statusCode(statusCode);
        Assert.assertTrue(response.getBody().asString().equals("Deletado"));
        System.out.println(response.statusCode());
    }

    @Given("^que eu altere o \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", (\\d+), \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void informarDadosParaAlteracao(String dataAdmissao, String cargo, String comissao, String cpf, Integer departamento, String nome, String salario, String sexo, String tipoContratacao){
        request = given()
                .contentType("application/json")
                .auth().basic("inmetrics","automacao")
                .body("{\"admissao\" : \""+dataAdmissao+"\",\"cargo\" : \""+cargo+"\",\"comissao\" : \""+comissao+"\",\"cpf\" : \""+cpf+"\",\"departamentoId\" : \""+departamento+"\",\"nome\" : \""+nome+"\",\"salario\" : \""+salario+"\",\"sexo\" : \""+sexo+"\",\"tipoContratacao\" : \""+tipoContratacao+"\"}");

    }

    @When("^submeto a requisição de alteração para o id (\\d+)$")
    public void submeterAlteracaoEmpregado(Integer idEmpregado){
        response = request.when()
                .put("https://inm-api-test.herokuapp.com/empregado/alterar/"+idEmpregado.toString());
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("o empregado é alterado com sucesso (.*)$")
    public void statusDaAlteracao(Integer statusCode){
        json = response.then().statusCode(statusCode);
        //Assert.assertTrue(response.getBody().asString().equals("Alterado"));
        System.out.println(response.statusCode());
    }

    @When("submeto a requisição de busca para o id (.*?)$")
    public void submeterBuscaEmpregado(Integer idEmpregado){
        response = request.when()
                .get("https://inm-api-test.herokuapp.com/empregado/list/"+idEmpregado.toString());
        System.out.println("Response retornado: " + response.prettyPrint());
    }

    @Then("a busca é realizada com sucesso (.*?)$")
    public void statusDaBusca(Integer statusCode){
        json = response.then().statusCode(statusCode);
        System.out.println(response.statusCode());
    }

    //, "([^"]*)", "([^"]*)", "([^"]*)", "([^"]*)", "([^"]*)" e "([^"]*)"
    //, String cpf, String cargo, String admissao, String salario, String comissao, String tipoContratacao

    @And("^valido os dados de retorno (\\d+), \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
    public void dadosDeRetorno(int empregadoId, String nome, String sexo, String cpf, String cargo, String admissao, String salario, String comissao, String tipoContratacao){
        json = response.then()
                .body("empregadoId", is(empregadoId))
                .body("nome", is(""+nome+""))
                .body("sexo", is(""+sexo+""))
                .body("cpf", is(""+cpf+""))
                .body("cargo", is(""+cargo+""))
                .body("admissao", is(""+admissao+""))
                .body("salario", is(""+salario+""))
                .body("comissao", is(""+comissao+""))
                .body("tipoContratacao", is(""+tipoContratacao+""));

        System.out.println("DADOS VALIDADOS COM SUCESSO!");
        System.out.println("EmpregadoId: "+empregadoId+"");
        System.out.println("nome: "+nome+"");
        System.out.println("sexo: "+sexo+"");
        System.out.println("cpf: "+cpf+"");
        System.out.println("cargo: "+cargo+"");
        System.out.println("admissao: "+admissao+"");
        System.out.println("salario: "+salario+"");
        System.out.println("comissao: "+comissao+"");
        System.out.println("tipoContratacao: "+tipoContratacao+"");
    }

    @Given("que eu busque todos os empregados cadastrados")
    public void buscaPorTodosEmpregadosCadastrados(){
        request = given()
                .contentType("application/json")
                .auth().basic("inmetrics","automacao");
    }

    @When("submeto a requisição de busca para todos")
    public void submeterBuscaPorTodosEmpregados(){
        response = request.when()
                .get("https://inm-api-test.herokuapp.com/empregado/list_all");
        System.out.println("Response retornado: " + response.prettyPrint());
    }

    @Then("a busca de todos os usuários é realizada com sucesso (.*?)$")
    public void statusDaBuscaPorTodosEmpregados(Integer statusCode){
        json = response.then().statusCode(statusCode);
        System.out.println(response.statusCode());
    }
}
