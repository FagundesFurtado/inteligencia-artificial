
import javax.swing.JFrame;

public class Main {
    
    public static void main(String args[]) {
        JFrame janela = new JFrame("Jogo da Velha");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 500);
        janela.setVisible(true);
        
        janela.add(new JogoVelha());
        janela.validate();
    }
    
}
