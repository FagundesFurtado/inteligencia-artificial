
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Kmeans {
    
    private double[][] dados;
    private double[][] centroides;
    private int k;
    
    public Kmeans(int k, String dir) {
        this.k = k;
        leDados(dir);
    }
    
    private void leDados(String dir) {
        
        try {
            
            BufferedReader arquivo = new BufferedReader(new FileReader(new File(dir)));
            BufferedReader numObjs = new BufferedReader(new FileReader(new File(dir)));
           
            String linha = arquivo.readLine();
            
            dados = new double[(int)numObjs.lines().count()][linha.split(",").length];
            
            int obs = 0;
            while(linha != null) {
                //System.out.println("Linha = "+linha);
                parseDados(linha, obs);
                linha = arquivo.readLine();
                obs++;
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void agrupar(String dir) {
        geraCentroides();
        
        HashMap<Integer, ArrayList<double[]>> grupos = new HashMap<>();
        for(int i = 0; i < k; ++i) {
            grupos.put(i, new ArrayList<>());
        }
        
        double centroideAnterior[][] = new double[k][centroides[0].length];
        
        double error = Double.MAX_VALUE;
        double tolerancia = 0.00001;
        
        while(error > tolerancia) {
            for(int i = 0; i < k; ++i) {
                grupos.get(i).clear();
            }
            for(int i = 0; i < dados.length; ++i) {

                double distMin = Double.MAX_VALUE;
                int grupo = -1;

                for(int j = 0; j < k; ++j) {
                    double dist = distanciaEuclidiana(dados[i], centroides[j]);
                    if(dist < distMin) {
                        grupo = j;
                        distMin = dist;
                    }
                }

                grupos.get(grupo).add(dados[i]);

            }

            for(int i = 0; i < k; ++i) {
                ArrayList<double[]> observacoes = grupos.get(i);
                double obsMedia[] = new double[centroides[0].length];
                for(int j = 0; j < observacoes.size(); ++j) {
                    for(int l = 0; l < obsMedia.length; ++l) {
                        obsMedia[l] += observacoes.get(j)[l];
                    }
                }
                for(int j = 0; j < obsMedia.length; ++j) {
                    centroideAnterior[i][j] = centroides[i][j];
                    centroides[i][j] = obsMedia[j]/observacoes.size();
                }
            }
            
            error = 0;
            for(int i = 0; i < k; ++i) {
                error += distanciaEuclidiana(centroideAnterior[i], centroides[i]);
            }
        }
        
        try {
            BufferedWriter arqSaida = new BufferedWriter(new FileWriter(new File(dir)));
            for(int i = 0; i < k; ++i) {
                ArrayList<double[]> observacoes = grupos.get(i);
                for(double[] obs:observacoes) {
                    String linha = "";
                    for(int j = 0; j < obs.length; ++j) {
                        linha += obs[j]+",";
                    }
                    linha = linha+(i+1);
                    arqSaida.write(linha+"\n");
                }
                System.out.println(Arrays.toString(centroides[i]));
            }
            arqSaida.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private double distanciaEuclidiana(double p1[], double p2[]) {
        double distancia = 0;
        for(int i = 0; i < p1.length; ++i) {
            distancia += Math.pow((p1[i]-p2[i]),2);
        }
        return Math.sqrt(distancia);
    }
    
    private void geraCentroides() {
        centroides = new double[k][dados[0].length];
        Random pos = new Random();
        for(int i = 0; i < k; ++i) {
            centroides[i] = dados[pos.nextInt(dados.length)];
        }
    }
    
    private void parseDados(String linha, int obs) {
        String atributos[] = linha.split(",");
        for(int i = 0; i < atributos.length; ++i) {
            dados[obs][i] = Double.parseDouble(atributos[i]);
        }
    }
    
    public static void main(String a[]) {
        Kmeans kmeans = new Kmeans(4, "/home/antonio/Desktop/dados.csv");
        kmeans.agrupar("/home/antonio/Desktop/grupos.csv");
        
    }
    
    
}
