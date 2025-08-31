package util;

import java.util.ArrayList;
import java.util.Arrays;

import model.Token;

public class AnalisadorLexico {
  private ArrayList<Token> listaTokens;
  private ArrayList<Token> listaErros;
  private static ArrayList<String> palavrasReservadas = new ArrayList<>(Arrays.asList(
    "ABSOLUTE", "ARRAY", "BEGIN", "CASE", "CHAR", "CONST", "DIV", "DO",
    "DOWTO", "ELSE", "END", "EXTERNAL", "FILE", "FOR", "FORWARD", "FUNC",
    "FUNCTION", "GOTO", "IF", "IMPLEMENTATION", "INTEGER", "INTERFACE",
    "INTERRUPT", "LABEL", "MAIN", "NIL", "NIT", "OF", "PACKED", "PROC",
    "PROGRAM", "REAL", "RECORD", "REPEAT", "SET", "SHL", "SHR", "STRING",
    "THEN", "TO", "TYPE", "UNIT", "UNTIL", "USES", "VAR", "WHILE",
    "WITH", "XOR"
  ));

  public AnalisadorLexico() {
    this.listaTokens = new ArrayList<>();
    this.listaErros = new ArrayList<>();
  }

  public ArrayList<Token> getListaTokens() {
    return listaTokens;
  }

  public ArrayList<Token> getListaErros() {
    return listaErros;
  }

  public ArrayList<String> getPalavrasReservadas(){
    return AnalisadorLexico.palavrasReservadas;
  }
}