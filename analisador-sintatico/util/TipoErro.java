/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: TipoErro
* Funcao...........: possuir todos os possíveis erros da linguagem
*   Sigma
*************************************************************** */

package util;

public enum TipoErro {
  // erros léxicos
  CARACTERE_INVALIDO,
  STRING_NAO_FECHADA,
  COMENTARIO_NAO_FECHADO,
  NUMERO_MAL_FORMADO,

  // Estrutura do programa
  PROGRAMA_NAO_INICIADO_CORRETAMENTE,
  PONTO_E_VIRGULA_APOS_IDENTIFICADOR_AUSENTE,
  BEGIN_NAO_ENCONTRADO,
  END_NAO_ENCONTRADO,
  PONTO_FINAL_AUSENTE,

  // Declarações
  DOIS_PONTOS_AUSENTE,
  PONTO_E_VIRGULA_APOS_TIPO_AUSENTE,
  IDENTIFICADOR_ESPERADO,
  IDENTIFICADOR_APOS_VIRGULA_AUSENTE,
  TIPO_INVALIDO,

  // Expressões e comandos
  ATRIBUICAO_INVALIDA,
  COMANDO_INVALIDO,
  EXPRESSAO_ESPERADA,

  // Estruturas de controle
  IF_ESPERADO,
  THEN_ESPERADO,
  OPERADOR_RELACIONAL_ESPERADO,
  WHILE_ESPERADO,
  DO_ESPERADO
}