package algoritmos;

import data_structures.Vec2;
import utils.Utils;

public class Armijo {
    public static Vec2 proxIter(Vec2 X){
        Vec2 DK = Utils.multEscalarVec2(-1, Utils.gradient(X));
        double alpha = 1;
        double n1 = 0.01;
        double fator = 0.5; //50%
        while (true) { // busca de um alpha valido
            System.out.println("Testando com alpha="+alpha);
            Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            double compareEsq = Utils.objetivo(X2);
            double compareDir = (Utils.objetivo(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient(X),DK)));
            //System.out.println(compareEsq+"<="+compareDir);
            if(compareEsq <= compareDir){ // comapração de Armijo
                System.out.println("Alpha escolhido="+alpha);
                //System.out.println("Função objetivo no ponto Xk = "+Utils.objetivo(X2));
                return X2;
            }
            //System.out.println("Alpha = "+alpha+" não serve!");  
            alpha=alpha*(1-fator);
        }
    }
}
