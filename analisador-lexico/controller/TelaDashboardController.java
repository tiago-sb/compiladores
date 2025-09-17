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

  @FXML
  private TextArea errosTextArea;

  @FXML
  void clicouAnalisarCodigo(MouseEvent event) {
    String conteudo = this.codigoFonteTextArea.getText();

    Analise analise = new Analise(conteudo);

    this.saidaAnalisadorTextArea.setText(analise.getLexamas());
    this.errosTextArea.setText(analise.getErros());
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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Fonte personalizada
    Font fonteTextArea = Font.loadFont(getClass().getResourceAsStream("/util/font/QuanSlimRegular.ttf"), 16);

    this.codigoFonteTextArea.setFont(fonteTextArea);
    
    this.saidaAnalisadorTextArea.setFont(fonteTextArea);
    this.saidaAnalisadorTextArea.setEditable(false);
    
    this.errosTextArea.setFont(fonteTextArea);
    this.errosTextArea.setEditable(false);
  }
}