package wolf;

import utils.*;

public class WolfGrafico01 {
    public static final double infinito = 0.7976931348623157E308; // constante infinita
    public static final double n1 = 0.01; // lambda
    public static double n2;
    public static double alpha;
    public static double alphaPiso;
    public static double alphaTeto;
    public static Vec2 DK;
    public static Vec2 X;
    public static Vec2 X2;
    public static double R = 2;//10;
    public static double r = 0.5;
    
    public static DadosRetorno proxIter(Vec2 Xpar,double neta){
        n2 = neta;
        alpha = 1;//0.005;
        alphaPiso = 0.0;
        alphaTeto = infinito; // começa com infinito 

        X=Xpar;
        DK = MathFunctions.multEscalarVec2(-1, MathFunctions.gradient(X));

        while (alphaPiso!=alphaTeto) { // busca de um alpha valido
            X2 = MathFunctions.sumVec2Vec2(X,MathFunctions.multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            if(testeArmijo(X2)){ // satizfaz armijo
                alphaPiso = alpha; // sobe o piso
            }else{ // não satizfaz armijo
                alphaTeto = alpha; // desce o teto
            }
            if(testeWolf(X2)){ // satizfaz WOLF (as duas)
                alphaTeto = alpha; // desce o teto
            }else{ // não satizfaz WOLF (as duas)
                if(alphaTeto==infinito){ // se nunca houve descida do teto (teto intocado, nunca houve uma falha de armijo)
                    alpha = Math.max(alpha,R*alphaPiso); // busca do novo alpha especial
                }else{ // quando cai o teto
                    double a = (1-r)*alphaPiso +     r*alphaTeto;
                    double b =     r*alphaPiso + (1-r)*alphaTeto;
                    alpha = Math.max(a,Math.min(alpha,b)); // busca do novo alpha padrão
                }
            }
        }
        return new DadosRetorno(X2,alpha);
    }

    // DUVIDA: a comparação de X2 e X. Cada novo X2 gerado é comparado com o X inicial?
    public static boolean testeArmijo(Vec2 X2){
        double compareEsq = MathFunctions.objetivo(X2);
        double compareDir = (MathFunctions.objetivo(X) + (alpha*n1*MathFunctions.innerProductVec2(MathFunctions.gradient(X),DK)));
        //System.out.println(compareEsq+"<="+compareDir);
        if(compareEsq <= compareDir) return true;
        return false;
    }
    public static boolean testeWolf(Vec2 X2){
        boolean segundaCond = MathFunctions.innerProductVec2(MathFunctions.gradient(X2), DK) >= (n2 * MathFunctions.innerProductVec2(MathFunctions.gradient(X), DK));
        if(testeArmijo(X2)==true && segundaCond==true) return true;
        return false;
    }

   /** Classe interna para representar os dados de retorno da execução do algoritmo */ 
 public static class DadosRetorno{
        public Vec2 X;
        public double stepSize;
        public DadosRetorno(Vec2 X, double stepSize){
            this.X=X;
            this.stepSize=stepSize;
        }
    }
}   
