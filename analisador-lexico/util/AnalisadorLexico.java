package util;

import java.util.ArrayList;

import model.Token;

public class AnalisadorLexico {
  ArrayList<Token> listaTokens;
  ArrayList<Token> listaErros;

  public AnalisadorLexico(){
    listaTokens = new ArrayList<>();
    listaErros = new ArrayList<>();
  }

  public ArrayList<Token> getListaTokens() {
    return listaTokens;
  }

  public ArrayList<Token> getListaErros() {
    return listaErros;
  }
}