/* ***************************************************************
* Autor............: Tiago Santos Bela
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: analisador léxico
* Funcao...........: Controla a lógica central do analisador léxico
*************************************************************** */

package util;

import java.util.*;
import model.Token;

public class AnalisadorLexico {
  private String codigoFonte;

  public AnalisadorLexico(String codigoFonte) {
    this.codigoFonte = codigoFonte;
  }

  public List<Token> analisar() {
    return simularAnaliseLexica(codigoFonte);
  }

  /*
   * ***************************************************************
   * Metodo: simularAnaliseLexica
   * Funcao: lógica central de funcionamento do analisador léxico, 
   *   categoriza os lexemas
   * Parametros: String codigo
   * Retorno: List<Token>
   */
  private List<Token> simularAnaliseLexica(String codigo) {
    List<Token> tokens = new ArrayList<>();
    String[] linhas = codigo.split("\n");

    int linha = 1;

    for (String linhaCodigo : linhas) {
      String[] palavras = linhaCodigo.trim().split("\\s+|(?<=[();,:])|(?=[();,:])");

      int coluna = 1;
      for (int i = 0; i < palavras.length; i++) {
        String palavra = palavras[i];

        if (palavra.isEmpty()) {
          coluna += 1;
          continue;
        }

        // Tratamento especial para end.
        if (palavra.equals("end.")) {
          tokens.add(new Token("end", "end", linha, coluna));
          tokens.add(new Token(".", ".", linha, coluna + 3));
          coluna += palavra.length() + 1;
          continue;
        }

        if (palavra.equals("'")) {
          int startCol = coluna;
          StringBuilder sb = new StringBuilder();
          sb.append("'");

          i++;
          while (i < palavras.length && !palavras[i].equals("'")) {
            sb.append(palavras[i]);
            // adiciona espaço entre palavras do conteúdo
            if (i + 1 < palavras.length && !palavras[i + 1].equals("'")) {
              sb.append(" ");
            }
            coluna += palavras[i].length() + 1;
            i++;
          }

          // fecha aspas se encontrou
          if (i < palavras.length && palavras[i].equals("'")) {
            sb.append("'");
            coluna += 2; // conta o apóstrofo de fechamento
          }

          tokens.add(new Token("str", sb.toString(), linha, startCol));
          continue;
        }

        // Caso já venha completo 'conteudo'
        if (palavra.startsWith("'") && palavra.endsWith("'") && palavra.length() >= 2) {
          tokens.add(new Token("str", palavra, linha, coluna));
          coluna += palavra.length() + 1;
          continue;
        }

        String tipo = identificarTipoToken(palavra);
        tokens.add(new Token(tipo, palavra, linha, coluna));
        coluna += palavra.length() + 1;
      }
      linha++;
    }

    tokens.add(new Token("$", "$", linha, 1));
    return tokens;
  }

  /*
   * ***************************************************************
   * Metodo: identificarTipoToken
   * Funcao: mapeia corretamente o tipo do token, útil para o código
   *   poder estar adequado com que é exigido
   * Parametros: String palavra
   * Retorno: String
   */
  private String identificarTipoToken(String palavra) {
    palavra = palavra.trim();

    switch (palavra) {
      case "program":
        return "program";
      case "var":
        return "var";
      case "integer":
        return "integer";
      case "boolean":
        return "boolean";
      case "begin":
        return "begin";
      case "end":
        return "end";
      case "read":
        return "read";
      case "readln":
        return "readln";
      case "write":
        return "write";
      case "writeln":
        return "writeln";
      case "if":
        return "if";
      case "then":
        return "then";
      case "else":
        return "else";
      case "while":
        return "while";
      case "do":
        return "do";
      case ":=":
        return ":=";
      case ":":
        return ":";
      case ";":
        return ";";
      case ",":
        return ",";
      case "(":
        return "(";
      case ")":
        return ")";
      case "+":
        return "+";
      case "-":
        return "-";
      case "*":
        return "*";
      case "/":
        return "/";
      case "<":
        return "<";
      case "<=":
        return "<=";
      case ">":
        return ">";
      case ">=":
        return ">=";
      case "=":
        return "=";
      case "<>":
        return "<>";
      case ".":
        return ".";
      default:
        // Strings entre apóstrofos
        if (palavra.matches("'[^']*'"))
          return "str";
        if (palavra.matches("\\d+"))
          return "num";
        if (palavra.matches("[a-zA-Z][a-zA-Z0-9_]*"))
          return "id";
        return "DESCONHECIDO";
    }
  }
}