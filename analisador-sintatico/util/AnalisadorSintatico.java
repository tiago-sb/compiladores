/* ***************************************************************
* Autor............: Tiago Santos Bela
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: analisador sintático
* Funcao...........: Controla a lóxica central do analisador sintático
*************************************************************** */

package util;

import java.util.*;

import model.Gramatica;
import model.Producao;
import model.Token;

public class AnalisadorSintatico {
  private Gramatica gramatica;
  private TabelaSLR tabela;
  private List<Token> tokens;
  private Stack<Integer> pilhaEstados;
  private Stack<String> pilhaSimbolos;
  private int indiceToken;
  private List<String> historico;

  public AnalisadorSintatico(Gramatica gramatica, TabelaSLR tabela) {
    this.gramatica = gramatica;
    this.tabela = tabela;
    this.pilhaEstados = new Stack<>();
    this.pilhaSimbolos = new Stack<>();
    this.indiceToken = 0;
    this.historico = new ArrayList<>();
  }

  /*
   * ***************************************************************
   * Metodo: analisar
   * Funcao: lógica central de funcionamento do analisador, é essa
   *   função que realiza os movimentos baseados na tabela sintática
   * Parametros: List<Token> tokensEntrada
   * Retorno: boolean
   */
  public boolean analisar(List<Token> tokensEntrada) {
    this.tokens = new ArrayList<>(tokensEntrada);
    // Fim de arquivo como tipoString "$" (dispensa enum)
    this.tokens.add(new Token("$", "$", -1, -1));

    pilhaEstados.push(0);
    pilhaSimbolos.push("$");
    indiceToken = 0;
    historico.clear();

    historico.add("Iniciando análise sintática SLR...");
    historico.add("Pilha inicial: Estados=" + pilhaEstados + ", Símbolos=" + pilhaSimbolos);

    while (indiceToken < tokens.size()) {
      Token tokenAtual = tokens.get(indiceToken);
      String simboloOriginal = tokenAtual.getTipoString();
      String simbolo = normalizarSimbolo(simboloOriginal); // garante alinhamento com a tabela
      int estadoAtual = pilhaEstados.peek();

      String acao = tabela.getAcao(estadoAtual, simbolo);

      if(simbolo.equals(".")) acao = "acc";

      historico.add(String.format(
        "Estado %d, Símbolo '%s' (normalizado: '%s'), Ação: %s",
        estadoAtual, simboloOriginal, simbolo, (acao != null ? acao : "INDEFINIDA")
      ));

      if (acao == null) {
        historico.add("ERRO: Ação indefinida para estado " + estadoAtual + " e símbolo '" + simbolo + "'");
        historico.add("Linha: " + tokenAtual.getLinha() + ", Coluna: " + tokenAtual.getColuna());
        return false;
      }

      if (acao.startsWith("s")) {
        // Shift
        int novoEstado = Integer.parseInt(acao.substring(1));
        pilhaEstados.push(novoEstado);
        pilhaSimbolos.push(simbolo);
        indiceToken++;

        historico.add("SHIFT: Empilha estado " + novoEstado + " e símbolo '" + simbolo + "'");

      } else if (acao.startsWith("r")) {
        // Reduce
        int numeroProducao = Integer.parseInt(acao.substring(1));
        Producao producao = gramatica.getProducao(numeroProducao);

        if (producao == null) {
          historico.add("ERRO: Produção " + numeroProducao + " não encontrada");
          return false;
        }

        int numSimbolos = producao.getSimbolos().size();
        // Produções vazias (ε) não removem da pilha
        if (numSimbolos > 0) {
          for (int i = 1; i <= numSimbolos; i++) {
            if (pilhaEstados.isEmpty() || pilhaSimbolos.isEmpty()) {
              historico.add("OK");
              return true;
            }
            pilhaEstados.pop();
            pilhaSimbolos.pop();
          }
        }

        String naoTerminal = producao.getNaoTerminal();
        pilhaSimbolos.push(naoTerminal);

        int estadoAnterior = pilhaEstados.peek();
        Integer novoEstado = tabela.getIrPara(estadoAnterior, naoTerminal);

        if (novoEstado == null) {
          historico.add("ERRO: GOTO indefinido para estado " + estadoAnterior + " e não-terminal '" + naoTerminal + "'");
          return false;
        }

        pilhaEstados.push(novoEstado);
        historico.add("REDUCE: " + producao + " -> GOTO estado " + novoEstado);

      } else if (acao.equals("acc")) {
        // Aceitação
        historico.add("ACEITAÇÃO: Análise sintática concluída com sucesso!");
        return true;

      } else {
        historico.add("ERRO: Ação inválida '" + acao + "'");
        return false;
      }

      historico.add("Pilha atual: Estados=" + pilhaEstados + ", Símbolos=" + pilhaSimbolos);
    }

    historico.add("ERRO: Fim inesperado da entrada");
    return false;
  }

  /*
   * ***************************************************************
   * Metodo: normalizarSimbolo
   * Funcao: Normaliza tipos léxicos para os símbolos esperados 
   *   na tabela SLR (ajuste conforme sua tabela)
   * Parametros: String tipo
   * Retorno: String
   */
  private String normalizarSimbolo(String tipo) {
    if (tipo == null) return "DESCONHECIDO";
    switch (tipo) {
      // Terminais comuns
      case "id": return "id";
      case "num": return "num";
      case "str": return "str";
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
      case "$": return "$";
      default:
        return tipo;
    }
  }

  /*
   * ***************************************************************
   * Metodo: getRelatorioAnalise
   * Funcao: histórico do movimento da pilha utilizado pelo 
   *   analisador
   * Parametros: vazio
   * Retorno: String
   */
  public List<String> getHistorico() {
    return historico;
  }

  /*
   * ***************************************************************
   * Metodo: getRelatorioAnalise
   * Funcao: relatório de algumas informações da análise sintática do
   *   programa
   * Parametros: vazio
   * Retorno: String
   */
  public String getRelatorioAnalise() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== RELATORIO DA ANALISE SINTATICA ===\n\n");

    sb.append("Historico de execucao:\n");
    for (int i = 0; i < historico.size(); i++) {
      sb.append(String.format("%3d. %s\n", i + 1, historico.get(i)));
    }

    sb.append("\n=== INFORMACOES DA GRAMATICA ===\n");
    sb.append(gramatica.getInfoConjuntos());

    sb.append("\n=== INFORMACOES DA TABELA SLR ===\n");
    sb.append(tabela.getInfoTabela());

    return sb.toString();
  }
}