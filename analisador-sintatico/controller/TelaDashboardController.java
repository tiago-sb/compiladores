/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: telaDashboardController
* Funcao...........: Controla os elementos gráficos do JavaFX da 
*   tela de dashboard
*************************************************************** */

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import model.Analise;
import util.Arquivo;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaDashboardController implements Initializable {

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private TextArea codigoFonteTextArea;

  @FXML
  private ImageView analisarCodigo;

  @FXML
  private ImageView enviarCodigo;

  @FXML
  private TextArea saidaAnalisadorTextArea;

  private String formatarHistoricoTSB(java.util.List<String> historico) {
    if (historico == null || historico.isEmpty()) {
      return "Nenhum rastreio sintático disponível.\n";
    }

    StringBuilder sb = new StringBuilder();

    // O cabeçalho TSB é necessário para a Saída TSB
    sb.append("\n=== Rastreio Sintático ===\n");
    sb.append("Pilha | Cadeia | Ação\n");

    // Adiciona o conteúdo de cada passo
    for (String passo : historico) {
      sb.append(passo).append("\n");
    }

    return sb.toString();
  }

  /*
   * ***************************************************************
   * Metodo: clicouAnalisarCodigo
   * Funcao: mostra os tokens e os erros em seus respectivos textAreas
   * quando o botão é clicado
   * Parametros: MouseEvent event - evento que dispara quando o mouse
   * entra no botao enviar
   * Retorno: void
   */
  @FXML
  void clicouAnalisarCodigo(MouseEvent event) {
    String conteudo = this.codigoFonteTextArea.getText();

    Analise analise = new Analise(conteudo);
    // seta a lista de tokens no textArea da saida do analisador
    this.saidaAnalisadorTextArea.setText("=== Análise Léxica ===\n");
    this.saidaAnalisadorTextArea.appendText("Iniciando analise léxica...\n");
    this.saidaAnalisadorTextArea.appendText("Tokens reconhecidos:\n");
    this.saidaAnalisadorTextArea.appendText(analise.getLexemasToString() + "\n");

    this.saidaAnalisadorTextArea.appendText("=== Análise Sintá´tica ===\n");
    this.saidaAnalisadorTextArea.appendText("Iniciando analise léxica...\n");
    this.saidaAnalisadorTextArea.appendText(analise.getAnalise());

    String historico = formatarHistoricoTSB(analise.getHistoricoAnalise());
    this.saidaAnalisadorTextArea.appendText(historico);
  }

  /*
   * ***************************************************************
   * Metodo: clicouAnalisarCodigo
   * Funcao: mostra os tokens e os erros em seus respectivos textAreas
   * quando o botão é clicado
   * Parametros: MouseEvent event - evento que dispara quando o mouse
   * entra no botao enviar
   * Retorno: void
   */
  @FXML
  private void clicouEnviarArquivo(MouseEvent event) {
    Arquivo manipularArquivo = new Arquivo();
    String conteudo = manipularArquivo.escolherArquivo();

    if (manipularArquivo.testaConteudo(conteudo))
      return;

    this.codigoFonteTextArea.setText(conteudo);
  }

  // modifica o cursor quando entra na região do botão analisarCodigo
  @FXML
  private void entrouAnalisarCodigo(MouseEvent event) {
    this.analisarCodigo.setCursor(Cursor.HAND);
  }

  // modifica o cursor quando entra na região do botão enviarArquivo
  @FXML
  void entrouEnviarArquivo(MouseEvent event) {
    this.enviarCodigo.setCursor(Cursor.HAND);
  }

  /*
   * ***************************************************************
   * Metodo: setCodigo
   * Funcao: mostra o codigo fonte a ser analisado
   * Parametros: String codigoAnalisado
   * Retorno: void
   */
  public void setCodigo(String codigoAnalisado) {
    this.codigoFonteTextArea.setText(codigoAnalisado);
  }

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: inicializa os conteúdos iniciais da tela de dashboard
   * Parametros: String codigoAnalisado
   * Retorno: void
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Fonte personalizada
    Font fonteTextArea = Font.loadFont(getClass().getResourceAsStream("/util/font/QuanSlimRegular.ttf"), 16);

    this.codigoFonteTextArea.setFont(fonteTextArea);

    this.saidaAnalisadorTextArea.setFont(fonteTextArea);
    // impede a moficiação do conteúdo da seção do analisador
    this.saidaAnalisadorTextArea.setEditable(false);
  }
}