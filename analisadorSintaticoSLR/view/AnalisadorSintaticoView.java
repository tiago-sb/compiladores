package view;

import controller.ControladorAnalisador;
import model.Token;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorSintaticoView extends Application {
    private TextArea areaCodigo;
    private TextArea areaResultado;
    private Button botaoAnalisar;
    private Button botaoInfoGramatica;
    private ControladorAnalisador controlador;
    
    @Override
    public void start(Stage primaryStage) {
        this.controlador = new ControladorAnalisador();
        inicializarComponentes(primaryStage);
    }
    
    private void inicializarComponentes(Stage primaryStage) {
        primaryStage.setTitle("Analisador Sintatico SLR - Linguagem Sigma");
        
        // Layout principal
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Painel de entrada (Top)
        VBox painelEntrada = new VBox(10);
        painelEntrada.setPadding(new Insets(10));
        painelEntrada.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-border-radius: 5;");
        
        Label labelEntrada = new Label("Codigo Fonte:");
        labelEntrada.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        areaCodigo = new TextArea();
        areaCodigo.setPrefRowCount(15);
        areaCodigo.setPrefColumnCount(60);
        areaCodigo.setWrapText(true);
        areaCodigo.setText("program teste;\nvar x, y : integer;\nbegin\n  read(x, y);\n  write(\":\", x, y);\n  if x > y then\n    write(x + y);\n  else\n    write(x - y);\nend.");
        
        painelEntrada.getChildren().addAll(labelEntrada, areaCodigo);
        
        // Painel de resultados (Center)
        VBox painelResultado = new VBox(10);
        painelResultado.setPadding(new Insets(10));
        painelResultado.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-border-radius: 5;");
        
        Label labelResultado = new Label("Resultado da Analise:");
        labelResultado.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        areaResultado = new TextArea();
        areaResultado.setPrefRowCount(10);
        areaResultado.setPrefColumnCount(60);
        areaResultado.setWrapText(true);
        areaResultado.setEditable(false);
        areaResultado.setStyle("-fx-control-inner-background: #f5f5f5;");
        
        painelResultado.getChildren().addAll(labelResultado, areaResultado);
        
        // Painel de botoes (Bottom)
        HBox painelBotoes = new HBox(10);
        painelBotoes.setPadding(new Insets(10));
        painelBotoes.setAlignment(javafx.geometry.Pos.CENTER);
    
        botaoAnalisar = new Button("Analisar Sintaticamente");
        botaoInfoGramatica = new Button("Info Gramática");
        Button botaoTabelaSLR = new Button("Info Tabela SLR");
        Button botaoRelatorio = new Button("Relatório Completo");
    
        // Estilos
        botaoAnalisar.setStyle("-fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        botaoInfoGramatica.setStyle("-fx-font-weight: bold; -fx-background-color: #2196F3; -fx-text-fill: white;");
        botaoTabelaSLR.setStyle("-fx-font-weight: bold; -fx-background-color: #FF9800; -fx-text-fill: white;");
        botaoRelatorio.setStyle("-fx-font-weight: bold; -fx-background-color: #9C27B0; -fx-text-fill: white;");
    
        // Ações
        botaoAnalisar.setOnAction(e -> executarAnalise());
        botaoInfoGramatica.setOnAction(e -> mostrarInfoGramatica());
        botaoTabelaSLR.setOnAction(e -> mostrarTabelaSLR());
        botaoRelatorio.setOnAction(e -> mostrarRelatorioCompleto());
    
        painelBotoes.getChildren().addAll(botaoAnalisar, botaoInfoGramatica, botaoTabelaSLR, botaoRelatorio);
        
        // Configurar layout principal
        root.setTop(painelEntrada);
        root.setCenter(painelResultado);
        root.setBottom(painelBotoes);
        
        // Configurar cena
        Scene scene = new Scene(root, 800, 700);
        
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }
    
    private void executarAnalise() {
        String codigo = areaCodigo.getText();
        areaResultado.clear();
        
        if (codigo.trim().isEmpty()) {
            mostrarAlerta("Erro", "Por favor, insira algum codigo para analisar.");
            return;
        }
        
        // Simular tokens
        List<Token> tokens = simularAnaliseLexica(codigo);
        
        areaResultado.appendText("Iniciando analise sintatica...\n");
        areaResultado.appendText("Tokens reconhecidos:\n");
        for (Token token : tokens) {
            if (!token.getTipo().equals("$")) {
                areaResultado.appendText("  " + token + "\n");
            }
        }
        areaResultado.appendText("\n");
        
        try {
            boolean sucesso = controlador.analisarCodigo(tokens);
            
            if (sucesso) {
                areaResultado.appendText("[OK] Codigo sintaticamente correto!\n");
                areaResultado.setStyle("-fx-control-inner-background: #e8f5e8;");
            } else {
                areaResultado.appendText("[ERRO] Erros sintaticos encontrados!\n");
                areaResultado.appendText("Verifique a estrutura do codigo.\n");
                areaResultado.setStyle("-fx-control-inner-background: #ffebee;");
            }
        } catch (Exception e) {
            areaResultado.appendText("[ERRO] Erro durante a analise: " + e.getMessage() + "\n");
            areaResultado.setStyle("-fx-control-inner-background: #ffebee;");
            e.printStackTrace();
        }
    }
    
    private void mostrarInfoGramatica() {
        String info = controlador.getInfoGramatica();
        
        TextArea areaInfo = new TextArea();
        areaInfo.setText(info);
        areaInfo.setEditable(false);
        areaInfo.setWrapText(true);
        areaInfo.setPrefRowCount(25);
        areaInfo.setPrefColumnCount(70);
        areaInfo.setStyle("-fx-control-inner-background: #f8f9fa;");
        
        ScrollPane scrollInfo = new ScrollPane(areaInfo);
        scrollInfo.setFitToWidth(true);
        scrollInfo.setPrefViewportHeight(500);
        scrollInfo.setPrefViewportWidth(700);
        
        Stage infoStage = new Stage();
        infoStage.setTitle("Informacoes da Gramatica Sigma");
        infoStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(new Label("Gramatica Sigma - Lista de Producoes:"), scrollInfo);
        
        Scene scene = new Scene(layout, 750, 550);
        infoStage.setScene(scene);
        infoStage.showAndWait();
    }
    
    private List<Token> simularAnaliseLexica(String codigo) {
        List<Token> tokens = new ArrayList<>();
        String[] linhas = codigo.split("\n");
        
        int linha = 1;
        for (String linhaCodigo : linhas) {
            // Processamento simples de tokens
            String[] palavras = linhaCodigo.trim().split("\\s+|(?<=[();,:])|(?=[();,:])");
            
            int coluna = 1;
            for (String palavra : palavras) {
                if (palavra.isEmpty() || palavra.trim().isEmpty()) {
                    coluna += palavra.length() + 1;
                    continue;
                }
                
                String tipo = identificarTipoToken(palavra);
                tokens.add(new Token(tipo, palavra, linha, coluna));
                coluna += palavra.length() + 1;
            }
            linha++;
        }
        
        // Adicionar token de fim de arquivo
        tokens.add(new Token("$", "$", linha, 1));
        
        return tokens;
    }
    
    private String identificarTipoToken(String palavra) {
        // Remover espacos
        palavra = palavra.trim();
        
        switch (palavra) {
            case "program": return "program";
            case "var": return "var";
            case "integer": return "integer";
            case "boolean": return "boolean";
            case "begin": return "begin";
            case "end": return "end";
            case "read": return "read";
            case "readln": return "readln";
            case "write": return "write";
            case "writeln": return "writeln";
            case "if": return "if";
            case "then": return "then";
            case "else": return "else";
            case "while": return "while";
            case "do": return "do";
            case ":=": return ":=";
            case ":": return ":";
            case ";": return ";";
            case ",": return ",";
            case "(": return "(";
            case ")": return ")";
            case "+": return "+";
            case "-": return "-";
            case "*": return "*";
            case "/": return "/";
            case "<": return "<";
            case "<=": return "<=";
            case ">": return ">";
            case ">=": return ">=";
            case "=": return "=";
            case "<>": return "<>";
            case ".": return ".";
            default:
                if (palavra.matches("\".*\"")) return "str";
                if (palavra.matches("\\d+")) return "num";
                if (palavra.matches("[a-zA-Z][a-zA-Z0-9_]*")) return "id";
                return "DESCONHECIDO";
        }
    }
    
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarTabelaSLR() {
        String info = controlador.getInfoTabela();
        exibirEmNovaJanela("Tabela SLR", info);
    }

    private void mostrarRelatorioCompleto() {
        String relatorio = controlador.getRelatorioCompleto();
        exibirEmNovaJanela("Relatório Completo da Análise", relatorio);
    }

    private void exibirEmNovaJanela(String titulo, String conteudo) {
        TextArea areaTexto = new TextArea();
        areaTexto.setText(conteudo);
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setPrefRowCount(25);
        areaTexto.setPrefColumnCount(80);
        
        ScrollPane scroll = new ScrollPane(areaTexto);
        scroll.setFitToWidth(true);
        
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(new Label(titulo + ":"), scroll);
        
        Scene scene = new Scene(layout, 900, 600);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}