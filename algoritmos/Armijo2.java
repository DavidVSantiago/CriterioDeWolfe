package algoritmos;

import data_structures.Execution;
import data_structures.Vec2;
import utils.Utils;

public class Armijo2 {
    public static Vec2 DK;
    public static double alpha;
    public static double n1;
    public static double fator;
    
    public static void initConstants(Vec2 X){
        DK = Utils.multEscalarVec2(-1, Utils.gradient2(X));
        alpha = 1;
        n1 = 0.001;
        fator = 0.5;
    }

    public static Vec2 proxIter(Execution execution, Vec2 X){
        initConstants(X);
        while (true) { // busca de um alpha valido
            Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            double compareEsq = Utils.objetivo2(X2);
            double compareDir = (Utils.objetivo2(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient2(X),DK)));
            if(compareEsq <= compareDir){ // comparação de Armijo
                execution.addStepsSize(alpha);
                return X2;
            }
            alpha=alpha*(1-fator);
        }
    }

    public static void testAlphaInterval(Vec2 X){
        initConstants(X);
        while (alpha>0) {
            Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            double compareEsq = Utils.objetivo2(X2);
            double compareDir = (Utils.objetivo2(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient2(X),DK)));
            //System.out.println(compareEsq+"<="+compareDir);
            if(compareEsq <= compareDir)
            System.out.println("Satisfaz armijo com alpha ="+alpha);
            else System.out.println("Não atisfaz armijo com alpha ="+alpha);
            alpha-=0.01;
        }
    }
}
