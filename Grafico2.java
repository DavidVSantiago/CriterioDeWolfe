import utils.Utils;
import utils.Vec2;
import java.util.ArrayList;
import java.util.Arrays;

public class Grafico2 {
    public static void run(){
        ArrayList<String> finalDataList = new ArrayList<String>(); // lista final de dados do arquivo 
        
        /** Realiza a geração dos conjuntos de execuções */
        int EXEC_SET_QTD = 100; // quantidade de conjuntos de execução
        for(int i=0;i<EXEC_SET_QTD;i++){
            Vec2 ponto = Utils.generatePoint(-100.0, 100);
            ArrayList<Double[]> executionList = new ArrayList<Double[]>();
            // iteração das execuções das variações do neta (21E-2 -> 999E-2)
            for(int neta=21;neta<1000;neta++){ // i vai de 21 - 999 | 979 execuções
                Double[] serieDados = MetodoGradiente.start_grafico01(ponto, (neta*0.001));
                executionList.add(serieDados);
            }
            extractExecSet(finalDataList,executionList);
        }
        
        /* Calculo da média das execução. Sintetização dos resultados para o gráfico */
        sintetizeExecSet(finalDataList);
    }
}
