package util;

import java.util.*;
// import java.util.regex.Pattern;
import model.Erro;
import model.Token;

public class AnalisadorLexico {
  private String codigoFonte;
  private int posicao;
  private int linha;
  private int coluna;
  private List<Token> tokens;
  private List<Erro> erros;
  private Map<String, Token.TipoToken> palavrasReservadas;

  public AnalisadorLexico(String codigoFonte) {
    this.codigoFonte = codigoFonte;
    this.posicao = 0;
    this.linha = 1;
    this.coluna = 1;
    this.tokens = new ArrayList<>();
    this.erros = new ArrayList<>();
    inicializarPalavrasReservadas();
  }

  private void inicializarPalavrasReservadas() {
    palavrasReservadas = new HashMap<>();
    palavrasReservadas.put("program", Token.TipoToken.PROGRAM);
    palavrasReservadas.put("var", Token.TipoToken.VAR);
    palavrasReservadas.put("begin", Token.TipoToken.BEGIN);
    palavrasReservadas.put("end", Token.TipoToken.END);
    palavrasReservadas.put("if", Token.TipoToken.IF);
    palavrasReservadas.put("then", Token.TipoToken.THEN);
    palavrasReservadas.put("else", Token.TipoToken.ELSE);
    palavrasReservadas.put("while", Token.TipoToken.WHILE);
    palavrasReservadas.put("do", Token.TipoToken.DO);
    palavrasReservadas.put("for", Token.TipoToken.FOR);
    palavrasReservadas.put("to", Token.TipoToken.TO);
    palavrasReservadas.put("downto", Token.TipoToken.DOWNTO);
    palavrasReservadas.put("function", Token.TipoToken.FUNCTION);
    palavrasReservadas.put("procedure", Token.TipoToken.PROCEDURE);
    palavrasReservadas.put("integer", Token.TipoToken.INTEGER);
    palavrasReservadas.put("real", Token.TipoToken.REAL);
    palavrasReservadas.put("boolean", Token.TipoToken.BOOLEAN);
    palavrasReservadas.put("char", Token.TipoToken.CHAR);
    palavrasReservadas.put("string", Token.TipoToken.STRING);
    palavrasReservadas.put("array", Token.TipoToken.ARRAY);
    palavrasReservadas.put("of", Token.TipoToken.OF);
    palavrasReservadas.put("true", Token.TipoToken.TRUE);
    palavrasReservadas.put("false", Token.TipoToken.FALSE);
    palavrasReservadas.put("and", Token.TipoToken.AND);
    palavrasReservadas.put("or", Token.TipoToken.OR);
    palavrasReservadas.put("not", Token.TipoToken.NOT);
    palavrasReservadas.put("div", Token.TipoToken.DIV);
    palavrasReservadas.put("mod", Token.TipoToken.MOD);
  }

  public List<Token> analisar() {
    while (posicao < codigoFonte.length()) {
      char atual = codigoFonte.charAt(posicao);

      if (Character.isWhitespace(atual)) {
        processarEspacos();
      } else if (Character.isLetter(atual) || atual == '_') {
        processarIdentificadorOuPalavraReservada();
      } else if (Character.isDigit(atual)) {
        processarNumero();
      } else if (atual == '\'') {
        processarCaractereOuString();
      } else if (atual == '{') {
        processarComentario();
      } else {
        processarSimbolo();
      }
    }

    tokens.add(new Token(Token.TipoToken.EOF, "EOF", linha, coluna));
    return tokens;
  }

  private void processarEspacos() {
    while (posicao < codigoFonte.length() &&
        Character.isWhitespace(codigoFonte.charAt(posicao))) {
      if (codigoFonte.charAt(posicao) == '\n') {
        linha++;
        coluna = 1;
      } else {
        coluna++;
      }
      posicao++;
    }
  }

  private void processarIdentificadorOuPalavraReservada() {
    int inicio = posicao;
    int linhaInicio = linha;
    int colunaInicio = coluna;

    while (posicao < codigoFonte.length() &&
        (Character.isLetterOrDigit(codigoFonte.charAt(posicao)) ||
            codigoFonte.charAt(posicao) == '_')) {
      posicao++;
      coluna++;
    }

    String lexema = codigoFonte.substring(inicio, posicao).toLowerCase();
    Token.TipoToken tipo = palavrasReservadas.getOrDefault(lexema, Token.TipoToken.IDENTIFICADOR);

    tokens.add(new Token(tipo, lexema, linhaInicio, colunaInicio));
  }

  private void processarNumero() {
    int inicio = posicao;
    int linhaInicio = linha;
    int colunaInicio = coluna;
    boolean temPonto = false;

    while (posicao < codigoFonte.length() &&
        (Character.isDigit(codigoFonte.charAt(posicao)) ||
            codigoFonte.charAt(posicao) == '.')) {

      if (codigoFonte.charAt(posicao) == '.') {
        if (temPonto) {
          erros.add(new Erro(Erro.TipoErro.NUMERO_MAL_FORMADO,
              "Número com múltiplos pontos decimais", linha, coluna));
          break;
        }
        temPonto = true;
      }

      posicao++;
      coluna++;
    }

    String lexema = codigoFonte.substring(inicio, posicao);
    Token.TipoToken tipo = temPonto ? Token.TipoToken.REAL_NUM : Token.TipoToken.INTEIRO;

    tokens.add(new Token(tipo, lexema, linhaInicio, colunaInicio));
  }

  private void processarCaractereOuString() {
    int inicio = posicao;
    int linhaInicio = linha;
    int colunaInicio = coluna;

    posicao++; // Pula o primeiro '
    coluna++;

    boolean fechado = false;
    while (posicao < codigoFonte.length()) {
      if (codigoFonte.charAt(posicao) == '\'') {
        fechado = true;
        posicao++;
        coluna++;
        break;
      }
      if (codigoFonte.charAt(posicao) == '\n') {
        break; // String não fechada
      }
      posicao++;
      coluna++;
    }

    String lexema = codigoFonte.substring(inicio, posicao);
    if (!fechado) {
      erros.add(new Erro(Erro.TipoErro.STRING_NAO_FECHADA,
          "String/caractere não fechado", linhaInicio, colunaInicio));
    } else if (lexema.length() == 3) { // 'x'
      tokens.add(new Token(Token.TipoToken.CARACTERE, lexema, linhaInicio, colunaInicio));
    } else {
      tokens.add(new Token(Token.TipoToken.STRING_LIT, lexema, linhaInicio, colunaInicio));
    }
  }

  private void processarComentario() {
    int linhaInicio = linha;
    int colunaInicio = coluna;

    posicao++; // Pula o {
    coluna++;

    boolean fechado = false;
    while (posicao < codigoFonte.length()) {
      if (codigoFonte.charAt(posicao) == '}') {
        fechado = true;
        posicao++;
        coluna++;
        break;
      }
      if (codigoFonte.charAt(posicao) == '\n') {
        linha++;
        coluna = 1;
      } else {
        coluna++;
      }
      posicao++;
    }

    if (!fechado) {
      erros.add(new Erro(Erro.TipoErro.COMENTARIO_NAO_FECHADO,
          "Comentário não fechado", linhaInicio, colunaInicio));
    }
  }

  private void processarSimbolo() {
    char atual = codigoFonte.charAt(posicao);
    int linhaAtual = linha;
    int colunaAtual = coluna;

    switch (atual) {
      case '+':
        tokens.add(new Token(Token.TipoToken.MAIS, "+", linhaAtual, colunaAtual));
        break;
      case '-':
        tokens.add(new Token(Token.TipoToken.MENOS, "-", linhaAtual, colunaAtual));
        break;
      case '*':
        tokens.add(new Token(Token.TipoToken.MULT, "*", linhaAtual, colunaAtual));
        break;
      case '/':
        tokens.add(new Token(Token.TipoToken.DIVISAO, "/", linhaAtual, colunaAtual));
        break;
      case ':':
        if (posicao + 1 < codigoFonte.length() && codigoFonte.charAt(posicao + 1) == '=') {
          tokens.add(new Token(Token.TipoToken.ATRIBUICAO, ":=", linhaAtual, colunaAtual));
          posicao++;
          coluna++;
        } else {
          tokens.add(new Token(Token.TipoToken.DOIS_PONTOS, ":", linhaAtual, colunaAtual));
        }
        break;
      case '=':
        tokens.add(new Token(Token.TipoToken.IGUAL, "=", linhaAtual, colunaAtual));
        break;
      case '<':
        if (posicao + 1 < codigoFonte.length()) {
          if (codigoFonte.charAt(posicao + 1) == '=') {
            tokens.add(new Token(Token.TipoToken.MENOR_IGUAL, "<=", linhaAtual, colunaAtual));
            posicao++;
            coluna++;
          } else if (codigoFonte.charAt(posicao + 1) == '>') {
            tokens.add(new Token(Token.TipoToken.DIFERENTE, "<>", linhaAtual, colunaAtual));
            posicao++;
            coluna++;
          } else {
            tokens.add(new Token(Token.TipoToken.MENOR, "<", linhaAtual, colunaAtual));
          }
        } else {
          tokens.add(new Token(Token.TipoToken.MENOR, "<", linhaAtual, colunaAtual));
        }
        break;
      case '>':
        if (posicao + 1 < codigoFonte.length() && codigoFonte.charAt(posicao + 1) == '=') {
          tokens.add(new Token(Token.TipoToken.MAIOR_IGUAL, ">=", linhaAtual, colunaAtual));
          posicao++;
          coluna++;
        } else {
          tokens.add(new Token(Token.TipoToken.MAIOR, ">", linhaAtual, colunaAtual));
        }
        break;
      case '.':
        tokens.add(new Token(Token.TipoToken.PONTO, ".", linhaAtual, colunaAtual));
        break;
      case ',':
        tokens.add(new Token(Token.TipoToken.VIRGULA, ",", linhaAtual, colunaAtual));
        break;
      case ';':
        tokens.add(new Token(Token.TipoToken.PONTO_VIRGULA, ";", linhaAtual, colunaAtual));
        break;
      case '(':
        tokens.add(new Token(Token.TipoToken.ABRE_PAR, "(", linhaAtual, colunaAtual));
        break;
      case ')':
        tokens.add(new Token(Token.TipoToken.FECHA_PAR, ")", linhaAtual, colunaAtual));
        break;
      case '[':
        tokens.add(new Token(Token.TipoToken.ABRE_COL, "[", linhaAtual, colunaAtual));
        break;
      case ']':
        tokens.add(new Token(Token.TipoToken.FECHA_COL, "]", linhaAtual, colunaAtual));
        break;
      default:
        erros.add(new Erro(Erro.TipoErro.CARACTERE_INVALIDO,
            "Caractere inválido: " + atual, linhaAtual, colunaAtual));
    }

    posicao++;
    coluna++;
  }

  public List<Token> getTokens() {
    return tokens;
  }

  public List<Erro> getErros() {
    return erros;
  }

  public boolean temErros() {
    return !erros.isEmpty();
  }
}
