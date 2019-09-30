
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class No {
    
    private No pai;
    private int[] estado;
    
    private ArrayList<No> filhos;
    
    public No(int[] estadoInicial) {
        estado = estadoInicial;
    }
    
    public void addFilho(No filho) {
        filho.pai = this;
        filhos.add(filho);
    }
    
    // retorna "1" se o jogador x ganhou.
    // retorna "-1" se o jogador o ganhou.
    // retorna "0" se houve empate.
    // retorna "-2" se não é terminal.
    public int testaTerminal() {
        int jogada = estado[0];
        if(jogada == estado[1] && jogada == estado[2] && jogada!=0) {
            return jogada;
        }
        if(jogada == estado[3] && jogada == estado[6]&& jogada!=0) {
            return jogada;
        }
        if(jogada == estado[4] && jogada == estado[8]&& jogada!=0) {
            return jogada;
        }
        jogada = estado[3];
        if(jogada == estado[4] && jogada == estado[5]&& jogada!=0) {
            return jogada;
        }
        jogada = estado[6];
        if(jogada == estado[7] && jogada == estado[8]&& jogada!=0) {
            return jogada;
        }
        jogada = estado[6];
        if(jogada == estado[4] && jogada == estado[2]&& jogada!=0) {
            return jogada;
        }
        jogada = estado[1];
        if(jogada == estado[4] && jogada == estado[7]&& jogada!=0) {
            return jogada;
        }
        jogada = estado[2];
        if(jogada == estado[5] && jogada == estado[8]&& jogada!=0) {
            return jogada;
        }
        jogada = estado[2];
        if(jogada == estado[4] && jogada == estado[6]&& jogada!=0) {
            return jogada;
        }
        for(int i:estado) {
            if(i == 0) {
                return -2;
            }
        }
        return 0;
    }
    
    public int[] getEstado() {
        int novoEstado[] = new int[9];
        for(int i = 0; i < estado.length; ++i) {
            novoEstado[i] = estado[i];
        }
        return novoEstado;
    }
    
    public ArrayList<int[]> realizaTransicao(String jogador) {
        ArrayList<int[]> estados = new ArrayList<>();
        int jogadorId = 0;
        if(jogador.equals(JogoVelha.JOGADOR_X)) {
            jogadorId = 1;
        }
        if(jogador.equals(JogoVelha.JOGADOR_O)) {
            jogadorId = -1;
        }
        for(int i = 0; i < estado.length; ++i) {
            int novoEstado[] = getEstado();
            if(estado[i] == 0) {
                novoEstado[i] = jogadorId;
                estados.add(novoEstado);
            }
        }
        return estados;
    }
    
}
