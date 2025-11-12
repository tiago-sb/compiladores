/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722
* Inicio...........: 12.11.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: Analise
* Funcao...........: possui o aparato nececessário para receber os tokens
*   classificados na fase léxica da compilação e analisar se estão arranjados
*   seguindo os critérios estabelecidos pela análise sintática
*************************************************************** */

package model;

import java.util.List;
import util.AnalisadorLexico;
import util.AnalisadorSintatico;

public class Analise {
  private AnalisadorLexico analisadorLexico;
  private AnalisadorSintatico analisadorSintatico;

  public Analise(String conteudo) {
    analisadorLexico = new AnalisadorLexico(conteudo);
    analisadorSintatico = new AnalisadorSintatico();  
  }

  /*
   * ***************************************************************
   * Metodo: getLexamas
   * Funcao: listar todos os lexemas com seus devidos tokens observados
   * na fase da análise léxica
   * Parametros: vazio
   * Retorno: String
   */
  public String getLexamas() {
    // pega do analisador léxico os tokens que ele pegou
    List<Token> tokens = analisadorLexico.analisar();
    StringBuilder listaLexemas = new StringBuilder();
    // analisa o maior token encontrado, para ajuste da tabela final
    String maiorLexema = maiorLexema(tokens);

    // laço que adiciona os tokens em uma lista de toksn analisados com o lexema e o
    // seu devido tipo
    for (Token token : tokens) {
      listaLexemas.append(
          String.format("%-" + this.calcularEspaco(maiorLexema, token) + "s %s%n", token.getLexema(), token.getTipo()));
    }

    return listaLexemas.toString();
  }

  /*
   * ***************************************************************
   * Metodo: getErros
   * Funcao: listar todos os erros observados na fase da análise léxica
   * Parametros: vazio
   * Retorno: String
   */
  public String getErros() {
    // caso tenha erro ele lista os erros na tela
    if (analisadorLexico.temErros()) {
      StringBuilder listaErros = new StringBuilder();
      listaErros.append("\nERROS:");

      // percorre os erros encontrados adicionando eles em uma lista de erros
      for (Erro erro : analisadorLexico.getErros()) {
        listaErros.append(String.format("Erro: %-30s Mensagem: %-30s Linha: %d Coluna: %d%n",
            erro.getTipo(), erro.getMensagem(), erro.getLinha(), erro.getColuna()));
      }

      return listaErros.toString();
    }

    return "\nNenhum erro léxico encontrado.";
  }

  /*
   * ***************************************************************
   * Metodo: calcularEspaco
   * Funcao: calcula a distancia do token em questão para o maior token
   * da lista de tokens gerados em relação a tamanho, isso é para a tabela
   * final
   * Parametros: vazio
   * Retorno: int
   */
  private int calcularEspaco(String maiorLexema, Token token) {
    int espacos = maiorLexema.length() - token.getLexema().length();
    return espacos + 20;
  }

  /*
   * ***************************************************************
   * Metodo: maiorLexema
   * Funcao: retorna o maior token encontrado na lista de tokens
   * Parametros: vazio
   * Retorno: String
   */
  private String maiorLexema(List<Token> tokens) {
    String tokenSaida = "";

    for (Token token : tokens) {
      // caso o token em questão seja maior do que o maior token até então encontrado
      // o maior token é atualizado
      if (token.getLexema().length() > tokenSaida.length())
        tokenSaida = token.getLexema();
    }

    return tokenSaida;
  }
}
