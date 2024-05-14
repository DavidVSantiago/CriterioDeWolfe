import data_structures.Vec2;

public class Wolf {
    public static double alpha = 1.0; //
    public static double alphaPiso = 0.0; // 
    public static double alphaTeto = 0.7976931348623157E308; // começa com infinito 
    public static double n1 = 0.01; // lambda
    public static double n2 = 0.1; // beta
    public static Vec2 DK = Utils.multEscalarVec2(-1, Utils.gradient(X));
    public static Vec2 X;
    
    public static Vec2 proxIter(Vec2 Xpar) {
        X = Xpar;
        while (true) { // busca de um alpha valido
            if(testeArmijo()){ // satizfaz armijo

            }else{ // não satizfaz armijo

            }
            if(testeWolf()){ // satizfaz WOLF (as duas)

            }else{ // não satizfaz WOLF (as duas)
                if(){ // se nunca houve descida do teto (teto intocado, nunca houve uma falha de armijo)

                }else(){ // quando cai o teto

                }
            }
        }
    }

    public static boolean testeArmijo(){
        Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
        double compareEsq = Utils.objetivo(X2);
        double compareDir = (Utils.objetivo(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient(X),DK)));
        //System.out.println(compareEsq+"<="+compareDir);
        if(compareEsq <= compareDir) return true;
        return false;
    }
    public static boolean testeWolf(){
        
    }
}   
