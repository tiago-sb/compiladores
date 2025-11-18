package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemLR {
    private Producao producao;
    private int posicaoPonto;
    private String simboloLookahead;
    
    public ItemLR(Producao producao, int posicaoPonto, String simboloLookahead) {
        this.producao = producao;
        this.posicaoPonto = posicaoPonto;
        this.simboloLookahead = simboloLookahead;
    }
    
    // Getters
    public Producao getProducao() { return producao; }
    public int getPosicaoPonto() { return posicaoPonto; }
    public String getSimboloLookahead() { return simboloLookahead; }
    
    public String getSimboloAposPonto() {
        if (posicaoPonto < producao.getSimbolos().size()) {
            return producao.getSimbolos().get(posicaoPonto);
        }
        return null;
    }
    
    public ItemLR avancarPonto() {
        return new ItemLR(producao, posicaoPonto + 1, simboloLookahead);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemLR item = (ItemLR) obj;
        return posicaoPonto == item.posicaoPonto &&
               producao.equals(item.producao) &&
               Objects.equals(simboloLookahead, item.simboloLookahead);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(producao, posicaoPonto, simboloLookahead);
    }
    
    @Override
    public String toString() {
        List<String> simbolos = producao.getSimbolos();
        List<String> comPonto = new ArrayList<>(simbolos);
        comPonto.add(posicaoPonto, ".");
        return producao.getNaoTerminal() + " -> " + String.join(" ", comPonto) + 
               (simboloLookahead != null ? ", " + simboloLookahead : "");
    }
}