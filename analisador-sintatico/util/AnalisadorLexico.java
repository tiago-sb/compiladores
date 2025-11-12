/* ***************************************************************
* Autor............: Tiago Santos Bela
* Matricula........: 202220722
* Inicio...........: 12.11.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: analisador léxico
* Funcao...........: Controla a lóxica central do analisador léxico
*************************************************************** */

package util;

import java.util.*;
import model.Erro;
import model.Token;

public class AnalisadorLexico {
  private String codigoFonte;
  private int posicao;
  private int linha;
  private int coluna;
  private List<Token> tokens;
  private List<Erro> erros;
  private Map<String, TipoToken> palavrasReservadas;

  public AnalisadorLexico(String codigoFonte) {
    this.codigoFonte = codigoFonte;
    this.posicao = 0;
    this.linha = 1;
    this.coluna = 1;
    this.tokens = new ArrayList<>();
    this.erros = new ArrayList<>();
    inicializarPalavrasReservadas();
  }

  /*
   * ***************************************************************
   * Metodo: inicializarPalavrasReservadas
   * Funcao: coloca as palavras reservadas da linhagem em um hashMap
   *   para que as mesmas sejam identificadas em tempo 0(1)
   * Parametros: vazio
   * Retorno: void
   */
  private void inicializarPalavrasReservadas() {
    palavrasReservadas = new HashMap<>();
    palavrasReservadas.put("program", TipoToken.PROGRAM);
    palavrasReservadas.put("var", TipoToken.VAR);
    palavrasReservadas.put("begin", TipoToken.BEGIN);
    palavrasReservadas.put("end", TipoToken.END);
    palavrasReservadas.put("if", TipoToken.IF);
    palavrasReservadas.put("then", TipoToken.THEN);
    palavrasReservadas.put("else", TipoToken.ELSE);
    palavrasReservadas.put("while", TipoToken.WHILE);
    palavrasReservadas.put("do", TipoToken.DO);
    palavrasReservadas.put("for", TipoToken.FOR);
    palavrasReservadas.put("to", TipoToken.TO);
    palavrasReservadas.put("downto", TipoToken.DOWNTO);
    palavrasReservadas.put("function", TipoToken.FUNCTION);
    palavrasReservadas.put("procedure", TipoToken.PROCEDURE);
    palavrasReservadas.put("integer", TipoToken.INTEGER);
    palavrasReservadas.put("real", TipoToken.REAL);
    palavrasReservadas.put("boolean", TipoToken.BOOLEAN);
    palavrasReservadas.put("char", TipoToken.CHAR);
    palavrasReservadas.put("string", TipoToken.STRING);
    palavrasReservadas.put("array", TipoToken.ARRAY);
    palavrasReservadas.put("of", TipoToken.OF);
    palavrasReservadas.put("true", TipoToken.TRUE);
    palavrasReservadas.put("false", TipoToken.FALSE);
    palavrasReservadas.put("and", TipoToken.AND);
    palavrasReservadas.put("or", TipoToken.OR);
    palavrasReservadas.put("not", TipoToken.NOT);
    palavrasReservadas.put("div", TipoToken.DIV);
    palavrasReservadas.put("mod", TipoToken.MOD);
  }

  /*
   * ***************************************************************
   * Metodo: analisar
   * Funcao: método principal da análise lexica, ele fica em loop durante
   *   toda a análise, identificando as classes dos tokens
   * Parametros: vazio
   * Retorno: void
   */
  public List<Token> analisar() {
    while (posicao < codigoFonte.length()) {
      char atual = codigoFonte.charAt(posicao);

      // se for espaço, palavra reservada, numero,  string, comentário ou símbolo
      // desconhecido o analisador consegue identificar
      if (Character.isWhitespace(atual)) {
        processarEspacos();
      } else if (Character.isLetter(atual) || atual == '_') {
        processarIdentificadorOuPalavraReservada();
      } else if (Character.isDigit(atual)) {
        processarNumero();
      } else if (atual == '\'') {
        processarCaractereOuString();
      } else if (atual == '\"') {
        processarCaractereOuStringEntreAspasDuplas();
      } else if (atual == '{') {
        processarComentario();
      } else {
        processarSimbolo();
      }
    }

    tokens.add(new Token(TipoToken.EOF, "EOF", linha, coluna));
    // tokens analisados retornados 
    return tokens;
  }


  /*
   * ***************************************************************
   * Metodo: processarEspacos
   * Funcao: lógica central para processar os espaços em branco, são
   *   ignorados apenas
   * Parametros: vazio
   * Retorno: void
   */
  private void processarEspacos() {
    // apenas verifica se é caractere em branco e ignora eles, pois são irrelavantes para
    // a análise
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

  /*
   * ***************************************************************
   * Metodo: processarIdentificadorOuPalavraReservada
   * Funcao: verifica se a palavra analisada é uma palavra reservada,
   *   identificador
   * Parametros: vazio
   * Retorno: void
   */
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

    // torna minisculo o lexema
    String lexema = codigoFonte.substring(inicio, posicao).toLowerCase();
    
    // retorna um identificador ou palavra reservada
    TipoToken tipo = palavrasReservadas.getOrDefault(lexema, TipoToken.IDENTIFICADOR);

    tokens.add(new Token(tipo, lexema, linhaInicio, colunaInicio));
  }

  /*
   * ***************************************************************
   * Metodo: processarNumero
   * Funcao: processa os números (reais)
   * Parametros: vazio
   * Retorno: void
   */
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
          erros.add(new Erro(TipoErro.NUMERO_MAL_FORMADO,
              "Número com múltiplos pontos decimais", linha, coluna));
          break;
        }
        temPonto = true;
      }

      posicao++;
      coluna++;
    }

    String lexema = codigoFonte.substring(inicio, posicao);
    // caso tenha ponto, o número é real, caso não será um inteiro
    TipoToken tipo = temPonto ? TipoToken.REAL_NUM : TipoToken.INTEIRO;

    tokens.add(new Token(tipo, lexema, linhaInicio, colunaInicio));
  }

  /*
   * ***************************************************************
   * Metodo: processarCaractereOuString
   * Funcao: processa as strings
   * Parametros: vazio
   * Retorno: void
   */
  private void processarCaractereOuString() {
    int inicio = posicao;
    int linhaInicio = linha;
    int colunaInicio = coluna;

    // Pula o primeiro '
    posicao++; 
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
        // String não fechada
        break; 
      }
      posicao++;
      coluna++;
    }

    String lexema = codigoFonte.substring(inicio, posicao);

    // verifica se a string recebeu o ', caso não retorna erro, caso tenha apenas
    // tamanho três significa que é um caracter apenas
    // caso seja maior que três e foi fechada (recebeu o ') é uma String normal
    if (!fechado) {
      erros.add(new Erro(TipoErro.STRING_NAO_FECHADA,
          "String/caractere não fechado", linhaInicio, colunaInicio));
    } else if (lexema.length() == 3) { // 'x'
      tokens.add(new Token(TipoToken.CARACTERE, lexema, linhaInicio, colunaInicio));
    } else {
      tokens.add(new Token(TipoToken.STRING_LIT, lexema, linhaInicio, colunaInicio));
    }
  }

  private void processarCaractereOuStringEntreAspasDuplas() {
   int inicio = posicao;
    int linhaInicio = linha;
    int colunaInicio = coluna;

    // Pula o primeiro '
    posicao++; 
    coluna++;

    boolean fechado = false;
    while (posicao < codigoFonte.length()) {
      if (codigoFonte.charAt(posicao) == '\"') {
        fechado = true;
        posicao++;
        coluna++;
        break;
      }
      if (codigoFonte.charAt(posicao) == '\n') {
        // String não fechada
        break; 
      }
      posicao++;
      coluna++;
    }

    String lexema = codigoFonte.substring(inicio, posicao);

    // verifica se a string recebeu o ', caso não retorna erro, caso tenha apenas
    // tamanho três significa que é um caracter apenas
    // caso seja maior que três e foi fechada (recebeu o ') é uma String normal
    if (!fechado) {
      erros.add(new Erro(TipoErro.STRING_NAO_FECHADA,
          "String/caractere não fechado", linhaInicio, colunaInicio));
    } else if (lexema.length() == 3) { // 'x'
      tokens.add(new Token(TipoToken.CARACTERE, lexema, linhaInicio, colunaInicio));
    } else {
      tokens.add(new Token(TipoToken.STRING_LIT, lexema, linhaInicio, colunaInicio));
    }
  }

  /*
   * ***************************************************************
   * Metodo: processarComentario
   * Funcao: processa os comentários
   * Parametros: vazio
   * Retorno: void
   */
  private void processarComentario() {
    int linhaInicio = linha;
    int colunaInicio = coluna;

    // Pula o {
    posicao++; 
    coluna++;

    boolean fechado = false;
    
    // os comentários serão ignorados, só irá avançar a linha e a coluna
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

    // tratamento para caso o comentário não seja fechado
    if (!fechado) {
      erros.add(new Erro(TipoErro.COMENTARIO_NAO_FECHADO,
          "Comentário não fechado", linhaInicio, colunaInicio));
    }
  }

  /*
   * ***************************************************************
   * Metodo: processarSimbolo
   * Funcao: processa os símbolos terminais mais simples. ex: +, -, ...
   * Parametros: vazio
   * Retorno: void
   */
  private void processarSimbolo() {
    char atual = codigoFonte.charAt(posicao);
    int linhaAtual = linha;
    int colunaAtual = coluna;

    // switch que controla toda a lógica da análise de simbolos
    switch (atual) {
      case '+':
        tokens.add(new Token(TipoToken.MAIS, "+", linhaAtual, colunaAtual));
        break;
      case '-':
        tokens.add(new Token(TipoToken.MENOS, "-", linhaAtual, colunaAtual));
        break;
      case '*':
        tokens.add(new Token(TipoToken.MULT, "*", linhaAtual, colunaAtual));
        break;
      case '/':
        tokens.add(new Token(TipoToken.DIVISAO, "/", linhaAtual, colunaAtual));
        break;
      case ':':
        if (posicao + 1 < codigoFonte.length() && codigoFonte.charAt(posicao + 1) == '=') {
          tokens.add(new Token(TipoToken.ATRIBUICAO, ":=", linhaAtual, colunaAtual));
          posicao++;
          coluna++;
        } else {
          tokens.add(new Token(TipoToken.DOIS_PONTOS, ":", linhaAtual, colunaAtual));
        }
        break;
      case '=':
        tokens.add(new Token(TipoToken.IGUAL, "=", linhaAtual, colunaAtual));
        break;
      case '<':
        if (posicao + 1 < codigoFonte.length()) {
          if (codigoFonte.charAt(posicao + 1) == '=') {
            tokens.add(new Token(TipoToken.MENOR_IGUAL, "<=", linhaAtual, colunaAtual));
            posicao++;
            coluna++;
          } else if (codigoFonte.charAt(posicao + 1) == '>') {
            tokens.add(new Token(TipoToken.DIFERENTE, "<>", linhaAtual, colunaAtual));
            posicao++;
            coluna++;
          } else {
            tokens.add(new Token(TipoToken.MENOR, "<", linhaAtual, colunaAtual));
          }
        } else {
          tokens.add(new Token(TipoToken.MENOR, "<", linhaAtual, colunaAtual));
        }
        break;
      case '>':
        if (posicao + 1 < codigoFonte.length() && codigoFonte.charAt(posicao + 1) == '=') {
          tokens.add(new Token(TipoToken.MAIOR_IGUAL, ">=", linhaAtual, colunaAtual));
          posicao++;
          coluna++;
        } else {
          tokens.add(new Token(TipoToken.MAIOR, ">", linhaAtual, colunaAtual));
        }
        break;
      case '.':
        tokens.add(new Token(TipoToken.PONTO, ".", linhaAtual, colunaAtual));
        break;
      case ',':
        tokens.add(new Token(TipoToken.VIRGULA, ",", linhaAtual, colunaAtual));
        break;
      case ';':
        tokens.add(new Token(TipoToken.PONTO_VIRGULA, ";", linhaAtual, colunaAtual));
        break;
      case '(':
        tokens.add(new Token(TipoToken.ABRE_PAR, "(", linhaAtual, colunaAtual));
        break;
      case ')':
        tokens.add(new Token(TipoToken.FECHA_PAR, ")", linhaAtual, colunaAtual));
        break;
      case '[':
        tokens.add(new Token(TipoToken.ABRE_COL, "[", linhaAtual, colunaAtual));
        break;
      case ']':
        tokens.add(new Token(TipoToken.FECHA_COL, "]", linhaAtual, colunaAtual));
        break;
      default:
        erros.add(new Erro(TipoErro.CARACTERE_INVALIDO,
            "Caractere inválido: " + atual, linhaAtual, colunaAtual));
    }

    posicao++;
    coluna++;
  }

  // get
  public List<Token> getTokens() {
    return tokens;
  }

  // get
  public List<Erro> getErros() {
    return erros;
  }

  // verifica se há erros encontrados
  public boolean temErros() {
    return !erros.isEmpty();
  }
}
