
import java.util.ArrayList;

/* Classe para representar a estrutura de uma pilha.
   A ideia é que essa classe possa ser usada como um pino no hanoi.
   Escolhemos a estrutura de Pilha pois ela satisfaz ações
   básicas que vamos realizar sobre os pinos.
*/
public class Pilha<T> {
    
    // para armazenar os discos em cada pino.
    ArrayList<T> discos; 
    
    // Construtor 
    public Pilha() {
        // apenas inicializa a lista que receberá os discos
        discos = new ArrayList<>();
    }
    
    // Top = acessa o elemento do topo da pilha
    public T top() {
        if(!discos.isEmpty())
            return discos.get(discos.size()-1);
        return null;
    }
    
    // Push = coloca um elemento no topo da pilha.
    public void push(T disco) {
        discos.add(disco);
    }
    
    // Pop = retira o elemento do topo da pilha.
    public T pop() {
        if(!discos.isEmpty())
            return discos.remove(discos.size()-1);
        return null;
    }
    
    public void mostraPilha() {
        for(T disco:discos) {
            System.out.print(disco.toString());
        }
    } 
    
    public Pilha<T> clone() {
        Pilha<T> clone = new Pilha<T>();
        ArrayList cloneDiscos = new ArrayList();
        for(int i = 0; i < discos.size(); ++i) {
            cloneDiscos.add(discos.get(i));
        }
        clone.discos = cloneDiscos;
        return clone;
    }
    
    public int numDiscos() {
        return discos.size();
    }
}
