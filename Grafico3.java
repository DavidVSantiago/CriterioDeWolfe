import utils.Utils;
import utils.Vec2;
import java.util.ArrayList;
import java.util.Arrays;

public class Grafico3 {
    public static void run(){
        ArrayList<String> finalDataList = new ArrayList<String>(); // lista final de dados do arquivo 
        
        /** Realiza a geração dos conjuntos de execuções */
        int EXEC_SET_QTD = 100; // quantidade de conjuntos de execução
        for(int i=0;i<EXEC_SET_QTD;i++){
            Vec2 ponto = Utils.generatePoint(-100, 100);
            ArrayList<Double> mediaTamPassoList = new ArrayList<Double>();
            // iteração das execuções das variações do neta (21E-2 -> 999E-2)
            for(int neta=21;neta<1000;neta++){ // i vai de 21 - 999 | 979 execuções
                Double[] serieDados = MetodoGradiente.start_grafico03(ponto, (neta*0.001));
                double mediaTamPasso = calcMediaTamPasso(serieDados);
                mediaTamPassoList.add(mediaTamPasso);
            }
            extractExecSet(finalDataList,mediaTamPassoList);
        }
        
        /* Calculo da média das execução. Sintetização dos resultados para o gráfico */
        sintetizeExecSet(finalDataList);
    }

    public static double calcMediaTamPasso(Double[] serieDados){
        double media=0;
        for(int i=0;i<serieDados.length;i++){
            media+=serieDados[i];
        }
        return media/=serieDados.length;
    }


    public static void extractExecSet(ArrayList<String> finalDataList, ArrayList<Double> mediaTamPassoList){
        /** Essa seção extrai as quantidades de iterações de do conjunto de execução */
        double[] iter = new double[mediaTamPassoList.size()];
        for(int i=0;i<mediaTamPassoList.size();i++){ // percorre a lista de execuções
            Double exec = mediaTamPassoList.get(i); // obtem cada uma das execuções
            iter[i] = exec; // obtém a quantidade de iterações de cada execução
        }

        /** Após a extração das quantidades de iterações, adicionamos esses dados como uma linha no array de string que representa os dados finais */
        String resultado = String.join(",", Arrays.toString(iter).replaceAll("[\\[\\]\\s]", ""));
        finalDataList.add(resultado);
    }
    public static void sintetizeExecSet(ArrayList<String> finalDataList){
        /* extrai o primeiro conjunto de execuções e o acumula na lista final*/
        String[] numbersListTemp = finalDataList.get(0).split(",");
        double[] sintNumbersList = new double[numbersListTemp.length]; // lista que armazena os valore das execuções dos grupos de forma sintetizada
        for(int j=0;j<numbersListTemp.length;j++){ // percorre a lista de numeros em formato de texto e os converte para inteiro
            sintNumbersList[j] += Double.parseDouble(numbersListTemp[j]);
        }

        /* extrai os demais conjuntos de execuções e os acumula na lista final*/
        for(int i=1;i<finalDataList.size();i++){ // percorre cada um dos conjuntos de execução, a partir do segundo
            String line = finalDataList.get(i); // obtem cada um dos grupos de execução
            numbersListTemp = line.split(",");
            for(int j=0;j<numbersListTemp.length;j++){ // percorre a lista de numeros em formato de texto e os converte para inteiro
                sintNumbersList[j] += Double.parseDouble(numbersListTemp[j]);
            }
            System.out.println("");
        }

        /* Após os valores terem sido acumulados, precisamos dividir cada um pela quantidade de valores acumulados (tirar a média) */
        for(int i=0;i<sintNumbersList.length;i++){ // percorre cada um dos valores acumulados
            sintNumbersList[i]/=finalDataList.size();
        }

        // salva os dados em arquivo
        Utils.serieDadosDoubleParaArquivo("grafico3.txt", sintNumbersList);
    }
}
