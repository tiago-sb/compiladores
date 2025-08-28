package model;

import util.TipoToken;

public class Token {
  private String lexema;
  private TipoToken tipoToken; 
  private int linhaToken;

  public Token(){}
  
  public Token(String lexema, TipoToken tipoToken, int linhaToken){
    this.lexema = lexema;
    this.tipoToken = tipoToken;
    this.linhaToken = linhaToken;
  }

  public String getLexema() {
    return lexema;
  }

  public void setLexema(String lexema) {
    this.lexema = lexema;
  }
  
  public TipoToken getTipoToken() {
    return tipoToken;
  }
  
  public void setTipoToken(TipoToken tipoToken) {
    this.tipoToken = tipoToken;
  }
  
  public int getLinhaToken() {
    return linhaToken;
  }
  
  public void setLinhaToken(int linhaToken) {
    this.linhaToken = linhaToken;
  }
}