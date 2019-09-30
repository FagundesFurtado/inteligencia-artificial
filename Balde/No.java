
import java.util.ArrayList;


public class No {
    
    private No pai;
    private ArrayList<No> filhos;
    private Balde balde1;
    private Balde balde2;
    private int nivel;
    private boolean ehTerminal;
    
    public No(Balde b1, Balde b2) {
        balde1 = b1;
        balde2 = b2;
        nivel = 0;
        pai = null;
        filhos = new ArrayList<>();
        ehTerminal = false;
    }
    
    public void addFilho(No filho) {
        
        No aux = this;
        while(aux!=null) {
            if(aux.ehIgual(filho)) {
                filho.ehTerminal = true;
            }
            aux = aux.pai;
        }
        if(!filho.ehTerminal) {
            filhos.add(filho);
            filho.nivel = nivel+1;
            filho.pai = this;
        }
    }
    
    public boolean ehIgual(No no) {
        return(no.balde1.getQuantidade() == balde1.getQuantidade() &&
           no.balde2.getQuantidade() == balde2.getQuantidade());
    }
    
    public ArrayList<No> transicao() {
        if(ehTerminal) {
            return new ArrayList<>();
        }
        if(balde1.getQuantidade() == 0) {
            addFilho(new No(balde1.encher(), balde2));
        } else {
            addFilho(new No(balde1.esvaziar(), balde2));
            Balde baldes[] = balde1.transferir(balde2);
            addFilho(new No(baldes[0], baldes[1]));
        }
        if(balde2.getQuantidade() == 0) {
            addFilho(new No(balde1, balde2.encher()));
        } else {
            addFilho(new No(balde1, balde2.esvaziar()));
            Balde baldes[] = balde2.transferir(balde1);
            addFilho(new No(baldes[1], baldes[0]));
        }
        return filhos;
    }
    
    public void mostraNo() {
        System.out.println(balde1.getQuantidade()+","+
                balde2.getQuantidade()+" ("+nivel+")");
    }
    
    public ArrayList<No> getFilhos() {
        return filhos;
    }
    
    public No getPai() {
        return pai;
    }
    
    public boolean testaObjetivo() {
        return (balde1.getQuantidade() == 0 && 
                balde2.getQuantidade() == 35);
    }
    
}
