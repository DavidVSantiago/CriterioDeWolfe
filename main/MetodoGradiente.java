package main;

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

    /** Execução para a geração do 1º gráfico (variando o n2)  várias execuções para o mesmo ponto*/
    public static Double[] startType01(Algorithm algorithm, Vec2 startPoint, double _n2){
        ArrayList<Double> graphData = new ArrayList<Double>();
        Vec2 X = startPoint;
        int iterate=0; // contador de iteradas
        while(true){
            Double stepSize = 0.0; // tamamho de passo a ser preenchido pela iteração do algoritmo
            double v = Utils.norma(Utils.gradient(X));
            //System.out.println("Passo:"+iterate+" - Norma="+v);
            graphData.add(v); // adiciona o o tamanho de passo na série de dados
            if(v < 0.000001){ // encontra o ponto crítico
                break; // termina as iteradas e abandona a estrutura
            }
            if (algorithm==Algorithm.ARMIJO) {
                DadosRetorno retorno = Armijo.proxIterType01(X);
                X = retorno.X;
                stepSize = retorno.stepSize;

            }else{ // executa wolf
                DadosRetorno retorno = Wolf.proxIterType01(X,_n2);
                X = retorno.X;
                stepSize = retorno.stepSize;
            }
            //System.out.println("Passo:"+iterate+" - Stepsize="+stepSize);
            //System.out.println("Passo:"+iterate+" - X=("+X.x+","+X.y+") | stepSize="+stepSize);
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
