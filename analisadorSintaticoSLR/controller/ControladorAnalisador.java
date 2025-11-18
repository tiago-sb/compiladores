package controller;

import model.*;
import java.util.List;

public class ControladorAnalisador {
    private GramaticaSigma gramatica;
    private TabelaSLR tabela;
    private AnalisadorSintaticoSLR analisador;
    
    public ControladorAnalisador() {
        this.gramatica = new GramaticaSigma();
        this.tabela = new TabelaSLR(gramatica);
        this.analisador = new AnalisadorSintaticoSLR(gramatica, tabela);
    }
    
    public boolean analisarCodigo(List<Token> tokens) {
        return analisador.analisar(tokens);
    }
    
    public String getInfoGramatica() {
        return gramatica.getInfoConjuntos();
    }
    
    public String getRelatorioCompleto() {
        return analisador.getRelatorioAnalise();
    }
    
    public List<String> getHistoricoAnalise() {
        return analisador.getHistorico();
    }
    
    public GramaticaSigma getGramatica() {
        return gramatica;
    }
    
    public String getInfoTabela() {
        return tabela.getInfoTabela();
    }
}