
import java.util.ArrayList;


public class No {
    
    // Armazenar os filhos de um no;
    private ArrayList<No> filhos;
    
    // Referência para o nó pai
    private No pai;
    
    // Descrição de um hanoi 
    private Hanoi estado;
    
    private int nivel;
    
    public No(Hanoi e) {
        estado = e;
        pai = null;
        filhos = new ArrayList<>();
        nivel = 0;
    }
    
    // Aqui adicionamos os filhos de um no.
    public void addFilho(No filho) {
        filhos.add(filho);
        filho.pai = this;
        filho.nivel = nivel + 1;
    }
    
    public No getPai() {
        return pai;
    }
    
    public void mostraEstado() {
        System.out.printf("Nivel %d\n",nivel);
        estado.mostraHanoi();
    }
    
    
    
    // Aqui realizamos a busca em largura
    public No buscaLargura() {
        
        // Retem todos os nós em um determinado nível
        ArrayList<No> nivel = new ArrayList<>();
        
        // Aqui produzo todos os estados a partir do estado inicial
        ArrayList<Hanoi> estados = estado.transicao();
        for(Hanoi estado:estados) {
            No filho = new No(estado);
            addFilho(filho);
        }
        
        nivel = filhos;
        // Enquanto não encontrar a solucao.
        while(true) {
            ArrayList<No> nivelAux = new ArrayList<>();
            for(No filho:nivel) {
                if(filho.estado.testaObjetivo()) {
                    return filho;
                }
                estados = filho.estado.transicao();
                for(Hanoi estado:estados) {
                    No novoFilho = new No(estado);
                    filho.addFilho(novoFilho);
                    nivelAux.add(novoFilho);
                }
            }
            nivel = nivelAux;
        }
        
    }
    
    public boolean buscaProfundidade(No atual) {
        if(atual.estado.testaObjetivo()) {
            return true;
        }
        ArrayList<Hanoi> estados = atual.estado.transicao();
        
        if(estados.isEmpty()) {
            return false;
        }
        for(Hanoi estado:estados) {
            No filho = new No(estado);
            boolean encontrouObjetivo = buscaProfundidade(filho);
            if(encontrouObjetivo) {
                atual.addFilho(filho);
                return true;
            }
        }
        return false;
    }
    
    public No getFilho(int pos) {
        if(filhos.isEmpty()) {
            return null;
        }
        return filhos.get(pos);
    }
    
}
