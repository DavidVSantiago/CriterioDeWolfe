package utils;
/* Esta classe possuias funções auxiliares aos algoritmos */

import java.util.Random;

import data_structures.Vec2;

public class Utils {
    /** ATRIBUTOS GLOBAIS */
    public static double countGlobalSteps=0.0, countLocalSteps=0.0;
    public static int countLocalIterations=0,countGlobalIterations= 0;
    public static long totalTimeNano=0;

    /** FUNÇÕES BÁSICAS */
    public static double norma(Vec2 vec2){
        double result = Math.sqrt( Math.pow(vec2.x, 2) +  Math.pow(vec2.y, 2));
        return result;
    }

    public static Vec2 gradient(Vec2 vec2){
        double x = 0.125*(-38+(97*vec2.x)-54*(vec2.x*vec2.x)+8*(Math.pow(vec2.x, 3)));
        double y = vec2.y*0.5;
        Vec2 result = new Vec2(x,y);
        return result;
    }
    /** Função objetivo definido pela Nalanda para piorar armijo */
    public static Vec2 gradient2(Vec2 vec2){
        double x = (-85+(122*vec2.x)-42*(vec2.x*vec2.x)+4*(Math.pow(vec2.x, 3))) / 6.0;
        double y = vec2.y/3.0;
        Vec2 result = new Vec2(x,y);
        return result;
    }

    public static double objetivo(Vec2 vec2){
        double result = 0.25*( ((Math.pow(( (4*vec2.x) - 2),2))*(Math.pow(( (0.25*vec2.x) - 1),2))) - vec2.x + Math.pow(vec2.y,2) + 2);
        return result;
    }
    /** Função objetivo definido pela Nalanda para piorar armijo */
    public static double objetivo2(Vec2 vec2){
        double result = ( ((Math.pow(( (6*vec2.x) - 6),2))*(Math.pow(( (vec2.x/6.0) - 1),2))) - vec2.x + Math.pow(vec2.y,2) - 8)/6.0;
        return result;
    }


    /** FUNÇÕES AUXILIARES */
    
    public static Vec2 multEscalarVec2(double escalar, Vec2 vec2){
        Vec2 result = new Vec2(escalar*vec2.x, escalar*vec2.y);
        return result;
    }

    public static Vec2 somaVec2Vec2(Vec2 v1, Vec2 v2){
        Vec2 result = new Vec2(v1.x+v2.x, v1.y+v2.y); 
        return result;
    }
    public static boolean compareVec2Vec2(Vec2 v1, Vec2 v2){
        if(v1.x==v2.x && v1.y==v2.y) return true;
        return false;
    }

    public static double produtoInternoVec2(Vec2 v1, Vec2 v2){
        double result = (v1.x*v2.x) + (v1.y*v2.y);
        return result;
    }

    public static double generateRandom(double min, double max){
        Random random = new Random();
        double frac = random.nextDouble();
        double value = ((max-min)*frac)-Math.abs(min);
        return value;
    }

    public static Vec2[] generatePoints(int n){
        int min = -100;
        int max = 100;
       
        Vec2[] pointList = new Vec2[n];
        for(int i=0;i<n;i++){ // gera os n Vec2
            double x = generateRandom(min, max);
            double y = generateRandom(min, max);
            Vec2 X = new Vec2(y,x);
            pointList[i] = X;  
        } 
        return pointList;
    }

    
    
}
