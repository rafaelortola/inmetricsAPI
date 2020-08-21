# ATENÇÃO!
# PARA RODAR ESTA FEATURE E CADASTRAR ALGUM DOS EMPREGADOS DA LISTA SERÁ NECESSÁRIO DESCOMENTAR
# UMA LINHA OU MAIS DE UMA LINHA NAS QUAIS DESEJA UTILIZAR

#@cadastrarEmpregado
@smokeTest
Feature: Cadastrar Empregado
  Scenario Outline: Cadastrar um empregado com sucesso
    Given que eu informe os dados de cadastro <dataAdmissao>, <cargo>, <comissao>, <cpf>, <departamento>, <nome>, <salario>, <sexo> e <tipoContratacao>
    When submeto o cadastro
    Then o cadastro é realizado com sucesso <statusCode>
    Examples:
      |dataAdmissao|cargo|comissao|cpf             |departamento|nome               |salario   |sexo|tipoContratacao | statusCode |
#      |"01/02/2020"|"479"|"1,00"  |"130.732.947-08"|2           |"Rafael User100"     |"9.000,00"|"i" |"clt"           | 202        |
#      |"01/02/2019"|"479"|"3,00"  |"758.436.930-82"|2           |"Rafael User2"     |"5.000,00"|"f" |"pj"            | 202        |
#      |"22/12/2018"|"479"|"5,00"  |"111.378.890-98"|2           |"Rafael User3"     |"2.000,00"|"m" |"clt"           | 202        |
#      |"01/12/2017"|"479"|"5,00"  |"589.171.880-44"|2           |"Rafael User4"     |"2.232,00"|"i" |"clt"           | 202        |
#      |"15/12/2016"|"479"|"5,00"  |"337.936.550-59"|2           |"Rafael User5"     |"2.500,00"|"f" |"pj"            | 202        |
#      |"02/12/2015"|"479"|"5,00"  |"224.064.340-44"|2           |"Rafael User6"     |"2.800,00"|"m" |"pj"            | 202        |
#      |"11/12/2014"|"479"|"5,00"  |"527.723.610-47"|2           |"Rafael User7"     |"3.510,00"|"i" |"clt"           | 202        |
#      |"01/05/2013"|"479"|"5,00"  |"902.731.180-30"|2           |"Rafael User8"     |"7.780,00"|"f" |"pj"            | 202        |
#      |"01/06/2012"|"479"|"5,00"  |"594.343.870-00"|2           |"Rafael User9"     |"2.080,00"|"m" |"pj"            | 202        |