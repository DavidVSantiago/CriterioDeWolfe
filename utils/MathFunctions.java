package utils;

/* Esta classe possuias funções auxiliares aos algoritmos */
public class MathFunctions{

    /** NORMA */
    public static double norma(Vec2 vec2){
        return Math.sqrt( Math.pow(vec2.x, 2) +  Math.pow(vec2.y, 2));
    }

    /** FUNÇÃO GRADIENTE */
    public static Vec2 gradient(Vec2 vec2){
        double x = (-85+(122*vec2.x)-42*(vec2.x*vec2.x)+4*(Math.pow(vec2.x, 3))) / 6.0;
        double y = vec2.y/3.0;
        return new Vec2(x,y);
    }

    /** FUNÇÃO OBJETIVO */
    public static double objetivo(Vec2 vec2){
        return ( ((Math.pow(( (6*vec2.x) - 6),2))*(Math.pow(( (vec2.x/6.0) - 1),2))) - vec2.x + Math.pow(vec2.y,2) - 8)/6.0;
    }
    
    /** MULTIPLICAÇÃO ESCALAR x VETOR */
    public static Vec2 multEscalarVec2(double escalar, Vec2 vec2){
        return new Vec2(escalar*vec2.x, escalar*vec2.y);
    }

    /** SOMA DE 2 VETORES */
    public static Vec2 sumVec2Vec2(Vec2 v1, Vec2 v2){
        return new Vec2(v1.x+v2.x, v1.y+v2.y); 
    }

    /** PRODUTO INTERNO ENTRE DOIS VETORES */
    public static double innerProductVec2(Vec2 v1, Vec2 v2){
        return ((v1.x*v2.x) + (v1.y*v2.y));
    }
}
