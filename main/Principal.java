package main;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import algoritmos.Armijo2;
import algoritmos.Wolf2;
import data_structures.*;
import utils.Clock;
import utils.Utils;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // gera a lista de n pontos aleatórios
        int n = 10000;
        Vec2[] pointList = Utils.generatePoints(n);
        // gera um relógio para contar o tempo

        Execution execArmijo = new Execution(Execution.Algorithm.ARMIJO ,pointList);
        Execution execWolf = new Execution(Execution.Algorithm.WOLF ,pointList);

        // executar o algoritmo para cada um dos n pontos para Amijo
        execArmijo.start();     
        // executar o algoritmo para cada um dos n pontos para Wolf
        execWolf.start();

        /** Um lista com filtragem dos pontos de "pointList", cujos respectivos pontos críticos são iguais para ambos os algoritmos*/
        List<Vec2> subPointList = new ArrayList<Vec2>();
        // filtrar os dados dos pontos críticos
        for(int i=0;i<pointList.length;i++){
            Vec2 critPointArmijo = execArmijo.getCritPoint(i);
            Vec2 critPointWolf = execWolf.getCritPoint(i);
            // se cada ums dos i-ésimos pontos criticos forem iguais
            if(Utils.compareVec2Vec2(critPointArmijo, critPointWolf)){
                subPointList.add(pointList[i]);
            }
        }

        // impressão dos relatórios
        execArmijo.printRel();
        execWolf.printRel();

        for(int i=0;i<100;i++){
            System.out.print("Armijo:("+execArmijo.getCritPoint(i).x+","+execArmijo.getCritPoint(i).y+")");
            System.out.println(" | Wolf:("+execWolf.getCritPoint(i).x+","+execWolf.getCritPoint(i).y+")");
        }
        System.out.println("Tamanho da sublista = "+subPointList.size());
    }
}