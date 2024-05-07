import data_structures.Vec2;

public class Wolf {
    public static Vec2 proxIter(Vec2 X){
        Vec2 DK = Utils.multEscalarVec2(-1, Utils.gradient(X));
        double alpha1 = 1.0;
        double n1 = 0.01;
        double salto1=0.8;
        while (true) { // busca de um alpha valido
            System.out.println("Testando o Alpha 1 = "+alpha1+"!");  
            Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha1, DK)); // cria a próxima geração do X
            double compareEsq = Utils.objetivo(X2);
            double compareDir = (Utils.objetivo(X) + (alpha1*n1*Utils.produtoInternoVec2(Utils.gradient(X),DK)));
            //System.out.println(compareEsq+"<="+compareDir);
            if(compareEsq <= compareDir){ // comapração de Armijo
                //System.out.println("Alpha 1 encontrado: "+alpha1);
                // começamos o processo de encontrar o segundo alpha (Segundo critério)
                double alpha2 = alpha1;
                double n2 = 0.1;
                double salto2=0.8;
                while (true) {// busca de um subalpha valido
                    System.out.println("Alpha2="+alpha2);
                    System.out.println("Obj no X2 = "+Utils.objetivo(X2));
                    //System.out.println("Testando o Alpha 2 = "+alpha2+"!");
                    X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha2, DK)); // atualizo o X2 com o subalpha
                    compareEsq = Utils.produtoInternoVec2(Utils.gradient(X2),DK);
                    compareDir = alpha2*n2*Utils.produtoInternoVec2(Utils.gradient(X),DK);
                    if(compareEsq >= compareDir){ // comparação do 2º critério
                        System.out.println("Alpha 2 encontrado: "+alpha2);
                        System.out.println("Função objetivo no ponto Xk = "+Utils.objetivo(X2));
                        return X2;
                    }
                    alpha2=alpha2*salto2;
                }
            }
            //System.out.println("Alpha 1 = "+alpha1+" não serve!");  
            alpha1=alpha1*salto1;
        }
    }
}
