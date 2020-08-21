# ATENÇÃO!
# PARA RODAR ESTA FEATURE E LISTAR UM EMPREGADO (apenas), será necessário você informar o ID dele e
# Informar todos os seus dados, também, para validar que trouxe corretamente.
# Um macete é você rodar o cenário abaixo "Listar todos os empregados com sucesso" e escolher um ID e pegar seus dados e jogar no cenário de cima.

# Para rodar o 2º cenário é preciso apenas executar normalmente.

#@listarEmpregado
@smokeTest
Feature: Listar Empregado
  Scenario Outline: Listar um empregado com sucesso
    Given que eu informe o id do empregado
    When submeto a requisição de busca para o id <id>
    Then a busca é realizada com sucesso <statusCode>
    And valido os dados de retorno <empregadoId>, <nome>, <sexo>, <cpf>, <cargo>, <admissao>, <salario>, <comissao> e <tipoContratacao>
    Examples:
      |id  |statusCode|empregadoId|nome           |sexo|cpf              |cargo|admissao     |salario   |comissao|tipoContratacao|
      |1451|202       |1451       |"Rafael Ortola"|"m" |"130.732.947-08" |"479"|"02/03/2018" |"3.000,00"|"1,00"  |"clt"          |

  @listarTodosEmpregados @smokeTest
  Scenario Outline: Listar todos os empregados com sucesso
    Given que eu busque todos os empregados cadastrados
    When submeto a requisição de busca para todos
    Then a busca de todos os usuários é realizada com sucesso <statusCode>
    Examples:
      |statusCode|
      |200       |
