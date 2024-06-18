package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import data_structures.*;
import utils.Utils;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // gera a lista de n pontos aleatórios
        int n = 10000;
        Vec2[] pointList = Utils.generatePoints(n);
        // array para marcar as coindicências de pontos críticos das execução
        boolean[] criticalMatch = new boolean[pointList.length];

        Execution execArmijo = new Execution(Execution.Algorithm.ARMIJO ,pointList);
        Execution execWolf = new Execution(Execution.Algorithm.WOLF ,pointList);

        // executar os algoritmos
        execArmijo.start(-1); // sem limitação de iteradas 
        execWolf.start(-1); // sem limitação de iteradas

        // impressão dos relatórios
        // System.out.println("Primeira execução com "+pointList.length+" pontos");
        // execArmijo.printRel();
        // execWolf.printRel();

        /** Um lista com filtragem dos pontos de "pointList", cujos respectivos pontos críticos são iguais para ambos os algoritmos*/
        List<Vec2> subPointList = new ArrayList<Vec2>();
        // filtrar os dados dos pontos críticos
        for(int i=0;i<pointList.length;i++){
            Vec2 critPointArmijo = execArmijo.getCritPoint(i);
            Vec2 critPointWolf = execWolf.getCritPoint(i);
            // se cada ums dos i-ésimos pontos criticos forem iguais
            if(Utils.compareVec2Vec2(critPointArmijo, critPointWolf)){
                subPointList.add(pointList[i]);
                criticalMatch[i]=true;
            }else criticalMatch[i]=false;
        }

        // segunda execução, com os pontos iniciais filtrados, aqueles que resultam no mesmo ponto crítico
        execArmijo = new Execution(Execution.Algorithm.ARMIJO ,Utils.listToArray(subPointList));
        execWolf = new Execution(Execution.Algorithm.WOLF ,Utils.listToArray(subPointList));

        // executar os algoritmos novamente.
        execWolf.start(-1); // sem limitação de iteradas
        execArmijo.start(execWolf.getTotalIterates()); // com limitação de iteradas   
        
        // impressão dos relatórios
        System.out.println("Nova execução com "+subPointList.size()+" pontos (os filtrados)");
        execWolf.printRel();
        execArmijo.printRel();
   
    }
}