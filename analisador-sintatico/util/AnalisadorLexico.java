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
import model.Token;

public class AnalisadorLexico {
  private String codigoFonte;

  public AnalisadorLexico(String codigoFonte) {
    this.codigoFonte = codigoFonte;
  }

  public List<Token> analisar(){
    return simularAnaliseLexica(codigoFonte);
  }

  private List<Token> simularAnaliseLexica(String codigo) {
    List<Token> tokens = new ArrayList<>();
    String[] linhas = codigo.split("\n");
    
    int linha = 1;
    for (String linhaCodigo : linhas) {
      // Processamento simples de tokens
      String[] palavras = linhaCodigo.trim().split("\\s+|(?<=[();,:])|(?=[();,:])");
      
      int coluna = 1;
      for (String palavra : palavras) {
        if (palavra.isEmpty() || palavra.trim().isEmpty()) {
          coluna += palavra.length() + 1;
          continue;
        }
          
        String tipo = identificarTipoToken(palavra);
        tokens.add(new Token(tipo, palavra, linha, coluna));
        coluna += palavra.length() + 1;
      }
      linha++;
    }
    
    // Adicionar token de fim de arquivo
    tokens.add(new Token("$", "$", linha, 1));
    
    return tokens;
  }

  private String identificarTipoToken(String palavra) {
    // Remover espacos
    palavra = palavra.trim();
    
    switch (palavra) {
      case "program": return "program";
      case "var": return "var";
      case "integer": return "integer";
      case "boolean": return "boolean";
      case "begin": return "begin";
      case "end": return "end";
      case "read": return "read";
      case "readln": return "readln";
      case "write": return "write";
      case "writeln": return "writeln";
      case "if": return "if";
      case "then": return "then";
      case "else": return "else";
      case "while": return "while";
      case "do": return "do";
      case ":=": return ":=";
      case ":": return ":";
      case ";": return ";";
      case ",": return ",";
      case "(": return "(";
      case ")": return ")";
      case "+": return "+";
      case "-": return "-";
      case "*": return "*";
      case "/": return "/";
      case "<": return "<";
      case "<=": return "<=";
      case ">": return ">";
      case ">=": return ">=";
      case "=": return "=";
      case "<>": return "<>";
      case ".": return ".";
      default:
        if (palavra.matches("\".*\"")) return "str";
        if (palavra.matches("\\d+")) return "num";
        if (palavra.matches("[a-zA-Z][a-zA-Z0-9_]*")) return "id";
        return "DESCONHECIDO";
    }
  }
}
