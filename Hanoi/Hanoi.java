
import java.util.ArrayList;

/*  Class para representar um estado do hanoi. 
    A ideia é que cada pino do hanoi seja representado
    por um pilha. Na pilha colocamos valores inteiros
    que correspondem ao diâmetro dos discos.
*/
public class Hanoi {
    
    // constante para o número de pinos.
    public static final int NUM_PINOS = 3;
    
    // Pinos do hanoi. Cada elemento do vetor é um pino.
    private Pilha<Integer> pinos[];
    
    // Numero de discos no hanoi
    private int numDiscos;
    
    private boolean pinoJogado[];
    
    // Constroe um objeto hanoi com 3 discos.
    public Hanoi() {
        numDiscos = 3;
        pinos = new Pilha[NUM_PINOS];
        configuraEstadoInicial();
    }
    
    // Constroe um objeto hanoi com número arbitrário de discos
    public Hanoi(int numDiscos) {
        this.numDiscos = numDiscos;
        pinos = new Pilha[NUM_PINOS];
        configuraEstadoInicial();
    }
    
    // configurar o estado inicial.
    private void configuraEstadoInicial() {
        pinoJogado = new boolean[NUM_PINOS];
        // Cria os pinos.
        for(int i = 0; i < NUM_PINOS; ++i) {
            pinos[i] = new Pilha<Integer>();
        }
        // Coloca no primeiro pino os discos.
        for(int i = numDiscos; i > 0; --i) {
            pinos[0].push(i);
        }
    }
    
    // Método para mostrar um estado do hanoi.
    public void mostraHanoi() {
        for(int i = 0; i < NUM_PINOS; ++i) {
            System.out.printf("Pino(%d): ",(i+1));
            pinos[i].mostraPilha();
            System.out.println();
        }
    }
    
    // realizar as transições, isto é, produzir os
    // estados a partir de um estado qualquer.
    public ArrayList<Hanoi> transicao() {
        
        // Lista que armazenará todos os estados produzidos.
        ArrayList<Hanoi> estados = new ArrayList<>();
        
        /* Procura por um pino candidato. Esse pino candidato
           terá um disco removido.        
        */
        for(int i = 0; i < NUM_PINOS; ++i) {
            
            /* Aqui verificamos se há discos no pino. 
               Se não há discos no pino então não precisamos
               nos preocupar com o pino. Não há discos para
               serem removidos.
            */
            if(pinos[i].numDiscos() > 0 && !pinoJogado[i]) {
                
                /* Agora temos que procurar um pino candidato a
                   recebe o disco que será retirado. Novamente
                   iteramos por todos os pinos.
                */
                for(int j = 0; j < NUM_PINOS; ++j) {
                    
                    /* Procuramos por pinos distintos.
                       Não faz sentido remover
                       e colocar um disco no mesmo pino
                    */
                    if(j!=i) {
                        
                        // O pino candidato a receber o disco está vazio?
                        if(pinos[j].numDiscos() == 0) {
                            // então coloque o disco nesse pino.
                            estados.add(efetuaAcao(i, j));
                        } else {
                            
                            /* O pino candidato a receber um disco 
                               também possui discos. Precisamos 
                               verificar se podemos colocar um disco 
                               sobre o disco que está nesse pino.                           
                            */
                            if(pinos[i].top() < 
                                    pinos[j].top()) {
                                estados.add(efetuaAcao(i, j));
                            }
                        }
                    }   
                }
            }
        }
        
        return estados;
    }
    
    /* Efetuamos uma ação sobre os pinos e discos.
       Neste método removemos o disco do 
       pino i e o colocamos sobre o pino j, produzindo, 
       portanto, um novo estado.
    */
    private Hanoi efetuaAcao(int i, int j) {
        Hanoi novoEstado = clone();
        int disco = novoEstado.pinos[i].pop();
        novoEstado.pinos[j].push(disco);
        novoEstado.pinoJogado[j] = true;
        novoEstado.pinoJogado[i] = false;
        return novoEstado;       
    }
    
    // Obtemos um clone do estado. 
    public Hanoi clone() {
        Hanoi clone = new Hanoi(numDiscos);
        for(int i = 0; i < NUM_PINOS; ++i) {
            clone.pinos[i] = pinos[i].clone();
        }
        return clone;
    }
    
    // Para testar o objetivo do problema.
    public boolean testaObjetivo() {
        for(int i = 1; i < NUM_PINOS; ++i) {
            return pinos[i].numDiscos() == numDiscos;
        }
        return false;
    }
    
}