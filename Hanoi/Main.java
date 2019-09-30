
import java.util.ArrayList;


public class Main {

    public static void main(String args[]) {
        Hanoi hanoi3 = new Hanoi(5);
        hanoi3.mostraHanoi();
        
        No raiz = new No(hanoi3);
        No solucao = raiz.buscaLargura();
        boolean encontrou = raiz.buscaProfundidade(raiz);
        if(encontrou) {
            solucao = raiz;
        }
        
        while(solucao!=null) {
            solucao.mostraEstado();
            solucao = solucao.getFilho(0);
        }
        
    }
    
}