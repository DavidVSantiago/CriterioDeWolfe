package data_structures;
public class MatLine extends Mat{

    public MatLine(int n){
        super(n);
    }

    // imprime a matriz linha
    public void print(){
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+"|");
        }
    }
    
}
