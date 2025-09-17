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
    int maiorLexema = maiorLexema(tokens);

    for (Token token : tokens) {
      if(token.getLexema().length() < maiorLexema){
        listaLexemas.append(String.format("%-" + maiorLexema + "s %s%n", token.getLexema(), token.getTipo()));
      } else {
        listaLexemas.append(String.format("%s %s%n", token.getLexema(), token.getTipo()));
      }
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

  private int maiorLexema(List<Token> tokens) {
    int max = 0;
    for (Token token : tokens) {
      if (token.getLexema().length() > max)
        max = token.getLexema().length();
    }

    return max;
  }
}
