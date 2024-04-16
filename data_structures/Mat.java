package data_structures;
// representação de uma matriz linha de tamanho n
public abstract class Mat {
    public double[] data;

    public Mat(int n){
        data = new double[n];
    }

    public abstract void print();

}
