package main;

import algoritmos.Armijo2;
import algoritmos.Wolf2;
import data_structures.Execution;
import data_structures.Vec2;
import utils.Utils;

public class MetodoGradiente {
    /** ALGORITMO DAS ITERADAS*/
    public static Vec2 start(Execution execution, Vec2 startPoint){
        Vec2 X = startPoint;
        while(true){
            execution.addIterate(); // registra mais 1 iterada na execução
            double v = Utils.norma(Utils.gradient2(X));
            if(v < 0.000001){ // contra o ponto crítico
                return X;
            }
            //System.out.println("Não funcionou. Tentando novamente encontrar um alpha \n");
            if (execution.getAlgorithm()==Execution.Algorithm.ARMIJO) {
                X = Armijo2.proxIter(execution,X);
            }else{ // executa wolf
                X = Wolf2.proxIter(execution,X);
            }
        }
    }
}
