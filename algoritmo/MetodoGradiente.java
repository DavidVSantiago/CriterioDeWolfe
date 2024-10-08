import java.util.ArrayList;

import utils.MathFunctions;
import utils.Vec2;
import wolf.WolfGrafico01;
import wolf.WolfGrafico01.DadosRetorno;


public class MetodoGradiente {
 
    /** Função de execução do algoritmo para a geração do Gráfico 01
     * @return Retorna uma lista com os dados para gráfico
    */
    public static Double[] start_grafico01(Vec2 startPoint){
        
        ArrayList<Double> graphData = new ArrayList<Double>(); // estrutura que conterá a série de dados do gráfico 01
        Vec2 X = startPoint;
        int iterate=0; // contador de iteradas
        while(true){ // laço das iteradas
            Double stepSize = 0.0; // a cada iterada, um novo registro de tamanho de passo
            double v = MathFunctions.norma(MathFunctions.gradient(X)); // calcula a norma do gradiente
            if(v < 0.000001){ // condição de parada das iteradas
                break; // termina as iteradas, abandonando a estrutura de repetição
            }
            DadosRetorno retorno = WolfGrafico01.proxIter(X); // executa wolf e obtém os dados de execução do algoritmo
            X = retorno.X; // captura o valor de X
            stepSize = retorno.stepSize; // captura o tamanho de passo da iteração
    
            //System.out.println("Função objetivo = "+MathFunctions.objetivo(X) );
            //System.out.println("Iterada "+(iterate+1)+" | X=("+X.x+","+X.y+") | Norma="+v);
            graphData.add(stepSize); // adiciona o o tamanho de passo na série de dados
            iterate++;
        }
        return graphData.toArray(new Double[graphData.size()]); // converte o arraylist para array cru
    }
}
