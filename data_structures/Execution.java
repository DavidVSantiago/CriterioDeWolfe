package data_structures;

import java.lang.reflect.Array;
import java.util.ArrayList;

import main.MetodoGradiente;
import utils.Clock;
import utils.Utils;

public class Execution {
    
    // Atributos
    private Algorithm algorithm;
    private Vec2[] pointList;
    private Vec2[] criticPointList;
    private long totalIterates;
    private double totalStepsSize;
    private double totalTime;
    Clock clock;

    // construtor
    public Execution(Algorithm algorithm,Vec2[] pointList){
        this.algorithm=algorithm;
        this.pointList = pointList;
        criticPointList = new Vec2[pointList.length];
        totalIterates = 0;
        totalStepsSize = 0.0;
        totalTime = 0.0;
        clock = new Clock();
    }

    // métodos
    /** Executa o algoritmo limitando a quantidade de iteradas*/
    public void start(long maxIterates){
        clock.setStartTime(); // marca o fim do tempo
        int i;
        for(i=0;i<pointList.length;i++){ // percorre os n pontos a serem executados
            // executa o método gradiente para cada um dos n pontos
            Vec2 criticalPoint = MetodoGradiente.start(this,pointList[i],maxIterates);
            if(criticalPoint==null) break;
            setCriticalPoint(criticalPoint, i);
        }
        clock.setEndTime(); // marca o fim do tempo
        totalTime = clock.getDeltaTimeInSeconds();
        clock.reset();

    }

    public void printRel(){
        String algorithmName = (algorithm==Algorithm.ARMIJO)?"ARMIJO":"WOLF";
        System.out.println("*************************************************");
        System.out.println("Execução do algoritmo "+algorithmName);
        System.out.println("Somatório Global dos Iteradas = "+totalIterates);
        System.out.println("Média do tamanho de passo por iterada = "+totalStepsSize/totalIterates);
        System.out.println("Tempo de execução = "+totalTime);
        System.out.println("*************************************************\n");
    }

    // getters
    public Algorithm getAlgorithm(){
        return this.algorithm;
    }
    public Vec2 getCritPoint(int index){
        return this.criticPointList[index];
    }
    public long getTotalIterates(){
        return this.totalIterates;
    }

    // setters
    public void addIterate(){
        this.totalIterates+=1; // mais 1 iterada
    }
    public void addStepsSize(double alpha){
        this.totalStepsSize+=alpha;
    }
    public void setCriticalPoint(Vec2 point, int index){
        this.criticPointList[index] = point;
    }
    

    // enumeradores
    public enum Algorithm {
        ARMIJO,WOLF
    }
}
