/* ***************************************************************
* Autor............: Tiago Santos Bela
* Matricula........: 202220722
* Inicio...........: 27.08.2025
* Ultima alteracao.: 18.09.2025
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
import javafx.scene.text.Font;
import model.Analise;
import util.Arquivo;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaDashboardController implements Initializable {
  @FXML
  private TextArea codigoFonteTextArea;

  @FXML
  private ImageView analisarCodigo;

  @FXML
  private ImageView enviarCodigo;

  @FXML
  private TextArea saidaAnalisadorTextArea;

  /*
   * ***************************************************************
   * Metodo: clicouAnalisarCodigo
   * Funcao: mostra os tokens e os erros em seus respectivos textAreas
   *   quando o botão é clicado
   * Parametros: MouseEvent event - evento que dispara quando o mouse
   * entra no botao enviar
   * Retorno: void
   */
  @FXML
  void clicouAnalisarCodigo(MouseEvent event) {
    String conteudo = this.codigoFonteTextArea.getText();

    Analise analise = new Analise(conteudo);

    // seta a lista de tokens no textArea da saida do analisador
    this.saidaAnalisadorTextArea.setText(analise.getLexamas());
  }

  /*
   * ***************************************************************
   * Metodo: clicouAnalisarCodigo
   * Funcao: mostra os tokens e os erros em seus respectivos textAreas
   *   quando o botão é clicado
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