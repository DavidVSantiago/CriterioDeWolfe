import java.util.Random;
import java.util.Scanner;

import data_structures.*;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //Random random = new Random();
        double max = 10;
        double min = -10;
        //Vec2 X = new Vec2(generateRandom(max,min), generateRandom(max,min));
        Vec2 X = new Vec2(5.0,5.0);
        System.out.println("X:"+X.x+" | Y:"+X.y);
        OtimzarFuncaoAlgo(X);
        //Armijo2.testAlphaInterval(new Vec2(0.0, 0.0));

    }
    /** ALGORITMO WOLFE */
    public static Vec2 OtimzarFuncaoAlgo(Vec2 start){
        int count = 1;
        Vec2 X = start;
        while(true){
            double v = Utils.norma(Utils.gradient2(X));
            System.out.println("Tentando critério de parada |"+count+"| com a norma: "+v+"\n");
            if(v < 0.000001){ // encontr
                System.out.println("Critério encontraro. Algoritmo encerrado");
                System.out.println("Ponto crítico encontrado = ("+X.x+","+X.y+")");
                return X;
            }
            System.out.println("Não funcionou. Tentando novamente encontrar um alpha \n");
            X = Armijo2.proxIter(X);
            count++;
        }
    }

    public static double generateRandom(double max, double min){
        Random random = new Random();
        double frac = random.nextDouble();
        double value = ((max-min)*frac)-min;
        return value;
    }
}