public class Balde {
    
    // Capacidade total do balde;
    private int capacidade;
    
    // Quantidade de água no balde;
    private int quantidade;
    
    public Balde(int capacidade, int quantidade) {
        this.capacidade = capacidade;
        this.quantidade = quantidade;
    }
    
    public Balde encher() {
        Balde balde = clone();
        balde.quantidade = balde.capacidade;
        return balde;
    }
    
    public Balde esvaziar() {
        Balde balde = clone();
        balde.quantidade = 0;
        return balde;
    }
    
    public Balde[] transferir(Balde baldeCand) {
        Balde balde1 = clone();
        Balde balde2 = baldeCand.clone();
        int falta = balde2.capacidade-balde2.quantidade;
        if(falta <= balde1.quantidade){
            balde2.quantidade += falta;
            balde1.quantidade -= falta;
        } else {
            balde2.quantidade = balde1.quantidade;
            balde1.quantidade = 0;
        }
        return new Balde[]{balde1,balde2};
    }
    
    public Balde clone() {
        Balde clone = new Balde(capacidade, quantidade);
        return clone;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    
    
   
    
}
