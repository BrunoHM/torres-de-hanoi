package hanoi;

import enums.Alocacao;
import lista.PilhaLista;
import interfaces.Pilha;
import vetor.PilhaVetor;

public class JogoTorreHanoi {
    private Pilha<Integer> primeraTorre;
    private Pilha<Integer> segundaTorre;
    private Pilha<Integer> terceiraTorre;

    private Pilha<Integer>[] arrayTorres;

    private int movimentos = 0;
    private String mensagemInvalida = "";

    public JogoTorreHanoi(Alocacao opcao) throws Exception {
        if (opcao == Alocacao.LISTA) {
            this.primeraTorre = new PilhaLista<>();
            this.segundaTorre = new PilhaLista<>();
            this.terceiraTorre = new PilhaLista<>();
        } else {
            this.primeraTorre = new PilhaVetor<>(5);
            this.segundaTorre = new PilhaVetor<>(5);
            this.terceiraTorre = new PilhaVetor<>(5);
        }

        this.construirTorres();
    }

    public void imprimir() {
        System.out.println("Digite -1 nas opções de torre para sair!\n");

        if (!this.mensagemInvalida.isEmpty()) {
            System.out.println(this.mensagemInvalida + "\n");
        }

        String[] arrayPrimeiraTorre = this.getTorreToString(this.primeraTorre);
        String[] arraySegundaTorre = this.getTorreToString(this.segundaTorre);
        String[] arrayTerceiraTorre = this.getTorreToString(this.terceiraTorre);

        for (int indice = 0; indice < 5; indice++) {
            System.out.println(arrayPrimeiraTorre[indice] + "   " + arraySegundaTorre[indice] + "    " + arrayTerceiraTorre[indice]);
        }
    }

    public void moverDisco(int torreOrigem, int torreDestino) throws Exception {
        if (torreOrigem == torreDestino) {
            this.mensagemInvalida = "Jogada Inválida!\nTorre de destino igual torre de origem.";
            return;
        }

        if (torreOrigem < 1 || torreOrigem > 3 || torreDestino < 1 || torreDestino > 3) {
            this.mensagemInvalida = "Jogada Inválida!\nTorre não existe.";
            return;
        }

        if (arrayTorres[torreOrigem - 1].size() == 0) {
            this.mensagemInvalida = "Jogada Inválida!\nTorre origem não tem peças.";
            return;
        }

        if (arrayTorres[torreDestino - 1].size() != 0 && arrayTorres[torreDestino - 1].top() < arrayTorres[torreOrigem - 1].top()) {
            this.mensagemInvalida = "Jogada Inválida!\nPeça da torre destino menor que peça da torre origem.";
            return;
        }

        this.movimentos++;
        this.mensagemInvalida = "";

        arrayTorres[torreDestino - 1].push(arrayTorres[torreOrigem - 1].pop());
    }

    public boolean verificarVencedor() {
        return this.terceiraTorre.size() == 5;
    }

    public int getMovimentos() {
        return movimentos;
    }

    private void construirTorres() throws Exception {
        for (int indice = 5; indice > 0; indice--) {
            this.primeraTorre.push(indice);
        }

        this.arrayTorres = new Pilha[3];

        this.arrayTorres[0] = this.primeraTorre;
        this.arrayTorres[1] = this.segundaTorre;
        this.arrayTorres[2] = this.terceiraTorre;
    }

    private String[] getTorreToString(Pilha<Integer> torre) {
        String[] arrayBarras = new String[5];

        int qtdBarras = 5 - torre.size();

        for (int idxBarra = 0; idxBarra < qtdBarras; ++idxBarra) {
            arrayBarras[idxBarra] = "|";
        }

        Integer[] arrayPecas = torre.toArray(Integer.class);

        for (int idxBarra = qtdBarras, idxPeca = 0; idxPeca < arrayPecas.length; ++idxPeca, ++idxBarra) {
            arrayBarras[idxBarra] = arrayPecas[idxPeca].toString();
        }

        return arrayBarras;
    }
}
