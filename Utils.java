/* Esta classe possuias funções auxiliares aos algoritmos */

import data_structures.Vec2;

public class Utils {

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

    public static double produtoInternoVec2(Vec2 v1, Vec2 v2){
        double result = (v1.x*v2.x) + (v1.y*v2.y);
        return result;
    }
}
