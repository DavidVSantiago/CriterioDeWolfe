import java.util.Random;

import javax.sound.midi.Soundbank;

import data_structures.*;

class Principal{
    public static void main(String[] args) {
        Random random = new Random();
        

        for(int i=0;i<5;i++) { // 5 reptições
            double x = (random.nextDouble()*5)+5; // entre 5 e 10
            double y = (random.nextDouble()*5)+5; // entre 5 e 10
            if(random.nextInt()%2==0) x*=-1;
            if(random.nextInt()%2==0) y*=-1;
            System.out.println("X = "+x+" | Y = "+y);
            wolfeAlgo(new Vec2(x, y));
            System.out.println("****************************************");
            System.out.println("****************************************");
            System.out.println("****************************************");
        }
        
        // wolfeAlgo(new Vec2(0.0, 0.0));
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // wolfeAlgo(new Vec2(0.0, 0.0));
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // wolfeAlgo(new Vec2(0.0, 0.0));
        // System.out.println("****************************************");
        // System.out.println("****************************************");
        // System.out.println("****************************************");

    }
    /** ALGORITMO WOLFE */
    public static Vec2 wolfeAlgo(Vec2 start){
        int count = 1;
        Vec2 X = start;
        while(true){
            double v = norma(gradient(X));
            System.out.println("Tentando critério de parada |"+count+"| com a norma: "+v);
            if(v < 0.000001){ // encontr
                System.out.println("Critério encontraro. Algoritmo encerrado");
                System.out.println("Ponto crítico encontrado = ("+X.x+","+X.y+")");
                return X;
            }
            //System.out.println("Não funcionou. Tentando novamente encontrar um alpha \n");
            X = proxIter(X);
            count++;
        }

    }
    
    public static Vec2 proxIter(Vec2 X){
        Vec2 DK = multEscalarVec2(-1, gradient(X));
        double alpha = 1.0;
        double n1 = 0.001;
        while (true) { // busca de um alpha valido
            //System.out.println("Testando o Alpha = "+alpha+"!");  
            Vec2 X2 = somaVec2Vec2(X,multEscalarVec2(alpha, DK)); // cria a próxima geração do X
            double compareEsq = objetivo(X2);
            double compareDir = (objetivo(X) + (alpha*n1*produtoInternoVec2(gradient(X),DK)));
            //System.out.println(compareEsq+"<="+compareDir);
            if(compareEsq <= compareDir){ // comapração de Armijo
                //System.out.println("Alpha encontrado: "+alpha);
                //X = X2;
                if(objetivo(X)<objetivo(X2)){
                    //System.out.println("Alerta! ERRO!");
                }else{
                    //System.out.println("OK!");
                }
                //System.out.println("Função objetivo no ponto Xk-1 = "+objetivo(X));
                //System.out.println("Função objetivo no ponto Xk = "+objetivo(X2));
                return X2;
            }
            //System.out.println("Alpha = "+alpha+" não serve!");  
            alpha=alpha*0.2;
        }
    }

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

    public static double objetivo(Vec2 vec2){
        double result = 0.25*( ((Math.pow(( (4*vec2.x) - 2),2))*(Math.pow(( (0.25*vec2.x) - 1),2))) - vec2.x + Math.pow(vec2.y,2) + 2);
        return result;
    }

    /** FUNÇÕES AUXILIARES */

    public static MatLine transpor(MatCol matCol){
        MatLine matLine = new MatLine(matCol.data.length);
        for(int i=0; i<matLine.data.length; i++){
            matLine.data[i]=matCol.data[i];
        }
        return matLine;
    }
    
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