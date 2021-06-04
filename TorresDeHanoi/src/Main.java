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
        String movimentoTorres = "";

        boolean venceu = false;

        do {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            jogoTorreHanoi.imprimir();
            torreOrigem  = 0;
            torreDestino = 0;


            while(torreOrigem == 0 || torreDestino == 0) {
                System.out.print("Insira a torre de origem e a torre de destino dentro dos seguintes formatos\n" +
                        "Padrão 1, Ex: \"11\", \"32\", \"21\"\n" +
                        "Padrão 2, Ex: \"3UmTextoQualquer1\", \"3Lorem2Ipsum\", \"a primeira torre vai receber 2 e a segunda torre1\"\n");
                //movimentoTorres = scanner.next();
                movimentoTorres = scanner.nextLine();
                if (!movimentoTorres.isEmpty()) {
                    boolean atribuicaoPadrao = false;

                    if(movimentoTorres.contains(" ") && movimentoTorres.replaceAll("[^0-9]", "").length() == 2){
                        //int[] movsTorresScanner = jogoTorreHanoi.constroiEntradaScanner(movimentoTorres);
                        movimentoTorres = jogoTorreHanoi.constroiEntradaScanner(movimentoTorres);
                        atribuicaoPadrao = true;
                        //torreOrigem  = movsTorresScanner[0];
                        //torreDestino = movsTorresScanner[1];
                    }

                    if(movimentoTorres.equals("sair") || movimentoTorres.equals("-1")){
                        System.exit(1);
                    }else if (movimentoTorres.matches("[0-9]+") && movimentoTorres.length() == 2) {
                        //Verifica se as posições das torres(Origem e Destino) foram informadas em sequencia;
                        //Caso responda ao padrão (NumeroNumero), ex: "11", "23", "31", a jogada é valida.
                        atribuicaoPadrao = true;
                    } else if (movimentoTorres.replaceAll("[^0-9]", "").length() == 2) {
                        movimentoTorres = movimentoTorres.replaceAll("[^0-9]", "");
                        atribuicaoPadrao = true;
                    } else if (movimentoTorres.matches("[0-9]+") && movimentoTorres.length() == 1) {
                        if(torreOrigem <= 0) {
                            torreOrigem  = Integer.valueOf(movimentoTorres);
                        }else{
                            torreDestino = Integer.valueOf(movimentoTorres);
                        }
                    }

                    if (atribuicaoPadrao) {
                        //movimentoTorres.charAt() retorna o char em ASCII
                        torreOrigem  = Integer.valueOf(Character.getNumericValue(movimentoTorres.charAt(0)));
                        torreDestino = Integer.valueOf(Character.getNumericValue(movimentoTorres.charAt(1)));
                    }
                }
            }

            jogoTorreHanoi.moverDisco(torreOrigem, torreDestino);

            venceu = jogoTorreHanoi.verificarVencedor();

        } while (!venceu && torreOrigem != -1 && torreDestino != -1);

        if (venceu) {
            System.out.println("Você venceu!!!");
            System.out.println("Quantidade de movimentos: " + jogoTorreHanoi.getMovimentos());
        }
    }
}
