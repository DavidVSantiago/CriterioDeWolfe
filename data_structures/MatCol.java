package data_structures;
public class MatCol extends Mat{

    public MatCol(int n){
        super(n);
    }

    // imprime a matriz coluna
    public void print(){
        for(int i=0;i<data.length;i++){
            System.out.println(data[i]);
        }
    }
    
}
