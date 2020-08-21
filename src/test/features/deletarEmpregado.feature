# ATENÇÃO!
# PARA RODAR ESTA FEATURE E DELETAR ALGUM DOS IDS SERÁ NECESSÁRIO DESCOMENTAR A LINHA OU
# MAIS DE UMA LINHA NAS QUAIS DESEJA UTILIZAR

#@deletarEmpregado
@smokeTest
Feature: Deletar Empregado
  Scenario Outline: Deletar um empregado com sucesso
    Given que eu informe o id do empregado
    When submeto a requisição para o id <id>
    Then o empregado é deletado com sucesso <statusCode>
    Examples:
      |id    |statusCode |
#     |1545   |202       |
#     |767   |202        |
#     |766   |202        |
#     |765   |202        |
#     |920   |202        |
#     |782   |202        |
#     |927   |202        |
#     |936   |202        |
