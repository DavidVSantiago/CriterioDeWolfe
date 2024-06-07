import java.lang.System.Logger.Level;
import java.util.Random;
import java.util.Scanner;

import data_structures.*;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n = 10000;
        Vec2[] pointList = generatePoints(n);
        
        // executar o algoritmo para cada um dos n pontos para Amijo
        long startTime = System.nanoTime();
        for(int i=0;i<n;i++){
            OtimzarFuncaoAlgo(pointList[i],Algo.ARMIJO);
        }
        long endTime = System.nanoTime();
        double totalTime =  (double)(endTime-startTime)/1_000_000_000.00;
        System.out.println("-------------------------------------------------");
        //System.out.println("Somatório Global dos Passos Armijo = "+Utils.countGlobalSteps);
        System.out.println("Somatório Global dos Iteradas Armijo = "+Utils.countGlobalIterations);
        System.out.println("Média do tamanho de passo por iterada = "+Utils.countGlobalSteps/Utils.countGlobalIterations);
        System.out.println("Tempo de execução = "+totalTime);
        System.out.println("\n");
        
        // executar o algoritmo para cada um dos n pontos para Wolf
        Utils.countGlobalIterations=0;
        Utils.countGlobalSteps=0.0;
        startTime = System.nanoTime();
        for(int i=0;i<n;i++){
            OtimzarFuncaoAlgo(pointList[i],Algo.WOLF);
        }
        endTime = System.nanoTime();
        totalTime =  (double)(endTime-startTime)/1_000_000_000.00;
        System.out.println("-------------------------------------------------");
        //System.out.println("Somatório Global dos Passos Wolf = "+Utils.countGlobalSteps);
        System.out.println("Somatório Global dos Iteradas Wolf = "+Utils.countGlobalIterations);
        System.out.println("Média do tamanho de passo por iterada = "+Utils.countGlobalSteps/Utils.countGlobalIterations);
        System.out.println("\nQuantidade de pontos Testados = "+n);
        System.out.println("Tempo de execução = "+totalTime);
    }
    /** ALGORITMO WOLFE */
    public static Vec2 OtimzarFuncaoAlgo(Vec2 start, Algo algo){
        String msg = algo==Algo.ARMIJO?"Armijo":"Wolf";
        Vec2 X = start;
        while(true){
            Utils.countLocalIterations++;
            double v = Utils.norma(Utils.gradient2(X));
            //System.out.println("Tentando critério de parada |"+count+"| com a norma: "+v+"\n");
            if(v < 0.000001){ // encontr
                //System.out.println("Critério encontraro. Algoritmo encerrado");
                // System.out.println(msg+"-Ponto inicial = ("+start.x+","+start.y+")");
                // System.out.println(msg+"-Ponto crítico encontrado = ("+X.x+","+X.y+")");
                // System.out.println(msg+"-Somatório dos passos = "+Utils.countLocalSteps);
                // System.out.println(msg+"-Iteradas = "+Utils.countLocalIterations+"\n");
                Utils.countGlobalSteps+=Utils.countLocalSteps;
                Utils.countLocalSteps=0.0;
                Utils.countGlobalIterations+=Utils.countLocalIterations;
                Utils.countLocalIterations=0;
                return X;
            }
            //System.out.println("Não funcionou. Tentando novamente encontrar um alpha \n");
            if (algo==Algo.ARMIJO) {
                X = Armijo2.proxIter(X);
            }else{
                X = Wolf2.proxIter(X);
            }
        }
    }

    public static Vec2[] generatePoints(int n){
        int min = -1000;
        int max = 1000;
       
        Vec2[] pointList = new Vec2[n];
        for(int i=0;i<n;i++){ // gera os n Vec2
            double x = generateRandom(min, max);
            double y = generateRandom(min, max);
            Vec2 X = new Vec2(y,x);
            pointList[i] = X;  
        } 
        return pointList;
    }

    public static double generateRandom(double min, double max){
        Random random = new Random();
        double frac = random.nextDouble();
        double value = ((max-min)*frac)-Math.abs(min);
        return value;
    }

    enum Algo {
        ARMIJO,WOLF
    }
}