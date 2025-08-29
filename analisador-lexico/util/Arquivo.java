package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

public class Arquivo {
  public Arquivo(){}
  
  public void mostrarErro(String mensagem) {
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
}
