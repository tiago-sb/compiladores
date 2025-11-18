package model;

import java.util.*;

public class AnalisadorSintaticoSLR {
    private GramaticaSigma gramatica;
    private TabelaSLR tabela;
    private List<Token> tokens;
    private Stack<Integer> pilhaEstados;
    private Stack<String> pilhaSimbolos;
    private int indiceToken;
    private List<String> historico;
    
    public AnalisadorSintaticoSLR(GramaticaSigma gramatica, TabelaSLR tabela) {
        this.gramatica = gramatica;
        this.tabela = tabela;
        this.pilhaEstados = new Stack<>();
        this.pilhaSimbolos = new Stack<>();
        this.indiceToken = 0;
        this.historico = new ArrayList<>();
    }
    
    public boolean analisar(List<Token> tokensEntrada) {
        this.tokens = new ArrayList<>(tokensEntrada);
        this.tokens.add(new Token("$", "$", -1, -1));
        
        pilhaEstados.push(0);
        pilhaSimbolos.push("$");
        indiceToken = 0;
        historico.clear();
        
        historico.add("Iniciando análise sintática SLR...");
        historico.add("Pilha inicial: Estados=" + pilhaEstados + ", Símbolos=" + pilhaSimbolos);
        
        while (indiceToken < tokens.size()) {
            Token tokenAtual = tokens.get(indiceToken);
            String simbolo = tokenAtual.getTipo();
            int estadoAtual = pilhaEstados.peek();
            
            String acao = tabela.getAcao(estadoAtual, simbolo);
            
            historico.add(String.format("Estado %d, Símbolo '%s', Ação: %s", 
                estadoAtual, simbolo, (acao != null ? acao : "INDEFINIDA")));
            
            if (acao == null) {
                historico.add("ERRO: Ação indefinida para estado " + estadoAtual + " e símbolo '" + simbolo + "'");
                historico.add("Linha: " + tokenAtual.getLinha() + ", Coluna: " + tokenAtual.getColuna());
                return false;
            }
            
            if (acao.startsWith("s")) {
                // Shift
                int novoEstado = Integer.parseInt(acao.substring(1));
                pilhaEstados.push(novoEstado);
                pilhaSimbolos.push(simbolo);
                indiceToken++;
                
                historico.add("SHIFT: Empilha estado " + novoEstado + " e símbolo '" + simbolo + "'");
                
            } else if (acao.startsWith("r")) {
                // Reduz
                int numeroProducao = Integer.parseInt(acao.substring(1));
                Producao producao = gramatica.getProducao(numeroProducao);
                
                if (producao == null) {
                    historico.add("ERRO: Produção " + numeroProducao + " não encontrada");
                    return false;
                }
                
                // Remover os símbolos da pilha
                int numSimbolos = producao.getSimbolos().size();
                for (int i = 0; i < numSimbolos; i++) {
                    pilhaEstados.pop();
                    pilhaSimbolos.pop();
                }
                
                // Empurrar o não-terminal reduzido
                String naoTerminal = producao.getNaoTerminal();
                pilhaSimbolos.push(naoTerminal);
                
                // Obter novo estado do GOTO
                int estadoAnterior = pilhaEstados.peek();
                Integer novoEstado = tabela.getIrPara(estadoAnterior, naoTerminal);
                
                if (novoEstado == null) {
                    historico.add("ERRO: GOTO indefinido para estado " + estadoAnterior + " e não-terminal '" + naoTerminal + "'");
                    return false;
                }
                
                pilhaEstados.push(novoEstado);
                historico.add("REDUCE: " + producao + " → GOTO estado " + novoEstado);
                
            } else if (acao.equals("acc")) {
                // Aceitação
                historico.add("ACEITAÇÃO: Análise sintática concluída com sucesso!");
                return true;
                
            } else {
                historico.add("ERRO: Ação inválida '" + acao + "'");
                return false;
            }
            
            historico.add("Pilha atual: Estados=" + pilhaEstados + ", Símbolos=" + pilhaSimbolos);
        }
        
        historico.add("ERRO: Fim inesperado da entrada");
        return false;
    }
    
    public List<String> getHistorico() {
        return historico;
    }
    
    public String getRelatorioAnalise() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATORIO DA ANALISE SINTATICA ===\n\n");
        
        sb.append("Historico de execucao:\n");
        for (int i = 0; i < historico.size(); i++) {
            sb.append(String.format("%3d. %s\n", i + 1, historico.get(i)));
        }
        
        sb.append("\n=== INFORMACOES DA GRAMATICA ===\n");
        sb.append(gramatica.getInfoConjuntos());
        
        sb.append("\n=== INFORMACOES DA TABELA SLR ===\n");
        sb.append(tabela.getInfoTabela());
        
        return sb.toString();
    }
}