package algoritmos;

import data_structures.Vec2;
import utils.Utils;

public class WolfTest {
    public static final double infinito = 0.7976931348623157E308; // constante infinita
    public static final double n1 = 0.01; // lambda
    public static final double n2 = 0.1; // beta
    public static double alpha;
    public static double alphaPiso;
    public static double alphaTeto;
    public static Vec2 DK;
    public static Vec2 X;
    public static double R = 10;
    public static double r = 0.1;
    
    public static Vec2 proxIter(Vec2 Xpar) {
        resetAlphas();
        X = Xpar;
        DK = Utils.multEscalarVec2(-1, Utils.gradient(X));

        while (alphaPiso!=alphaTeto) { // busca de um alpha valido
            System.out.println("Testando com alpha="+alpha);
            if(testeArmijo(X)){ // satizfaz armijo
                System.out.println("Satizfaz Amijo com alpha = "+alpha);
                alphaPiso = alpha; // sobe o piso
            }else{ // não satizfaz armijo
                //System.out.println("Não satizfaz Amijo - desce o teto");
                alphaTeto = alpha; // desce o teto
            }

            if(testeWolf(X)){ // satizfaz WOLF (as duas)
                System.out.println("Satizfaz WOLF com alpha ="+alpha);
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
        System.out.println("Alpha escolhido="+alpha);
        return X;
    }

    public static boolean testeArmijo(Vec2 X){
        double compareEsq = Utils.objetivo(X);
        double compareDir = (Utils.objetivo(X) + (alpha*n1*Utils.produtoInternoVec2(Utils.gradient(X),DK)));
        //System.out.println(compareEsq+"<="+compareDir);
        if(compareEsq <= compareDir) return true;
        return false;
    }
    public static boolean testeWolf(Vec2 X){
        boolean segundaCond = Utils.produtoInternoVec2(Utils.gradient(X), DK) >= (n2 * Utils.produtoInternoVec2(Utils.gradient(X), DK));
        if(testeArmijo(X)==true && segundaCond==true) return true;
        return false;
    }

    public static void printAlphas(){
        System.out.println("alphaTeto: "+alphaTeto);
        System.out.println("alpha: "+alpha);
        System.out.println("alphaPiso: "+alphaPiso);
    }
    public static void resetAlphas(){
        alpha = 0.15;//0.005;
        alphaPiso = 0.0;
        alphaTeto = infinito; // começa com infinito 
    }
}   
