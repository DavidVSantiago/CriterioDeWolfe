import data_structures.*;

class Principal{
    public static void main(String[] args) {
        double x = 2.0;
        double y = 3.0;
        Point result = gradient(new Point(x, y));

        System.out.println("O gradiente no ponto ("+x+","+y+") = ("+result.x+","+result.y+")");

    }
    /** ALGORITMO WOLFE */
    public static Point worfeAlgo(Point point){
        Point DK;
        Point X = point;
        while(true){
            double v = norma(gradient(X));
            if(v <0.000001) return X;
            DK = multEscalarPoint(-1, gradient(X));
            X = proxIter(X);
        }

    }
    
    public static Point proxIter(Point X){
       return null;
    }

    /** FUNÇÕES AUXILIARES */

    public static MatLine transpor(MatCol matCol){
        MatLine matLine = new MatLine(matCol.data.length);
        for(int i=0; i<matLine.data.length; i++){
            matLine.data[i]=matCol.data[i];
        }
        return matLine;
    }

    public static double norma(Point point){
        return  Math.sqrt( Math.pow(point.x, 2) +  Math.pow(point.y, 2));
    }

    public static Point gradient(Point point){
        return new Point(2*point.x, (2*point.y)+1);
    }

    public static Point multEscalarPoint(double escalar, Point point){
        return new Point(escalar*point.x, escalar*point.y);
    }

    public static Point somaPointPoint(Point p1, Point p2){
        return new Point(p1.x+p2.x, p1.y+p2.y); 
    }
}