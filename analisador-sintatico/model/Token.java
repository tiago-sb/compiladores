/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: Token
* Funcao...........: possui todos os métodos necessários para lidar
*   com os tokens
*************************************************************** */

package model;

import util.TipoToken;

public class Token {
  private TipoToken tipo;
  private String tipoString;
  private String lexema;
  private int linha;
  private int coluna;

  /*
   * ***************************************************************
   * Metodo: mapTipo
   * Funcao: pega os tipos de tokens analisados genericamente pelo 
   *   analisador e retorna tipos específicos da linguagem mini-pascal
   * Parametros: TipoToken token
   * Retorno: String
   */
  private String mapTipo(TipoToken token) {
    switch (token) {
      // palavras reservadas
      case PROGRAM: case VAR: case BEGIN: case END:
      case IF: case THEN: case ELSE: case WHILE:
      case DO: case FOR: case TO: case DOWNTO:
      case FUNCTION: case PROCEDURE: case INTEGER:
      case REAL: case BOOLEAN: case CHAR: case STRING:
      case ARRAY: case OF: case TRUE: case FALSE:
      case DIV: case MOD: return "PALAVRA_RESERVADA";

      // identificador
      case IDENTIFICADOR: return "IDENTIFICADOR";

      // numeros
      case INTEIRO: return "NUMERO_INTEIRO";
      case REAL_NUM: return "NUMERO_REAL";
      
      // string
      case CARACTERE: return "CARACTERE";
      case STRING_LIT: return "STRING";

      // operador aritmetico
      case MAIS: case MENOS: case MULT: case DIVISAO: return "OPERADOR_ARITMETICO";

      // operador relacional
      case IGUAL: case DIFERENTE: case MENOR:
      case MAIOR: case MENOR_IGUAL: case MAIOR_IGUAL: return "OPERADOR_RELACIONAL";

      // operador logico
      case AND: case OR: case NOT: return "OPERADOR_LOGICO";

      // simbolo especial
      case PONTO: case VIRGULA: case PONTO_VIRGULA:
      case DOIS_PONTOS: case ABRE_PAR: case FECHA_PAR:
      case ABRE_COL: case FECHA_COL: return "SIMBOLO_ESPECIAL";

      // atribuicao
      case ATRIBUICAO: return "ATRIBUIÇÃO";

      // comentario
      case COMENTARIO: return "";

      // fim do arquivo
      case EOF: return "FIM";

      // casos extras
      default:
        return "DESCONHECIDO";
    }
  }

  public Token(TipoToken tipo, String lexema, int linha, int coluna) {
    this.tipo = tipo;
    this.lexema = lexema;
    this.linha = linha;
    this.coluna = coluna;
  }

  public Token(String tipo, String lexema, int linha, int coluna) {
    this.tipo = TipoToken.TIPOSTRING;
    this.tipoString = tipo;
    this.lexema = lexema;
    this.linha = linha;
    this.coluna = coluna;
  }

  // Get
  public String getTipo() {
    return this.mapTipo(tipo);
  }
  
  public String getTipoString(){
    return this.tipoString;
  }
  
  // Get
  public String getLexema() {
    return lexema;
  }

  // Get
  public int getLinha() {
    return linha;
  }

  // Get
  public int getColuna() {
    return coluna;
  }

  // método toString para o objeto Token
  @Override
  public String toString() {
    return String.format("Token [%s, '%s', linha=%d, coluna=%d]",
        this.getTipoString(), lexema, linha, coluna);
  }
}
