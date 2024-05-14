import data_structures.Vec2;

public class Wolf {
    public static double alpha = 1.0; //
    public static double alphaPiso = 0.0; // 
    public static double infinito = 0.7976931348623157E308; // constante infinita
    public static double alphaTeto = infinito; // começa com infinito 
    public static double n1 = 0.01; // lambda
    public static double n2 = 0.1; // beta
    public static Vec2 DK = Utils.multEscalarVec2(-1, Utils.gradient(X));
    public static Vec2 X;
    public static double R = 2.0;
    public static double r = 0.5;
    
    public static Vec2 proxIter(Vec2 Xpar) {
        X = Xpar;
        while (true) { // busca de um alpha valido
            Vec2 X2 = Utils.somaVec2Vec2(X,Utils.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            
            if(testeArmijo(X2)){ // satizfaz armijo
                alphaPiso = alpha; // sobe o piso
            }else{ // não satizfaz armijo
                alphaTeto = alpha; // desce o teto
            }

            if(testeWolf(X2)){ // satizfaz WOLF (as duas)
                return X2;
                // algoritmo resolvido
            }else{ // não satizfaz WOLF (as duas)
                if(alphaTeto==infinito){ // se nunca houve descida do teto (teto intocado, nunca houve uma falha de armijo)
                    alpha = Math.max(alpha,R*alphaPiso); // busca do novo alpha especial
                }else{ // quando cai o teto
                    double a = (1-r)*alphaPiso +     r*alphaTeto;
                    double b =     r*alphaPiso + (1-r)*alphaTeto;;
                    alpha = Math.max(a,Math.min(alpha,b)); // busca do novo alpha padrão
                }
            }
        }
    }

    public static boolean testeArmijo(Vec2 X2){
        double compareEsq = Utils.objetivo(X2);
        double compareDir = (Utils.objetivo(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient(X),DK)));
        //System.out.println(compareEsq+"<="+compareDir);
        if(compareEsq <= compareDir) return true;
        return false;
    }
    public static boolean testeWolf(Vec2 X2){
        boolean segundaCond = Utils.produtoInternoVec2(Utils.gradient(X2), DK) >= (n2 * Utils.produtoInternoVec2(Utils.gradient(X), DK) );
        if(testeArmijo(X2)==true &&  segundaCond==true) return true;
        return false;
    }
}   
