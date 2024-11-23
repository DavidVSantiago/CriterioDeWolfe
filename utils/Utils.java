package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/* Esta classe define as funções auxiliares aos algoritmos */
public class Utils {
 
    public static double generateRandom(double min, double max){
        Random random = new Random();
        double frac = random.nextDouble();
        double value = ((max-min)*frac)-Math.abs(min);
        return value;
    }

    public static Vec2 generatePoint(double min, double max){
        double x = generateRandom(min, max);
        double y = generateRandom(min, max);
        return new Vec2(x,y);
    }

    /** Definition */
    public static Vec2[] generatePoints(int qtd){
        Random random = new Random(); // objeto gerador de numeros aleatórios
        Vec2[ ] pontos = new Vec2[qtd]; // array de pontos
        for(int i=0;i<qtd;i++){ // preenche o array com cada um dos 100 pontos
            // gera os valores x e y aleatórios entre -100 e 100 que irão compor o ponto X0
            double x = ((100-(-100))*random.nextDouble())-Math.abs(-100);
            double y = ((100-(-100))*random.nextDouble())-Math.abs(-100);
            pontos[i] = new Vec2(x,y); // adiciona no array um novo ponto inicial X0.
        }
        return pontos;
    }

    /** Definition */
    public static Vec2[] listToArray(List<Vec2> list){
        Vec2[] array = new Vec2[list.size()];
        for(int i=0;i<list.size();i++){
            array[i]=list.get(i);
        }
        return array;
    }

     
    /* ******************************************************************************************** */
    /** FUNÇÕES DE ARQUIVOS */
    /* ******************************************************************************************** */
    
    /** Salva a série execuções com a informação da quantidade de iterações em cada execução */
    public static void serieDadosParaArquivo(String nomeArquivo, int[] serieDados) {
        try {
            FileWriter fw = new FileWriter(nomeArquivo); // Cria um objeto FileWriter para escrever no arquivo
            PrintWriter pw = new PrintWriter(fw); // Cria um objeto PrintWriter para facilitar a escrita
            
            // percorre cada um dos dados na série
            for(int i=0;i<serieDados.length;i++){
                pw.println(serieDados[i]); // Escreve o texto no arquivo
            }
            pw.close();// Fecha o PrintWriter para liberar os recursos
        } catch (Exception e) {
           System.out.println("Erro da gravação dos dados em arquivo!");
        }
    }
    public static void serieDadosDoubleParaArquivo(String nomeArquivo, double[] serieDados) {
        try {
            FileWriter fw = new FileWriter(nomeArquivo); // Cria um objeto FileWriter para escrever no arquivo
            PrintWriter pw = new PrintWriter(fw); // Cria um objeto PrintWriter para facilitar a escrita
            
            // Formatador de números com separador decimal ","
            DecimalFormat df = new DecimalFormat("#,###.00000000"); // Ajuste o padrão conforme necessário

            // percorre cada um dos dados na série
            for(int i=0;i<serieDados.length;i++){
                String numeroFormatado = df.format(serieDados[i]);
                pw.println(numeroFormatado); // Escreve o texto no arquivo
            }
            pw.close();// Fecha o PrintWriter para liberar os recursos
        } catch (Exception e) {
           System.out.println("Erro da gravação dos dados em arquivo!");
        }
    }
}