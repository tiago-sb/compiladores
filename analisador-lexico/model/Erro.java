package model;

public class Erro {
  private String mensagem;
  private int linha;
  private int coluna;
  private TipoErro tipo;

  public enum TipoErro {
    CARACTERE_INVALIDO,
    STRING_NAO_FECHADA,
    COMENTARIO_NAO_FECHADO,
    NUMERO_MAL_FORMADO
  }

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

  // Getters
  public String getMensagem() {
    return mensagem;
  }

  public int getLinha() {
    return linha;
  }

  public int getColuna() {
    return coluna;
  }

  public TipoErro getTipo() {
    return tipo;
  }

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
