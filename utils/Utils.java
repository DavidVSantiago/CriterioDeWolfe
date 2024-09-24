package utils;
/* Esta classe possuias funções auxiliares aos algoritmos */

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import data_structures.Vec2;

public class Utils {

    /* ******************************************************************************************** */
    /** FUNÇÕES BÁSICAS */
    /* ******************************************************************************************** */
    public static double norma(Vec2 vec2){
        double result = Math.sqrt( Math.pow(vec2.x, 2) +  Math.pow(vec2.y, 2));
        return result;
    }

    public static Vec2 gradient(Vec2 vec2){
        return gradient_v2(vec2);
    }

    public static Vec2 gradient_v1(Vec2 vec2){
        double x = 0.125*(-38+(97*vec2.x)-54*(vec2.x*vec2.x)+8*(Math.pow(vec2.x, 3)));
        double y = vec2.y*0.5;
        Vec2 result = new Vec2(x,y);
        return result;
    }
    /** Função objetivo definido pela Nalanda para piorar armijo */
    public static Vec2 gradient_v2(Vec2 vec2){
        double x = (-85+(122*vec2.x)-42*(vec2.x*vec2.x)+4*(Math.pow(vec2.x, 3))) / 6.0;
        double y = vec2.y/3.0;
        Vec2 result = new Vec2(x,y);
        return result;
    }

    public static double objetivo(Vec2 vec2){
        return objetivo_v2(vec2);
    }
    public static double objetivo_v1(Vec2 vec2){
        double result = 0.25*( ((Math.pow(( (4*vec2.x) - 2),2))*(Math.pow(( (0.25*vec2.x) - 1),2))) - vec2.x + Math.pow(vec2.y,2) + 2);
        return result;
    }
    /** Função objetivo definido pela Nalanda para piorar armijo */
    public static double objetivo_v2(Vec2 vec2){
        double result = ( ((Math.pow(( (6*vec2.x) - 6),2))*(Math.pow(( (vec2.x/6.0) - 1),2))) - vec2.x + Math.pow(vec2.y,2) - 8)/6.0;
        return result;
    }


    /* ******************************************************************************************** */
    /** FUNÇÕES AUXILIARES */
    /* ******************************************************************************************** */
    
    public static Vec2 multEscalarVec2(double escalar, Vec2 vec2){
        Vec2 result = new Vec2(escalar*vec2.x, escalar*vec2.y);
        return result;
    }

    public static Vec2 somaVec2Vec2(Vec2 v1, Vec2 v2){
        Vec2 result = new Vec2(v1.x+v2.x, v1.y+v2.y); 
        return result;
    }
    public static boolean compareVec2Vec2(Vec2 v1, Vec2 v2){
        if(Math.abs(v1.x-v2.x)<1e-5 && Math.abs(v1.y-v2.y)<1e-5) return true;
        return false;
    }

    public static double produtoInternoVec2(Vec2 v1, Vec2 v2){
        double result = (v1.x*v2.x) + (v1.y*v2.y);
        return result;
    }

    public static double generateRandom(double min, double max){
        Random random = new Random();
        double frac = random.nextDouble();
        double value = ((max-min)*frac)-Math.abs(min);
        return value;
    }

    public static Vec2[] generatePoints(int n){
        int min = 0;
        int max = 10;
       
        Vec2[] pointList = new Vec2[n];
        for(int i=0;i<n;i++){ // gera os n Vec2
            double x = generateRandom(min, max);
            double y = generateRandom(min, max);
            Vec2 X = new Vec2(y,x);
            pointList[i] = X;  
        } 
        return pointList;
    }

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
    public static void serieDadosParaArquivo(String nomeArquivo, Double[] serieDados) {
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
}
