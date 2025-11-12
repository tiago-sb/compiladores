/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722
* Inicio...........: 12.11.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: TipoToken
* Funcao...........: possuir todos os possíveis tokens da linguagem
*   Sigma
*************************************************************** */

package util;

public enum TipoToken {
  // Palavras reservadas
  PROGRAM, VAR, BEGIN, END, IF, THEN, ELSE, WHILE, DO, FOR, TO, DOWNTO,
  FUNCTION, PROCEDURE, INTEGER, REAL, BOOLEAN, CHAR, STRING, ARRAY, OF,
  TRUE, FALSE, AND, OR, NOT, DIV, MOD,

  // Identificadores e literais
  IDENTIFICADOR, INTEIRO, REAL_NUM, CARACTERE, STRING_LIT,

  // Operadores e símbolos
  MAIS, MENOS, MULT, DIVISAO, ATRIBUICAO, IGUAL, DIFERENTE, MENOR,
  MAIOR, MENOR_IGUAL, MAIOR_IGUAL, PONTO, VIRGULA, PONTO_VIRGULA,
  DOIS_PONTOS, ABRE_PAR, FECHA_PAR, ABRE_COL, FECHA_COL,

  // Comentários e outros
  COMENTARIO, EOF;

  int getTipo() {
    throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
  }
}