import enums.Alocacao;
import hanoi.JogoTorreHanoi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        JogoTorreHanoi jogoTorreHanoi = new JogoTorreHanoi(Alocacao.VETOR);

        if (args.length != 0 && args[0].toLowerCase().equals("dinamica")) {
            jogoTorreHanoi = new JogoTorreHanoi(Alocacao.LISTA);
        }

        Scanner scanner = new Scanner(System.in);

        int torreOrigem = 0;
        int torreDestino = 0;

        boolean venceu = false;

        do {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            jogoTorreHanoi.imprimir();

            System.out.print("\nEscolha torre de origem: ");
            torreOrigem = scanner.nextInt();

            System.out.print("Escolha torre de Destino: ");
            torreDestino = scanner.nextInt();

            jogoTorreHanoi.moverDisco(torreOrigem, torreDestino);

            venceu = jogoTorreHanoi.verificarVencedor();

        } while (!venceu && torreOrigem != -1 && torreDestino != -1);

        if (venceu) {
            System.out.println("Você venceu!!!");
            System.out.println("Quantidade de movimentos: " + jogoTorreHanoi.getMovimentos());
        }
    }
}
