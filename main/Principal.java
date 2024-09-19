package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import data_structures.*;
import utils.Utils;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // // gera a lista de n pontos aleatórios
        // int n = 10;
        // Vec2[] pointList = Utils.generatePoints(n);
        // // array para marcar as coindicências de pontos críticos das execução
        // boolean[] criticalMatch = new boolean[pointList.length];

        // Execution execArmijo = new Execution();
        // Execution execWolf = new Execution();

        // // executar os algoritmos
        // execArmijo.start(-1,Execution.Algorithm.ARMIJO ,pointList); // sem limitação de iteradas 
        // execWolf.start(-1,Execution.Algorithm.WOLF ,pointList); // sem limitação de iteradas

        // /** Um lista com filtragem dos pontos de "pointList", cujos respectivos pontos críticos são iguais para ambos os algoritmos*/
        // List<Vec2> subPointList = new ArrayList<Vec2>();
        // // filtrar os dados dos pontos críticos
        // for(int i=0;i<pointList.length;i++){
        //     Vec2 critPointArmijo = execArmijo.getCritPoint(i);
        //     Vec2 critPointWolf = execWolf.getCritPoint(i);
        //     // se cada ums dos i-ésimos pontos criticos forem iguais
        //     if(Utils.compareVec2Vec2(critPointArmijo, critPointWolf)){
        //         subPointList.add(pointList[i]);
        //         criticalMatch[i]=true;
        //     }else criticalMatch[i]=false;
        // }

        // // segunda execução, com os pontos iniciais filtrados, aqueles que resultam no mesmo ponto crítico
        // execArmijo = new Execution();
        // execWolf = new Execution();

        // // executar os algoritmos novamente.
        // execWolf.start(-1,Execution.Algorithm.WOLF ,Utils.listToArray(subPointList)); // sem limitação de iteradas
        // execArmijo.start(execWolf.getTotalIterates(),Execution.Algorithm.ARMIJO ,Utils.listToArray(subPointList)); // sem limitação de iteradas   
        
        // // impressão dos relatórios
        // System.out.println("Nova execução com "+subPointList.size()+" pontos (os filtrados)");
        // execWolf.printRel();
        // execArmijo.printRel();

        // // System.out.println("SUBLISTA DE PONTOS");
        // // for (int i=0;i<subPointList.size();i++) {
        // //     System.out.println("ponto "+i+":("+subPointList.get(i).x+","+subPointList.get(i).y+")");
        // // }

        Vec2 ponto = new Vec2(5, 5);
        Execution execArmijo = new Execution();
        Execution execWolf = new Execution();

        System.out.println("\nARMIJO");
        Double[] serieDados = execArmijo.startType01(Execution.Algorithm.ARMIJO, ponto);
        // System.out.println("ARMIJO");
        // for (int i=0; i<serieDados.length;i++) {
        //     System.out.println("posX "+(i+1)+": "+serieDados[i]);
        // }
        System.out.println("\nWOLF");
        serieDados = execWolf.startType01(Execution.Algorithm.WOLF, ponto);
        Utils.serieDadosParaArquivo("grafico-01.txt", serieDados);
        // for (int i=0; i<serieDados.length;i++) {
        //     System.out.println("posX "+(i+1)+": "+serieDados[i]);
        // }
    }
}