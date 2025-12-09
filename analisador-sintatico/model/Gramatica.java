/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: Gramática
* Funcao...........: possui todos os métodos necessários para lidar
*   com a gramática utilizada na análise sintática
*************************************************************** */

package model;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

public class Gramatica {
  private List<Producao> producoes;
  private Map<String, Set<String>> primeiro;
  private Map<String, Set<String>> seguidor;
  private String simboloInicial;

  public Gramatica() {
    this.producoes = new ArrayList<>();
    this.primeiro = new HashMap<>();
    this.seguidor = new HashMap<>();
    this.simboloInicial = "S'";
    inicializarGramatica();
  }

  /*
   * ***************************************************************
   * Metodo: inicializarGramatica
   * Funcao: inicializa as produções da gramática, com seu devido 
   *   número, lado esquerdo e direito
   * Parametros: vazio
   * Retorno: void
   */
  private void inicializarGramatica() {
    // S' → S
    producoes.add(new Producao(0, "S'", Arrays.asList("S")));

    // S → program id ; D begin L end .
    producoes.add(new Producao(1, "S", Arrays.asList("program", "id", ";", "D", "begin", "L", "end", ".")));
    // S → program id ; begin L end .
    producoes.add(new Producao(2, "S", Arrays.asList("program", "id", ";", "begin", "L", "end", ".")));

    // D → var V
    producoes.add(new Producao(3, "D", Arrays.asList("var", "V")));

    // V → I : T ; V
    producoes.add(new Producao(4, "V", Arrays.asList("I", ":", "T", ";", "V")));
    // V → I : T ;
    producoes.add(new Producao(5, "V", Arrays.asList("I", ":", "T", ";")));

    // T → integer
    producoes.add(new Producao(6, "T", Arrays.asList("integer")));
    // T → Boolean
    producoes.add(new Producao(7, "T", Arrays.asList("Boolean")));

    // I → id
    producoes.add(new Producao(8, "I", Arrays.asList("id")));
    // I → id , I
    producoes.add(new Producao(9, "I", Arrays.asList("id", ",", "I")));

    // L → C ;
    producoes.add(new Producao(10, "L", Arrays.asList("C", ";")));
    // L → C ; L
    producoes.add(new Producao(11, "L", Arrays.asList("C", ";", "L")));

    // C → A
    producoes.add(new Producao(12, "C", Arrays.asList("A")));
    // C → R
    producoes.add(new Producao(13, "C", Arrays.asList("R")));
    // C → W
    producoes.add(new Producao(14, "C", Arrays.asList("W")));
    // C → M
    producoes.add(new Producao(15, "C", Arrays.asList("M")));
    // C → N
    producoes.add(new Producao(16, "C", Arrays.asList("N")));
    // C → P
    producoes.add(new Producao(17, "C", Arrays.asList("P")));

    // A → id := E
    producoes.add(new Producao(18, "A", Arrays.asList("id", ":=", "E")));

    // R → read ( I )
    producoes.add(new Producao(19, "R", Arrays.asList("read", "(", "I", ")")));
    // R → readln
    producoes.add(new Producao(20, "R", Arrays.asList("readln")));
    // R → readln ( I )
    producoes.add(new Producao(21, "R", Arrays.asList("readln", "(", "I", ")")));

    // W → write ( F )
    producoes.add(new Producao(22, "W", Arrays.asList("write", "(", "F", ")")));
    // W → writeln
    producoes.add(new Producao(23, "W", Arrays.asList("writeln")));
    // W → writeln ( F )
    producoes.add(new Producao(24, "W", Arrays.asList("writeln", "(", "F", ")")));

    // F → G
    producoes.add(new Producao(25, "F", Arrays.asList("G")));
    // F → G , F
    producoes.add(new Producao(26, "F", Arrays.asList("G", ",", "F")));

    // G → str
    producoes.add(new Producao(27, "G", Arrays.asList("str")));
    // G → E
    producoes.add(new Producao(28, "G", Arrays.asList("E")));

    // M → begin L end
    producoes.add(new Producao(29, "M", Arrays.asList("begin", "L", "end")));

    // N → if B then C
    producoes.add(new Producao(30, "N", Arrays.asList("if", "B", "then", "C")));
    // N → if B then C else C
    producoes.add(new Producao(31, "N", Arrays.asList("if", "B", "then", "C", "else", "C")));

    // P → while B do C
    producoes.add(new Producao(32, "P", Arrays.asList("while", "B", "do", "C")));

    // E → E + E
    producoes.add(new Producao(33, "E", Arrays.asList("E", "+", "E")));
    // E → E - E
    producoes.add(new Producao(34, "E", Arrays.asList("E", "-", "E")));
    // E → E * E
    producoes.add(new Producao(35, "E", Arrays.asList("E", "*", "E")));
    // E → E / E
    producoes.add(new Producao(36, "E", Arrays.asList("E", "/", "E")));
    // E → ( E )
    producoes.add(new Producao(37, "E", Arrays.asList("(", "E", ")")));
    // E → id
    producoes.add(new Producao(38, "E", Arrays.asList("id")));
    // E → num
    producoes.add(new Producao(39, "E", Arrays.asList("num")));

    // B → E < E
    producoes.add(new Producao(40, "B", Arrays.asList("E", "<", "E")));
    // B → E <= E
    producoes.add(new Producao(41, "B", Arrays.asList("E", "<=", "E")));
    // B → E > E
    producoes.add(new Producao(42, "B", Arrays.asList("E", ">", "E")));
    // B → E >= E
    producoes.add(new Producao(43, "B", Arrays.asList("E", ">=", "E")));
    // B → E = E
    producoes.add(new Producao(44, "B", Arrays.asList("E", "=", "E")));
    // B → E <> E
    producoes.add(new Producao(45, "B", Arrays.asList("E", "<>", "E")));
    // B → id
    producoes.add(new Producao(46, "B", Arrays.asList("id")));

    inicializarConjuntosPrimeiroSeguidor();
  }

  /*
   * ***************************************************************
   * Metodo: inicializarConjuntosPrimeiroSeguidor
   * Funcao: inicializa os conjuntos First e Follow da gramática
   * Parametros: vazio
   * Retorno: void
   */
  private void inicializarConjuntosPrimeiroSeguidor() {
    // Inicializar conjuntos FIRST
    for (Producao p : producoes) {
      primeiro.put(p.getNaoTerminal(), new HashSet<>());
    }

    // Conjuntos FIRST para terminais
    primeiro.put("program", new HashSet<>(Arrays.asList("program")));
    primeiro.put("id", new HashSet<>(Arrays.asList("id")));
    primeiro.put(";", new HashSet<>(Arrays.asList(";")));
    primeiro.put("var", new HashSet<>(Arrays.asList("var")));
    primeiro.put(":", new HashSet<>(Arrays.asList(":")));
    primeiro.put("integer", new HashSet<>(Arrays.asList("integer")));
    primeiro.put("Boolean", new HashSet<>(Arrays.asList("Boolean")));
    primeiro.put("begin", new HashSet<>(Arrays.asList("begin")));
    primeiro.put("end", new HashSet<>(Arrays.asList("end")));
    primeiro.put(".", new HashSet<>(Arrays.asList(".")));
    primeiro.put(":=", new HashSet<>(Arrays.asList(":=")));
    primeiro.put("if", new HashSet<>(Arrays.asList("if")));
    primeiro.put("then", new HashSet<>(Arrays.asList("then")));
    primeiro.put("else", new HashSet<>(Arrays.asList("else")));
    primeiro.put("while", new HashSet<>(Arrays.asList("while")));
    primeiro.put("do", new HashSet<>(Arrays.asList("do")));
    primeiro.put("read", new HashSet<>(Arrays.asList("read")));
    primeiro.put("readln", new HashSet<>(Arrays.asList("readln")));
    primeiro.put("write", new HashSet<>(Arrays.asList("write")));
    primeiro.put("writeln", new HashSet<>(Arrays.asList("writeln")));
    primeiro.put("(", new HashSet<>(Arrays.asList("(")));
    primeiro.put(")", new HashSet<>(Arrays.asList(")")));
    primeiro.put(",", new HashSet<>(Arrays.asList(",")));
    primeiro.put("str", new HashSet<>(Arrays.asList("str")));
    primeiro.put("<", new HashSet<>(Arrays.asList("<")));
    primeiro.put("<=", new HashSet<>(Arrays.asList("<=")));
    primeiro.put(">", new HashSet<>(Arrays.asList(">")));
    primeiro.put(">=", new HashSet<>(Arrays.asList(">=")));
    primeiro.put("=", new HashSet<>(Arrays.asList("=")));
    primeiro.put("<>", new HashSet<>(Arrays.asList("<>")));
    primeiro.put("+", new HashSet<>(Arrays.asList("+")));
    primeiro.put("-", new HashSet<>(Arrays.asList("-")));
    primeiro.put("*", new HashSet<>(Arrays.asList("*")));
    primeiro.put("/", new HashSet<>(Arrays.asList("/")));
    primeiro.put("num", new HashSet<>(Arrays.asList("num")));

    // Conjuntos FIRST para não-terminais (segundo sua especificação)
    primeiro.put("S", new HashSet<>(Arrays.asList("program")));
    primeiro.put("D", new HashSet<>(Arrays.asList("var")));
    primeiro.put("V", new HashSet<>(Arrays.asList("id")));
    primeiro.put("T", new HashSet<>(Arrays.asList("Boolean", "integer")));
    primeiro.put("I", new HashSet<>(Arrays.asList("id")));
    primeiro.put("L", new HashSet<>(Arrays.asList("begin", "id", "if", "read", "readln", "while", "write", "writeln")));
    primeiro.put("C", new HashSet<>(Arrays.asList("begin", "id", "if", "read", "readln", "while", "write", "writeln")));
    primeiro.put("A", new HashSet<>(Arrays.asList("id")));
    primeiro.put("R", new HashSet<>(Arrays.asList("read", "readln")));
    primeiro.put("W", new HashSet<>(Arrays.asList("write", "writeln")));
    primeiro.put("F", new HashSet<>(Arrays.asList("(", "id", "num", "str")));
    primeiro.put("G", new HashSet<>(Arrays.asList("(", "id", "num", "str")));
    primeiro.put("M", new HashSet<>(Arrays.asList("begin")));
    primeiro.put("N", new HashSet<>(Arrays.asList("if")));
    primeiro.put("P", new HashSet<>(Arrays.asList("while")));
    primeiro.put("E", new HashSet<>(Arrays.asList("(", "id", "num")));
    primeiro.put("B", new HashSet<>(Arrays.asList("(", "id", "num")));

    // Inicializar conjuntos FOLLOW
    seguidor.put("S'", new HashSet<>(Arrays.asList("$")));
    seguidor.put("S", new HashSet<>(Arrays.asList("$")));
    seguidor.put("D", new HashSet<>(Arrays.asList("begin")));
    seguidor.put("V", new HashSet<>(Arrays.asList("begin")));
    seguidor.put("T", new HashSet<>(Arrays.asList(";")));
    seguidor.put("I", new HashSet<>(Arrays.asList(")", ":")));
    seguidor.put("L", new HashSet<>(Arrays.asList("end")));
    seguidor.put("C", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("A", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("R", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("W", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("F", new HashSet<>(Arrays.asList(")")));
    seguidor.put("G", new HashSet<>(Arrays.asList(")", ",")));
    seguidor.put("M", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("N", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("P", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("E", new HashSet<>(
        Arrays.asList(")", "*", "+", ",", "-", "/", ";", "<", "<=", "<>", "=", ">", ">=", "do", "else", "then")));
    seguidor.put("B", new HashSet<>(Arrays.asList("do", "then")));
  }
  
  /*
   * ***************************************************************
   * Metodo: getProducoes
   * Funcao: retorna as produções da gramática
   * Parametros: vazio
   * Retorno: List<Producao>
   */
  public List<Producao> getProducoes() {
    return producoes;
  }

  /*
   * ***************************************************************
   * Metodo: getProducao
   * Funcao: retorna a produção que corresponde ao número passado
   *   como argumento, de acordo à numeração da gramática encontrada 
   *   em doc
   * Parametros: String numero
   * Retorno: Producao
   */
  public Producao getProducao(int numero) {
    return producoes.get(numero);
  }

  /*
   * ***************************************************************
   * Metodo: getPrimeiro
   * Funcao: retorna o conjunto First do simbolo passado como
   *   parâmetro
   * Parametros: String simbolo
   * Retorno: Set<String>
   */
  public Set<String> getPrimeiro(String simbolo) {
    return primeiro.getOrDefault(simbolo, new HashSet<>());
  }

  /*
   * ***************************************************************
   * Metodo: getSeguidor
   * Funcao: retorna o conjunto Follow do simbolo passado como
   *   parâmetro
   * Parametros: String simbolo
   * Retorno: Set<String>
   */
  public Set<String> getSeguidor(String simbolo) {
    return seguidor.getOrDefault(simbolo, new HashSet<>());
  }

  /*
   * ***************************************************************
   * Metodo: getSimboloInicial
   * Funcao: retorna o símbolo inicial da gramática
   * Parametros: vazio
   * Retorno: String
   */
  public String getSimboloInicial() {
    return simboloInicial;
  }

  /*
   * ***************************************************************
   * Metodo: isNaoTerminal
   * Funcao: verifica se o símbolo passado como argumento é um
   *   terminal ou não, a resposta é em binário
   * Parametros: String simbolo
   * Retorno: boolean
   */
  public boolean isTerminal(String simbolo) {
    return primeiro.containsKey(simbolo) && !producoes.stream()
        .anyMatch(p -> p.getNaoTerminal().equals(simbolo));
  }

  /*
   * ***************************************************************
   * Metodo: isNaoTerminal
   * Funcao: verifica se o símbolo passado como argumento é uma 
   *   variável ou não, a resposta é em binário
   * Parametros: String simbolo
   * Retorno: boolean
   */
  public boolean isNaoTerminal(String simbolo) {
    return producoes.stream().anyMatch(p -> p.getNaoTerminal().equals(simbolo));
  }

  /*
   * ***************************************************************
   * Metodo: getInfoConjuntos
   * Funcao: retorna informações a respeito da gramática 
   *   (conjuntos First, Follow)
   * Parametros: vazio
   * Retorno: String
   */
  public String getInfoConjuntos() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== CONJUNTOS FIRST ===\n");
    for (String nt : primeiro.keySet()) {
      sb.append(String.format("FIRST(%s) = %s\n", nt, primeiro.get(nt)));
    }

    sb.append("\n=== CONJUNTOS FOLLOW ===\n");
    for (String nt : seguidor.keySet()) {
      sb.append(String.format("FOLLOW(%s) = %s\n", nt, seguidor.get(nt)));
    }

    return sb.toString();
  }

}