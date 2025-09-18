package model;

import java.util.List;
import util.AnalisadorLexico;

public class Analise {
  private AnalisadorLexico analisadorLexico;

  public Analise(String conteudo) {
    analisadorLexico = new AnalisadorLexico(conteudo);
  }

  public String getLexamas() {
    List<Token> tokens = analisadorLexico.analisar();
    StringBuilder listaLexemas = new StringBuilder();
    String maiorLexema = maiorLexema(tokens);

    for (Token token : tokens) {
      listaLexemas.append(String.format("%-" + this.calcularEspaco(maiorLexema, token) + "s %s%n", token.getLexema(), token.getTipo()));
    }

    return listaLexemas.toString();
  }

  public String getErros() {
    if (analisadorLexico.temErros()) {
      StringBuilder listaErros = new StringBuilder();
      listaErros.append("\nERROS:");

      for (Erro erro : analisadorLexico.getErros()) {
        listaErros.append(String.format("Erro: %-30s Mensagem: %-30s Linha: %d Coluna: %d%n",
            erro.getTipo(), erro.getMensagem(), erro.getLinha(), erro.getColuna()));
      }

      return listaErros.toString();
    }

    return "\nNenhum erro léxico encontrado.";
  }

  private int calcularEspaco(String maiorLexema, Token token){
    int espacos = maiorLexema.length() - token.getLexema().length();

    return espacos + 20;
  }

  private String maiorLexema(List<Token> tokens) {
    String tokenSaida = "";

    for (Token token : tokens) {
      if (token.getLexema().length() > tokenSaida.length())
        tokenSaida = token.getLexema();
    }

    return tokenSaida;
  }
}
