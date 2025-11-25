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
   * Retorno: String
   */
  public List<Token> getLexemas() {
    if (conteudo.trim().isEmpty()) {
      return null;
    }
    
    List<Token> tokens = analisadorLexico.analisar();
    
    return tokens;
  }

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

  public boolean analisarCodigo(List<Token> tokens) {
    return analisadorSintatico.analisar(tokens);
  }
    
  public String getInfoGramatica() {
    return gramatica.getInfoConjuntos();
  }
    
  public String getRelatorioCompleto() {
    return analisadorSintatico.getRelatorioAnalise();
  }
    
  public List<String> getHistoricoAnalise() {
    return analisadorSintatico.getHistorico();
  }
    
  public Gramatica getGramatica() {
    return gramatica;
  }
    
  public String getInfoTabela() {
    return tabela.getInfoTabela();
  }
}
