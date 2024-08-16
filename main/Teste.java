package main;
import java.util.Scanner;

import algoritmos._old.WolfTest;
import data_structures.*;

class Teste{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //Random random = new Random();
        OtimzarFuncaoAlgo(new Vec2(0.0, 0.0));
    }
    /** ALGORITMO WOLFE */
    public static Vec2 OtimzarFuncaoAlgo(Vec2 start){
        int count = 1;
        Vec2 X = start;
        X = WolfTest.proxIter(X);
        count++;
        return X;
    }
}