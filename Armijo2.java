import data_structures.Vec2;

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

    public static Vec2 proxIter(Vec2 X){
        initConstants(X);

        while (true) { // busca de um alpha valido
            //System.out.println("Testando com alpha="+alpha);
            Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            double compareEsq = Utils.objetivo2(X2);
            double compareDir = (Utils.objetivo2(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient2(X),DK)));
            //System.out.println(compareEsq+"<="+compareDir);
            if(compareEsq <= compareDir){ // comapração de Armijo
                //System.out.println("Alpha escolhido="+alpha);
                Utils.countLocalSteps+=alpha;
                //System.out.println("Função objetivo no ponto Xk = "+Utils.objetivo2(X));
                return X2;
            }
            //System.out.println("Alpha = "+alpha+" não serve!");  
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
