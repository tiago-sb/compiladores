package model;

public class Token {
  private TipoToken tipo;
  private String lexema;
  private int linha;
  private int coluna;

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

  private String mapTipo(Token.TipoToken token) {
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
      case ABRE_COL: case FECHA_COL: return "SÍMBOLO_ESPECIAL";

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

  // Getters
  public String getTipo() {
    return this.mapTipo(tipo);
  }

  public String getLexema() {
    return lexema;
  }

  public int getLinha() {
    return linha;
  }

  public int getColuna() {
    return coluna;
  }

  @Override
  public String toString() {
    return String.format("Token [%s, '%s', linha=%d, coluna=%d]",
        tipo, lexema, linha, coluna);
  }
}
