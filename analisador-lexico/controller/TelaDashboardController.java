/* ***************************************************************
* Autor............: Tiago Santos Bela
* Matricula........: 202220722
* Inicio...........: 27.08.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: tela principal controller
* Funcao...........: Controla os elementos gráficos do JavaFX da 
*   tela principal
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

  @FXML
  private TextArea errosTextArea;

  @FXML
  void clicouAnalisarCodigo(MouseEvent event) {
    // String conteudo = this.codigoFonteTextArea.getText();
    this.saidaAnalisadorTextArea.setText("teste");
    this.errosTextArea.setText("teste");
  }

  @FXML
  private void clicouEnviarArquivo(MouseEvent event) {
    Arquivo manipularArquivo = new Arquivo();
    String conteudo = manipularArquivo.escolherArquivo();

    if (manipularArquivo.testaConteudo(conteudo))
      return;

    this.codigoFonteTextArea.setText(conteudo);
  }

  @FXML
  private void entrouAnalisarCodigo(MouseEvent event) {
    this.analisarCodigo.setCursor(Cursor.HAND);
  }

  @FXML
  void entrouEnviarArquivo(MouseEvent event) {
    this.enviarCodigo.setCursor(Cursor.HAND);
  }

  public void setCodigo(String codigoAnalisado) {
    this.codigoFonteTextArea.setText(codigoAnalisado);
  }

  /*
   * ***************************************************************
   * Metodo: initialize
   * Funcao: metodo que eh disparado assim que a classe
   * tela principal controller eh chamada
   * Parametros: URL url e ResourceBundle resourceBundle - localizar
   * os arquivos necessarios da janela como botoes, imagens, slides
   * , e etc
   * Retorno: void
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.saidaAnalisadorTextArea.setEditable(false);
    this.errosTextArea.setEditable(false);

    Font fonteTextArea = Font.loadFont(getClass().getResourceAsStream("/util/font/QuanSlimRegular.ttf"), 28);

    this.codigoFonteTextArea.setFont(fonteTextArea);
    this.saidaAnalisadorTextArea.setFont(fonteTextArea);
    this.errosTextArea.setFont(fonteTextArea);
  }
}
