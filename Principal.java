import java.util.Random;
import java.util.Scanner;

import data_structures.*;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //Random random = new Random();
        OtimzarFuncaoAlgo(new Vec2(0.0, 0.0));
        
        // wolfeAlgo(new Vec2(0.0, 0.0));
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // wolfeAlgo(new Vec2(0.0, 0.0));
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // wolfeAlgo(new Vec2(0.0, 0.0));
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // System.out.println("****************************************");

    }
    /** ALGORITMO WOLFE */
    public static Vec2 OtimzarFuncaoAlgo(Vec2 start){
        int count = 1;
        Vec2 X = start;
        while(true){
            double v = Utils.norma(Utils.gradient(X));
            System.out.println("Tentando critério de parada |"+count+"| com a norma: "+v+"\n");
            if(v < 0.000001){ // encontr
                System.out.println("Critério encontraro. Algoritmo encerrado");
                System.out.println("Ponto crítico encontrado = ("+X.x+","+X.y+")");
                return X;
            }
            System.out.println("Não funcionou. Tentando novamente encontrar um alpha \n");
            X = Armijo.proxIter(X);
            count++;
        }
    }
}