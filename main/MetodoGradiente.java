package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

import algoritmos.Armijo;
import algoritmos.DadosRetorno;
import algoritmos.Wolf;
import data_structures.Execution;
import data_structures.Execution.Algorithm;
import data_structures.Vec2;
import utils.Utils;

public class MetodoGradiente {
    /** ALGORITMO DAS ITERADAS*/

    public static Double[] startType01(Algorithm algorithm, Vec2 startPoint){
        ArrayList<Double> graphData = new ArrayList<Double>(); // estrutura que conterá a série de dados do gráfico 01
        Vec2 X = startPoint;
        int iterate=0; // contador de iteradas
        while(true){
            Double stepSize = 0.0; // tamamho de passo a ser preenchido pela iteração do algoritmo

            double v = Utils.norma(Utils.gradient(X));
            if(v < 0.000001){ // encontra o ponto crítico
                break; // termina as iteradas e abandona a estrutura
            }
            if (algorithm==Algorithm.ARMIJO) {
                DadosRetorno retorno = Armijo.proxIterType01(X);
                X = retorno.X;
                stepSize = retorno.stepSize;

            }else{ // executa wolf
                DadosRetorno retorno = Wolf.proxIterType01(X);
                X = retorno.X;
                stepSize = retorno.stepSize;
            }
            System.out.println(""+(iterate+1)+"X=("+X.x+","+X.y+") | stepSize="+stepSize);
            graphData.add(stepSize); // adiciona o o tamanho de passo na série de dados
            iterate++;
        }
        return graphData.toArray(new Double[graphData.size()]); // converte o arraylist para array cru
    }
    
    public static Vec2 start(Execution execution, Vec2 startPoint, long maxIterates){
        Vec2 X = startPoint;
        while(true){
            execution.addIterate(); // registra mais 1 iterada na execução
            double v = Utils.norma(Utils.gradient(X));
            if(v < 0.000001){ // encontra o ponto crítico
                return X;
            }
            //System.out.println("Não funcionou. Tentando novamente encontrar um alpha \n");
            if (execution.getAlgorithm()==Execution.Algorithm.ARMIJO) {
                X = Armijo.proxIter(execution,X);
            }else{ // executa wolf
                X = Wolf.proxIter(execution,X);
            }
            // verifica a quantidade máxima de iteradas e, caso ultrapasse, retorna o ponto crítico menos aproximado
            if(execution.getTotalIterates()==maxIterates) return null;
        }
    }
}
