/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: produção
* Funcao...........: possui todos os métodos necessários para lidar
*   com as produções da gramática
*************************************************************** */
package model;

import java.util.ArrayList;
import java.util.List;

public class Producao {
  private String naoTerminal;
  private List<String> simbolos;
  private int numero;
    
  public Producao(int numero, String naoTerminal, List<String> simbolos) {
    this.numero = numero;
    this.naoTerminal = naoTerminal;
    this.simbolos = new ArrayList<>(simbolos);
  }
    
  // Getters
  public String getNaoTerminal() { return naoTerminal; }
  public List<String> getSimbolos() { return simbolos; }
  public int getNumero() { return numero; }
    
  @Override
  public String toString() {
    return naoTerminal + " -> " + String.join(" ", simbolos);
  }
}