import utils.Utils;
import utils.Vec2;
import java.util.ArrayList;
import java.util.Arrays;

public class Grafico1 {
    public static void run(Vec2 ponto){
        
        ArrayList<Double[]> executionList = new ArrayList<Double[]>();
        // iteração das execuções das variações do neta (21E-2 -> 999E-2)
        for(int neta=21;neta<1000;neta++){ // i vai de 21 - 999 | 979 execuções
            Double[] serieDados = MetodoGradiente.start_grafico02(ponto, (neta*0.001));
            executionList.add(serieDados);
        }

        sintetizeExecSet(executionList);
        
    }
    public static void sintetizeExecSet(ArrayList<Double[]> executionList){

        int[] iteratesSize = new int[executionList.size()];
        
        for(int i=0;i<executionList.size();i++){
            iteratesSize[i] = executionList.get(i).length;
        }
        // salva os dados em arquivo
        Utils.serieDadosParaArquivo("grafico1.txt", iteratesSize);
    }
}
