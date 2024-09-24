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
    
    // métodos
    private void init(Algorithm algorithm,Vec2[] pointList){
        this.algorithm=algorithm;
        this.pointList = pointList;
        criticPointList = new Vec2[pointList.length];
        totalIterates = 0;
        totalStepsSize = 0.0;
        totalTime = 0.0;this.algorithm=algorithm;
        clock = new Clock();
    }
    /** Executa o algoritmo limitando a quantidade de iteradas*/
    public void start(long maxIterates,Algorithm algorithm,Vec2[] pointList){
        init(algorithm, pointList);
        clock.setStartTime(); // marca o inicio do tempo
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

    /** Executa o algoritmo para a geração do gráfico tipo 1
     * @return - array com a série de dados para o gráfico */
    public Double[] startType01(Algorithm algorithm,Vec2 point){
        ArrayList<Double[]> execList = new ArrayList<Double[]>();
        for(int i=2;i<=29;i++){
            double n2 = i*0.01;
            Double[] exec = MetodoGradiente.startType01(algorithm,point,n2);
            execList.add(exec);
        }
        return null;
        
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

    public void printGraph01(){
        String algorithmName = (algorithm==Algorithm.ARMIJO)?"ARMIJO":"WOLF";
        System.out.println("*************************************************");

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
