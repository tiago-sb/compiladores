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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaDashboardController implements Initializable {

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private TextArea codigoFonteTextArea;

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
  }
}
