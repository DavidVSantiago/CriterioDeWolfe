import utils.Utils;
import utils.Vec2;

class Principal{
    public static void main(String[] args) {

        // obter o conjunto dos QTDs pontos
        int EXEC_SET_QTD = 100; // quantidade de conjuntos de execução
        Vec2[] pontos = Utils.generatePoints(EXEC_SET_QTD);
        Grafico2.run(pontos);
        Grafico3.run(pontos);
    }
}