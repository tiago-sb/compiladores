package model;

import util.TipoErro;

public class Erro {
  private TipoErro tipoErro;
  private String mensagemErro;
  private int linhaErro;

  public Erro(){}

  public Erro(TipoErro tipoErro, String mensagemErro, int linhaErro){
    this.tipoErro = tipoErro;
    this.mensagemErro = mensagemErro;
    this.linhaErro = linhaErro;
  }

  public TipoErro getTipoErro() {
    return tipoErro;
  }

  public void setTipoErro(TipoErro tipoErro) {
    this.tipoErro = tipoErro;
  }

  public String getMensagemErro() {
    return mensagemErro;
  }

  public void setMensagemErro(String mensagemErro) {
    this.mensagemErro = mensagemErro;
  }

  public int getLinhaErro() {
    return linhaErro;
  }

  public void setLinhaErro(int linhaErro) {
    this.linhaErro = linhaErro;
  }
}