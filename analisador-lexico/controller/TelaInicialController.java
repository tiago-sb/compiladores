/* ***************************************************************
* Autor............: Tiago Santos Bela
* Matricula........: 202220722
* Inicio...........: 27.08.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: tela inicial controller
* Funcao...........: Controla os elementos gráficos do JavaFX da 
*   tela inicial
*************************************************************** */

package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
// import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

  void mostrarErro(String mensagem) {
    Alert alerta = new Alert(Alert.AlertType.ERROR);
    alerta.setTitle("Erro");
    alerta.setHeaderText(null);
    alerta.setContentText(mensagem);
    alerta.showAndWait();
  }

  public String escolherArquivo() {
    FileChooser escolhaArquivo = new FileChooser();
    escolhaArquivo.setTitle("Selecione um arquivo .txt");
    escolhaArquivo.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Arquivos de Texto", "*.txt"));

    File arquivo = escolhaArquivo.showOpenDialog(null);

    if (arquivo != null) {
      try {
        return new String(Files.readAllBytes(arquivo.toPath()), StandardCharsets.UTF_8);
      } catch (IOException e) {
        mostrarErro("Erro ao ler o arquivo: " + e.getMessage());
      }
    }

    return "";
  }

  public boolean testaConteudo(String conteudo) {
    if (conteudo == "")
      mostrarErro("Selecione um arquivo válido.");

    return conteudo == "";
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
    String conteudo = escolherArquivo();

    if (testaConteudo(conteudo))
      return;

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
    palcoPrincipal.setTitle("Trabalho 01 - Analisador Léxico");
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
    // Image icone = new Image(getClass().getResourceAsStream("/img/logo.png"));
    // palcoPrincipal.getIcons().add(icone);
  }
}