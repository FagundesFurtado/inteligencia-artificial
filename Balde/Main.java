
import java.util.ArrayList;

public class Main {
    
    public static No buscaProfundidade(No estadoInicial) {
        if(estadoInicial.testaObjetivo()) {
            return estadoInicial;
        }
        ArrayList<No> nivel = estadoInicial.transicao();
        for(No no:nivel) {
            No solucao = buscaProfundidade(no);
            if(solucao!=null) {
                return solucao;
            }
        }
        return null;
    }
    
    public static No buscaLargura(No estadoInicial) {
        
        ArrayList<No> nivel = estadoInicial.transicao();
        while(true) {
            
            ArrayList<No> aux = new ArrayList<>();
            for(No filho:nivel) {
                if(filho.testaObjetivo()) {
                    return filho;
                }
                aux.addAll(filho.transicao());
            }
            nivel = aux;
        }
    }
    
    public static void main(String args[]) {
        
        long ti,tf;
        int cap = 200, cap2 = 181;
        No estadoInicial = new No(new Balde(cap, 0), new Balde(cap2, 0));
        ti = System.currentTimeMillis();
        No solucao = buscaLargura(estadoInicial);
        tf = System.currentTimeMillis();
        System.out.println("Solucao Larg. encontrada t = "+(tf-ti));
        while(solucao != null) {
            solucao.mostraNo();
            solucao = solucao.getPai();
        }
        
        ti = System.currentTimeMillis();
        solucao = buscaProfundidade(new No(new Balde(cap, 0), new Balde(cap2, 0)));
        tf = System.currentTimeMillis();
        System.out.println("Solucao Prof. encontrada t = "+(tf-ti));
        while(solucao != null) {
            solucao.mostraNo();
            solucao = solucao.getPai();
        }
        
        
    }
    
}
