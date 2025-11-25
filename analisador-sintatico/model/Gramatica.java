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
    
  private void inicializarGramatica() {
      // S' → # S
      producoes.add(new Producao(0, "S'", Arrays.asList("#", "S")));
      
      // S → program id ; D begin L end .
      producoes.add(new Producao(1, "S", Arrays.asList("program", "id", ";", "D", "begin", "L", "end", ".")));
      // S → program id ; begin L end .
      producoes.add(new Producao(2, "S", Arrays.asList("program", "id", ";", "begin", "L", "end", ".")));
      
      // D → var V
      producoes.add(new Producao(3, "D", Arrays.asList("var", "V")));
      // D → ε
      producoes.add(new Producao(4, "D", Arrays.asList("ε")));
      
      // V → I : T ; V
      producoes.add(new Producao(5, "V", Arrays.asList("I", ":", "T", ";", "V")));
      // V → I : T ;
      producoes.add(new Producao(6, "V", Arrays.asList("I", ":", "T", ";")));
      
      // I → id , I
      producoes.add(new Producao(7, "I", Arrays.asList("id", ",", "I")));
      // I → id
      producoes.add(new Producao(8, "I", Arrays.asList("id")));
      
      // T → integer
      producoes.add(new Producao(9, "T", Arrays.asList("integer")));
      // T → boolean
      producoes.add(new Producao(10, "T", Arrays.asList("boolean")));
      
      // L → C ; L
      producoes.add(new Producao(11, "L", Arrays.asList("C", ";", "L")));
      // L → C ;
      producoes.add(new Producao(12, "L", Arrays.asList("C", ";")));
      
      // C → A
      producoes.add(new Producao(13, "C", Arrays.asList("A")));
      // C → M
      producoes.add(new Producao(14, "C", Arrays.asList("M")));
      // C → N
      producoes.add(new Producao(15, "C", Arrays.asList("N")));
      // C → P
      producoes.add(new Producao(16, "C", Arrays.asList("P")));
      // C → R
      producoes.add(new Producao(17, "C", Arrays.asList("R")));
      // C → W
      producoes.add(new Producao(18, "C", Arrays.asList("W")));
      
      // A → id := E
      producoes.add(new Producao(19, "A", Arrays.asList("id", ":=", "E")));
      
      // M → begin L end
      producoes.add(new Producao(20, "M", Arrays.asList("begin", "L", "end")));
      
      // N → if B then C
      producoes.add(new Producao(21, "N", Arrays.asList("if", "B", "then", "C")));
      // N → if B then C else C
      producoes.add(new Producao(22, "N", Arrays.asList("if", "B", "then", "C", "else", "C")));
      
      // P → while B do C
      producoes.add(new Producao(23, "P", Arrays.asList("while", "B", "do", "C")));
      
      // R → read ( I )
      producoes.add(new Producao(24, "R", Arrays.asList("read", "(", "I", ")")));
      // R → readln
      producoes.add(new Producao(25, "R", Arrays.asList("readln")));
      // R → readln ( I )
      producoes.add(new Producao(26, "R", Arrays.asList("readln", "(", "I", ")")));
      
      // W → write ( F )
      producoes.add(new Producao(27, "W", Arrays.asList("write", "(", "F", ")")));
      // W → writeln
      producoes.add(new Producao(28, "W", Arrays.asList("writeln")));
      // W → writeln ( F )
      producoes.add(new Producao(29, "W", Arrays.asList("writeln", "(", "F", ")")));
      
      // F → G , F
      producoes.add(new Producao(30, "F", Arrays.asList("G", ",", "F")));
      // F → G
      producoes.add(new Producao(31, "F", Arrays.asList("G")));
      
      // G → E
      producoes.add(new Producao(32, "G", Arrays.asList("E")));
      // G → str
      producoes.add(new Producao(33, "G", Arrays.asList("str")));
      
      // B → E < E
      producoes.add(new Producao(34, "B", Arrays.asList("E", "<", "E")));
      // B → E <= E
      producoes.add(new Producao(35, "B", Arrays.asList("E", "<=", "E")));
      // B → E > E
      producoes.add(new Producao(36, "B", Arrays.asList("E", ">", "E")));
      // B → E >= E
      producoes.add(new Producao(37, "B", Arrays.asList("E", ">=", "E")));
      // B → E = E
      producoes.add(new Producao(38, "B", Arrays.asList("E", "=", "E")));
      // B → E <> E
      producoes.add(new Producao(39, "B", Arrays.asList("E", "<>", "E")));
      // B → id
      producoes.add(new Producao(40, "B", Arrays.asList("id")));
      
      // E → E + E
      producoes.add(new Producao(41, "E", Arrays.asList("E", "+", "E")));
      // E → E - E
      producoes.add(new Producao(42, "E", Arrays.asList("E", "-", "E")));
      // E → E * E
      producoes.add(new Producao(43, "E", Arrays.asList("E", "*", "E")));
      // E → E / E
      producoes.add(new Producao(44, "E", Arrays.asList("E", "/", "E")));
      // E → ( E )
      producoes.add(new Producao(45, "E", Arrays.asList("(", "E", ")")));
      // E → - E
      producoes.add(new Producao(46, "E", Arrays.asList("-", "E")));
      // E → id
      producoes.add(new Producao(47, "E", Arrays.asList("id")));
      // E → num
      producoes.add(new Producao(48, "E", Arrays.asList("num")));
      
      inicializarConjuntosPrimeiroSeguidor();
  }
    
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
    primeiro.put("boolean", new HashSet<>(Arrays.asList("boolean")));
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
    primeiro.put("ε", new HashSet<>());
    
    // Inicializar conjuntos FOLLOW
    seguidor.put("S'", new HashSet<>(Arrays.asList("$")));
    seguidor.put("S", new HashSet<>(Arrays.asList("$")));
    seguidor.put("D", new HashSet<>(Arrays.asList("begin")));
    seguidor.put("V", new HashSet<>(Arrays.asList("begin")));
    seguidor.put("I", new HashSet<>(Arrays.asList(";", ")")));
    seguidor.put("T", new HashSet<>(Arrays.asList(";")));
    seguidor.put("L", new HashSet<>(Arrays.asList("end")));
    seguidor.put("C", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("A", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("R", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("W", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("F", new HashSet<>(Arrays.asList(")")));
    seguidor.put("G", new HashSet<>(Arrays.asList(",", ")")));
    seguidor.put("M", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("N", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("P", new HashSet<>(Arrays.asList(";", "else")));
    seguidor.put("E", new HashSet<>(Arrays.asList("+", "-", "*", "/", "<", "<=", ">", ">=", "=", "<>", ",", ")", ";", "else", "then", "do")));
    seguidor.put("B", new HashSet<>(Arrays.asList("then", "do")));
  }
    
  public List<Producao> getProducoes() {
    return producoes;
  }
    
  public Producao getProducao(int numero) {
    return producoes.get(numero);
  }
  
  public Set<String> getPrimeiro(String simbolo) {
    return primeiro.getOrDefault(simbolo, new HashSet<>());
  }
  
  public Set<String> getSeguidor(String simbolo) {
    return seguidor.getOrDefault(simbolo, new HashSet<>());
  }
  
  public String getSimboloInicial() {
    return simboloInicial;
  }
  
  public boolean isTerminal(String simbolo) {
    return primeiro.containsKey(simbolo) && !producoes.stream()
      .anyMatch(p -> p.getNaoTerminal().equals(simbolo));
  }
  
  public boolean isNaoTerminal(String simbolo) {
    return producoes.stream().anyMatch(p -> p.getNaoTerminal().equals(simbolo));
  }

  public String getInfoConjuntos() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== CONJUNTOS FIRST ===\n");
    for (String nt : Arrays.asList("S", "D", "I", "T", "L", "C", "A", "R", "W", "F", "G", "M", "N", "P", "E", "B")) {
      if (primeiro.containsKey(nt)) {
        sb.append(String.format("FIRST(%s) = %s\n", nt, primeiro.get(nt)));
      }
    }
      
    sb.append("\n=== CONJUNTOS FOLLOW ===\n");
    for (String nt : Arrays.asList("S'", "S", "D", "V", "T", "I", "L", "C", "A", "R", "W", "M", "N", "P", "F", "G", "E", "B")) {
      if (seguidor.containsKey(nt)) {
        sb.append(String.format("FOLLOW(%s) = %s\n", nt, seguidor.get(nt)));
      }
    }
      
    return sb.toString();
  }
}