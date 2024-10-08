package utils;

/* Esta classe possuias funções auxiliares aos algoritmos */
public class MathFunctions{

    /** NORMA */
    public static double norma(Vec2 vec2){
        double result = Math.sqrt( Math.pow(vec2.x, 2) +  Math.pow(vec2.y, 2));
        return result;
    }

    /** FUNÇÃO GRADIENTE */
    public static Vec2 gradient(Vec2 vec2){
        double x = (-85+(122*vec2.x)-42*(vec2.x*vec2.x)+4*(Math.pow(vec2.x, 3))) / 6.0;
        double y = vec2.y/3.0;
        Vec2 result = new Vec2(x,y);
        return result;
    }

    /** FUNÇÃO OBJETIVO */
    public static double objetivo(Vec2 vec2){
        double result = ( ((Math.pow(( (6*vec2.x) - 6),2))*(Math.pow(( (vec2.x/6.0) - 1),2))) - vec2.x + Math.pow(vec2.y,2) - 8)/6.0;
        return result;
    }
    
    /** MULTIPLICAÇÃO ESCALAR x VETOR */
    public static Vec2 multEscalarVec2(double escalar, Vec2 vec2){
        Vec2 result = new Vec2(escalar*vec2.x, escalar*vec2.y);
        return result;
    }

    /** SOMA DE 2 VETORES */
    public static Vec2 sumVec2Vec2(Vec2 v1, Vec2 v2){
        Vec2 result = new Vec2(v1.x+v2.x, v1.y+v2.y); 
        return result;
    }

    /** ?? NÃO LEMBRO DE TERMOS UTILIZADO ESTA ?? */
    public static boolean compareVec2Vec2(Vec2 v1, Vec2 v2){
        if(Math.abs(v1.x-v2.x)<1e-5 && Math.abs(v1.y-v2.y)<1e-5) return true;
        return false;
    }

    /** PRODUTO INTERNO ENTRE DOIS VETORES */
    public static double innerProductVec2(Vec2 v1, Vec2 v2){
        double result = (v1.x*v2.x) + (v1.y*v2.y);
        return result;
    }
}
