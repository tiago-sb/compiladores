/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722
* Inicio...........: 12.11.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: Erro
* Funcao...........: possui todos os métodos necessários para lidar
*   com os erros
*************************************************************** */

package model;

import util.TipoErro;

public class Erro {
  private String mensagem;
  private int linha;
  private int coluna;
  private TipoErro tipo;

  public Erro(TipoErro tipo, String mensagem, int linha, int coluna) {
    this.tipo = tipo;
    this.mensagem = mensagem;
    this.linha = linha;
    this.coluna = coluna;
  }

  public Erro(String mensagem, int linha, int coluna) {
    this.mensagem = mensagem;
    this.linha = linha;
    this.coluna = coluna;
    this.tipo = null;
  }

  // Get
  public String getMensagem() {
    return mensagem;
  }

  // Get
  public int getLinha() {
    return linha;
  }

  // Get
  public int getColuna() {
    return coluna;
  }

  // Get
  public TipoErro getTipo() {
    return tipo;
  }

  // método toString para o objeto Token
  @Override
  public String toString() {
    if (tipo != null) {
      return String.format("Erro [%s]: %s (linha %d, coluna %d)",
          tipo, mensagem, linha, coluna);
    }
    return String.format("Erro: %s (linha %d, coluna %d)",
        mensagem, linha, coluna);
  }
}
