
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JogoVelha extends JPanel {
    
    public static final String JOGADOR_X = "X";
    public static final String JOGADOR_O = "O";
    
    private JButton casa0;
    private JButton casa1;
    private JButton casa2;
    private JButton casa3;
    private JButton casa4;
    private JButton casa5;
    private JButton casa6;
    private JButton casa7;
    private JButton casa8;
    
    private ArrayList<JButton> casas;
    
    private String jogador;
    
    private MiniMax minimax;
    
    public JogoVelha() {
        jogador = JOGADOR_X;
        minimax = new MiniMax();
        inicializaTabuleiro();
    }
    
    private void inicializaTabuleiro() {
        GridLayout layout = new GridLayout(3, 3);
    
        setLayout(layout);
        
        casa0 = new JButton("");
        casa1 = new JButton("");
        casa2 = new JButton("");
        casa3 = new JButton("");
        casa4 = new JButton("");
        casa5 = new JButton("");
        casa6 = new JButton("");
        casa7 = new JButton("");
        casa8 = new JButton("");
        
        add(casa0);
        add(casa1);
        add(casa2);
        add(casa3);
        add(casa4);
        add(casa5);
        add(casa6);
        add(casa7);
        add(casa8);  
        
        casas = new ArrayList();
        casas.add(casa0);
        casas.add(casa1);
        casas.add(casa2);
        casas.add(casa3);
        casas.add(casa4);
        casas.add(casa5);
        casas.add(casa6);
        casas.add(casa7);
        casas.add(casa8);
     
        efetuaJogada(minimax.buscaMelhorJogada(getEstado(), jogador), jogador);
        setEventos();
    }
    
    public void setEventos() {
        for(JButton casa:casas) {
            casa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    casa.setText(jogador);
                    mudaJogador();
                    efetuaJogada(minimax.buscaMelhorJogada(getEstado(), jogador), jogador);                    
                }
            });
        }
    }
    
    /**
     * Efetua uma jogada no jogo da velha.
     * @param pos posição da jogada no tabuleiro.
     * @param jogador jogador que está fazendo a jogada.
     */
    public void efetuaJogada(int pos, String jogador) {
        casas.get(pos).setText(jogador);
        mudaJogador();
    }
    
    /**
     * Obtém o estado atual de um tabuleiro.
     * @return um vetor de inteiros que corresponde ao 
     * estado atual de um tabuleiro. Uma posição 
     * está vazia se um elemento do vetor de inteiros é 0.
     */
    public int[] getEstado() {
        int novoEstado[] = new int[9];
        for(int i = 0; i < casas.size(); ++i) {
            if(casas.get(i).getText().equals(JOGADOR_X)) {
                novoEstado[i] = 1;
            }
            if(casas.get(i).getText().equals(JOGADOR_O)) {
                novoEstado[i] = -1;
            }
        }
        return novoEstado;
    }
    
    // Muda de jogador.
    private void mudaJogador() {
        if(jogador.equals(JOGADOR_X)) {
            jogador = JOGADOR_O;
        } else {
            jogador = JOGADOR_X;
        }
    }
}
