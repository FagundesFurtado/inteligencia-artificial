
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class KNN {
    
    // Para armazenar os dados do problema
    private double dados[][];
    private int erros;
    
    
    public KNN(String dir, int numObjs, int numAtr) {
        
        dados = new double[numObjs][numAtr];
        erros = 0;
        leArquivo(dir);
    }
    
    // Le todos os valores do arquivo.
    // x1, x2, x3, ... , y.
    private void leArquivo(String dir) {
        
        try {
            File f = new File(dir);
            BufferedReader bf = new BufferedReader(
                new FileReader(f));
            
            String linha = bf.readLine();
            int n = 0;
            while(linha != null) {
                leAtributos(n,linha);
                linha = bf.readLine();
                n++;
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }    
    }
    
    private void leAtributos(int n,String linha) {
        String atributos[] = linha.split(",");
        for(int i =0;i<atributos.length; ++i) {
            dados[n][i] = Double.parseDouble(atributos[i]);
        }
    }
    
    public void mostraDados() {
        for(int i = 0; i < dados.length; ++i) {
            for(int j = 0; j < dados[i].length; ++j) {
                System.out.print(dados[i][j]+",");
            }
            System.out.println();
        }
    }
    
    public int classificaObjeto(String objetoStr) {
        String atributo[] = objetoStr.split(",");
        double objeto[] = new double[atributo.length-1];
        int esperado = -1;
        for(int i = 0; i < atributo.length-1; ++i) {
            objeto[i] = Double.parseDouble(atributo[i]);
            
        }
        esperado = (int)Integer.parseInt(atributo[atributo.length-1]);
        
        
        double dMin = Double.MAX_VALUE;
        int classe = -1;
        for(int i = 0; i < dados.length; ++i) {
            double d = distancia(dados[i], objeto);
            if(d < dMin) {
                dMin = d;
                classe = (int)dados[i][dados[i].length-1];
            }
        }
        if(classe != esperado) {
            erros++;
        }
        return classe;
        
    }
    
    public double distancia(double x[], double o[]) {
        double d = 0;
        for(int i = 0; i < o.length; ++i) {
            d += Math.pow(x[i]-o[i], 2);
        }
        return Math.sqrt(d);
    }
    
    public void classificaArquivo(String dir) {
    
        
        try {
            File f = new File(dir);
            BufferedReader bf = new BufferedReader(
                new FileReader(f));
            
            String linha = bf.readLine();
            int n = 0;
            while(linha != null) {
                int classe = classificaObjeto(linha);
                System.out.println("Classe = "+classe);
                n++;
                linha = bf.readLine();
            }
            System.out.println("Erros = "+erros);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        KNN knn = new KNN("/home/antonio/mnist.data",10000,785);
        //knn.mostraDados();
        knn.classificaArquivo("/home/antonio/mnist.test");
    }
    
    
}
