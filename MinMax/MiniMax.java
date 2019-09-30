
import java.util.ArrayList;

public class MiniMax {
    
    private String trocaJogador(String jogadorAtual) {
        if(JogoVelha.JOGADOR_O.equals(jogadorAtual)) {
            return JogoVelha.JOGADOR_X;
        } else {
            return JogoVelha.JOGADOR_O;
        }
    }
    
    public int buscaMelhorJogada(int [] estadoAtual, String jogador) {
        
        No no = new No(estadoAtual);
        ArrayList<int[]> estadosMin = no.realizaTransicao(jogador);
        int max = Integer.MIN_VALUE;
        int minimax;
        int melhorEstado[] = estadoAtual;
        for(int[] estadoMin:estadosMin) {
            minimax = avaliaMin(estadoMin, trocaJogador(jogador));
            System.out.println("Valor = "+minimax);
            if(minimax > max) {
                max = minimax;
                melhorEstado = estadoMin;
            }
        }
        
        for(int i = 0; i < estadoAtual.length; ++i) {
            if(estadoAtual[i] != melhorEstado[i]) {
                return i;
            }
        }
        
        return -1;
        
    }
    
    private int avaliaMin(int[] estadoMin, String jogador) {
        int minimax;
        
        No no = new No(estadoMin);
        int valor = no.testaTerminal();
        if(valor!=-2) {
            return valor;
        }
        
        ArrayList<int[]> estadosMax = no.realizaTransicao(jogador);
        int min = Integer.MAX_VALUE;
        for(int[] estadoMax:estadosMax) {
            minimax = avaliaMax(estadoMax, trocaJogador(jogador));
            if(minimax < min) {
                min = minimax;
            }
        }        
        return min;
    }
    
    private int avaliaMax(int[] estadoMax, String jogador) {
        int minimax;
        
        No no = new No(estadoMax);
        int valor = no.testaTerminal();
        if(valor!=-2) {
            return valor;
        }
        ArrayList<int[]> estadosMin = no.realizaTransicao(jogador);
        int max = Integer.MIN_VALUE;
        for(int[] estadoMin:estadosMin) {
            minimax = avaliaMin(estadoMin, trocaJogador(jogador));
            if(minimax > max) {
                max = minimax;
            }
        }
        
        return max;
    }
    
}
