# ATENÇÃO!
# PARA RODAR ESTA FEATURE E ALTERAR ALGUM DOS EMPREGADOS DA LISTA SERÁ NECESSÁRIO
# ALTERAR OS DADOS DE CADASTRO NO "EXEMPLES:" LOGO ABAIXO. ANTES, É BACANA IR ATÉ A FEATURE
# listarEmpregado e pegar os dados atuais dele, após isso você pode vir para a feature alterarEmpregado
# e colocar o ID e os demais campos que deseja alterar;

#@alterarEmpregado
@smokeTest
Feature: Alterar Empregado
  Scenario Outline: Alterar um empregado com sucesso
    Given que eu altere o <dataAdmissao>, <cargo>, <comissao>, <cpf>, <departamento>, <nome>, <salario>, <sexo> e <tipoContratacao>
    When submeto a requisição de alteração para o id <id>
    Then o empregado é alterado com sucesso <statusCode>
    Examples:
      |id    | statusCode |dataAdmissao|cargo|comissao|cpf             |departamento|nome                |salario   |sexo|tipoContratacao|
      |1460  | 202        |"01/02/2021"|"479"|"2,00"  |"130.732.947-08"|2           |"Silva Alterado"    |"1.000,00"|"m" |"pj"          |
