/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722
* Inicio...........: 12.11.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: tela inicial controller
* Funcao...........: Controla os elementos gráficos do JavaFX da 
*   tela inicial
*************************************************************** */

package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.Arquivo;
import javafx.scene.Cursor;

public class TelaInicialController {

  // identificadores dos componentes do arquivo fxml
  @FXML
  private ImageView botaoEnviar;

  /*
   * ***************************************************************
   * Metodo: mouseEntrouEnviar
   * Funcao: modificar o estilo do cursor do mouse quando ele entrar
   * na regiao do botao 'enviar'
   * Parametros: MouseEvent event - evento que dispara quando o mouse
   * entra no botao enviar
   * Retorno: void
   */
  @FXML
  void mouseEntrouEnviar(MouseEvent event) {
    botaoEnviar.setCursor(Cursor.HAND);
  }

  /*
   * ***************************************************************
   * Metodo: botaoEnviarClicado
   * Funcao: carregar o fxml da tela principal da aplicao quando o
   * botao enviar for clicado
   * Parametros: MouseEvent event - evento que dispara quando o mouse
   * clica no botao enviar
   * Retorno: void
   */
  @FXML
  public void botaoEnviarClicado(MouseEvent event) throws IOException {
    // Função para escolher o arquivo de texto e retornar o conteúdo
    Arquivo manupularArquivo = new Arquivo();
    
    String conteudo = manupularArquivo.escolherArquivo();

    if (manupularArquivo.testaConteudo(conteudo)) return;

    // pega a referência da janela inicial que possui o botão enviar
    Stage palcoInicial = (Stage) botaoEnviar.getScene().getWindow();

    // carrega o arquivo FXML da tela principal
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tela_dashboard.fxml"));
    
    // aqui o FXMLLoader instancia o controller e injeta o FXML
    Parent raiz = loader.load(); 

    // pega o controller corretamente já com o TextArea injetado
    TelaDashboardController controller = loader.getController();

    // envia o código escolhido para a tela de dashboard
    controller.setCodigo(conteudo);

    // cria o novo palco da aplicação
    Stage palcoPrincipal = new Stage();
    palcoPrincipal.setTitle("Trabalho 02 - Analisador Sintático");
    palcoPrincipal.setResizable(false);
    palcoPrincipal.setScene(new Scene(raiz, 900, 650));
    palcoPrincipal.show();

    // fecha a janela inicial
    palcoInicial.close();

    // garante que todas as instâncias sejam encerradas ao fechar o palco
    palcoPrincipal.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });

    // opcional: definir ícone da aplicação
    Image icone = new Image(getClass().getResourceAsStream("/img/icone.png"));
    palcoPrincipal.getIcons().add(icone);
  }
}