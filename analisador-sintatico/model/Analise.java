/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: Análise
* Funcao...........: possui o aparato nececessário para receber os tokens
*   classificados na fase léxica da compilação e analisar se estão 
*   arranjados seguindo os critérios estabelecidos pela análise sintática
*************************************************************** */

package model;

import java.util.List;
import util.AnalisadorLexico;
import util.AnalisadorSintatico;
import util.TabelaSLR;

public class Analise {
  private AnalisadorLexico analisadorLexico;
  private AnalisadorSintatico analisadorSintatico;
  private Gramatica gramatica;
  private TabelaSLR tabela;
  private String conteudo;

  public Analise(String conteudo) {
    this.analisadorLexico = new AnalisadorLexico(conteudo);
    this.conteudo = conteudo;
    this.gramatica = new Gramatica();
    this.tabela = new TabelaSLR(gramatica);
    this.analisadorSintatico = new AnalisadorSintatico(gramatica, tabela);
  }

  /*
   * ***************************************************************
   * Metodo: getLexemas
   * Funcao: listar todos os lexemas com seus devidos tokens observados
   * na fase da análise léxica
   * Parametros: vazio
   * Retorno: List<Token>
   */
  public List<Token> getLexemas() {
    if (conteudo.trim().isEmpty()) {
      return null;
    }
    
    List<Token> tokens = analisadorLexico.analisar();
    
    return tokens;
  }

  /*
   * ***************************************************************
   * Metodo: getAnalise
   * Funcao: puxa a análise sintática, verifica se está tudo bem
   *   e retorna um texto explicatívo (Ok ou Erro)
   * Parametros: vazio
   * Retorno: String
   */
  public String getAnalise(){
    List<Token> tokens = this.getLexemas();
    String mensagem = "";
    try {
      boolean sucesso = this.analisarCodigo(tokens);
      
      if (sucesso) { mensagem = "[OK] Codigo sintaticamente correto!\n"; } 
      else { mensagem = "[ERRO] Erros sintaticos encontrados!\n Verifique a estrutura do codigo."; }
    } catch (Exception e) {
      mensagem = "[ERRO] Erro durante a analise: " + e.getMessage() + "\n";
      e.printStackTrace();
    }

    return mensagem;
  }

  /*
   * ***************************************************************
   * Metodo: getLexemasToString
   * Funcao: retorna a lista de lexemas do programa de entrada no
   *   formato de String
   * Parametros: vazio
   * Retorno: String
   */
  public String getLexemasToString(){
    List<Token> tokens = this.getLexemas();
    StringBuilder listaLexemas = new StringBuilder();
    
    for (Token token : tokens) {
        if (!token.getTipo().equals("$")) {
          listaLexemas.append("  " + token + "\n");
      }
    }

    return listaLexemas.toString();
  }

  /*
   * ***************************************************************
   * Metodo: analisarCodigo
   * Funcao: puxa a análise sintática, verifica se está tudo bem e
   *   retorna o boolean com o resultado
   * Parametros: List<Token> tokens
   * Retorno: boolean
   */
  public boolean analisarCodigo(List<Token> tokens) {
    return analisadorSintatico.analisar(tokens);
  }
  
   /*
   * ***************************************************************
   * Metodo: getInfoGramatica
   * Funcao: retorna informações a respeito da gramática 
   *   (conjuntos First, Follow)
   * Parametros: vazio
   * Retorno: String
   */
  public String getInfoGramatica() {
    return gramatica.getInfoConjuntos();
  }
  
  /*
   * ***************************************************************
   * Metodo: getRelatorioCompleto
   * Funcao: relatório de algumas informações da análise sintática do
   *   programa
   * Parametros: vazio
   * Retorno: String
   */
  public String getRelatorioCompleto() {
    return analisadorSintatico.getRelatorioAnalise();
  }

  /*
   * ***************************************************************
   * Metodo: getHistoricoAnalise
   * Funcao: histórico do movimento da pilha utilizado pelo 
   *   analisador
   * Parametros: vazio
   * Retorno: List<String>
   */
  public List<String> getHistoricoAnalise() {
    return analisadorSintatico.getHistorico();
  }
  
  /*
   * ***************************************************************
   * Metodo: getGramatica
   * Funcao: retorna a gramática completa
   * Parametros: vazio
   * Retorno: Gramatica
   */
  public Gramatica getGramatica() {
    return gramatica;
  }
  
  /*
   * ***************************************************************
   * Metodo: getInfoTabela
   * Funcao: retorna informações relevantes da tabela sintática
   * Parametros: vazio
   * Retorno: String
   */
  public String getInfoTabela() {
    return tabela.getInfoTabela();
  }
}
