package vetor;

import interfaces.Pilha;

import java.lang.reflect.Array;

public class PilhaVetor<T> implements Pilha<T> {
    private int qtdElementos;
    private int tamanho;
    private T[] vetor;

    public PilhaVetor(int tamanho) {
        this.tamanho = tamanho;
        this.vetor = (T[]) new Object[tamanho];
    }

    public void push(T element) throws Exception {
        if (this.qtdElementos == this.tamanho){
            throw new Exception("A pilha esta cheia");
        }

        this.vetor[this.qtdElementos] = element;
        this.qtdElementos++;
    }

    public T pop() throws Exception {
        if (vazia()){
            throw new Exception("A pilha está vazia");
        }

        T removido = this.vetor[this.qtdElementos - 1];
        this.qtdElementos--;

        return removido;
    }

    public T top() throws Exception {
        if (vazia()){
            throw new Exception("A pilha está vazia");
        }

        return this.vetor[this.qtdElementos - 1];
    }

    public boolean vazia() {
        return this.qtdElementos == 0;
    }

    public void libera() {
        this.vetor = (T[]) new Object[tamanho];
    }

    public T[] toArray(Class<T> c) {
        T[] temp = (T[]) Array.newInstance(c, this.qtdElementos);

        int valorIndice = this.qtdElementos - 1;

        for (int indice = valorIndice, idxTemp = 0; indice >= 0; indice--, idxTemp++){
            temp[idxTemp] = this.vetor[indice];
        }

        return temp;
    }

    public int size() {
        return this.qtdElementos;
    }

    public String toString() {
        StringBuilder elementos = new StringBuilder();

        for (int indice = this.qtdElementos - 1; indice >= 0; indice--) {
            if (indice != this.qtdElementos - 1)
                elementos.append(" - ");

            elementos.append(this.vetor[indice]);
        }

        return elementos.toString();
    }
}
