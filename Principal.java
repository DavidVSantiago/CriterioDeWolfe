import java.util.ArrayList;
import java.util.Scanner;

import utils.MathFunctions;
import utils.Utils;
import utils.Vec2;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Vec2 ponto = new Vec2(97.37, 38.59);//Utils.generatePoint(-100.0, 0);
        System.out.println("\nWOLF");


        ArrayList<Double[]> executionList = new ArrayList<Double[]>();

        // temporario, apenas para teste
        for(int i=0;i<=20;i++){// 20 repetições
            executionList.add(new Double[1]);
        } 
        // iteração das execuções das variações do neta (21E-2 -> 999E-2)
        for(int neta=21;neta<1000;neta++){ // i vai de 21 - 999
            Double[] serieDados = MetodoGradiente.start_grafico01(ponto, (neta*0.001));
            executionList.add(serieDados);
        } 
        System.out.println("");
        //Utils.serieDadosParaArquivo("serie_01.txt", serieDados);
    }
}