/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722
* Inicio...........: 12.11.2025
* Ultima alteracao.: xx.xx.2025
* Nome.............: Arquivo
* Funcao...........: possui todos os métodos necessários para entrada
*   e tratamento dos textos em .txt que serão analisados pelo analisador
*************************************************************** */

package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

public class Arquivo {
  public Arquivo(){}
  
  /*
   * ***************************************************************
   * Metodo: mostrarErro
   * Funcao: sinaliza na tela que houve erro no momento de submeter o arquivo 
   *   .txt
   * Parametros: String mensagem
   * Retorno: void
   */
  public void mostrarErro(String mensagem) {
    // cria um objeto alert 
    Alert alerta = new Alert(Alert.AlertType.ERROR);
    
    // coloca a string no corpo da mensagem 
    alerta.setTitle("Erro");
    alerta.setHeaderText(null);
    alerta.setContentText(mensagem);
    alerta.showAndWait();
  }

  /*
   * ***************************************************************
   * Metodo: escolherArquivo
   * Funcao: recebe o arquivo de texto .txt
   *   .txt
   * Parametros: vazio
   * Retorno: String
   */
  public String escolherArquivo() {
    // abre a janela para escolher o arquivo de texto
    FileChooser escolhaArquivo = new FileChooser();
    escolhaArquivo.setTitle("Selecione um arquivo .txt");
    escolhaArquivo.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Arquivos de Texto", "*.txt"));

    File arquivo = escolhaArquivo.showOpenDialog(null);

    // if que garante que caso haja erro retorna a mensagem de erro
    if (arquivo != null) {
      try {
        // pega o conteudo do .txt
        return new String(Files.readAllBytes(arquivo.toPath()), StandardCharsets.UTF_8);
      } catch (IOException e) {
        mostrarErro("Erro ao ler o arquivo: " + e.getMessage());
      }
    }

    return "";
  }

  /*
   * ***************************************************************
   * Metodo: testaConteudo
   * Funcao: verifica se o conteúdo recebido foi válido ou não
   * Parametros: vazio
   * Retorno: String
   */
  public boolean testaConteudo(String conteudo) {
    // caso não haja conteúdo mostra que houve erro
    if (conteudo == "")
      mostrarErro("Selecione um arquivo válido.");

    return conteudo == "";
  }
}
