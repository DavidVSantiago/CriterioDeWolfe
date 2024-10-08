import java.util.Scanner;

import utils.Vec2;

class Principal{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Vec2 ponto = new Vec2(5, 5);
        System.out.println("\nWOLF");
        Double[] serieDados = MetodoGradiente.start_grafico01(ponto);
        
    }
}