package lista;

import interfaces.Pilha;

import java.lang.reflect.Array;
import java.util.Objects;

public class PilhaLista<T> implements Pilha<T> {
    private NoLista<T> topo;
    private int qtdElementos;

    public void push(T element) throws Exception {
        this.topo = new NoLista<T>(element, this.topo);
        this.qtdElementos++;
    }

    public T pop() throws Exception {
        if (this.vazia()) {
            throw new Exception("A fila está vazia");
        }

        NoLista<T> removido = this.topo;
        this.topo = this.topo.getNext();
        this.qtdElementos--;

        return removido.getInfo();
    }

    public T top() throws Exception {
        if (this.vazia()) {
            throw new Exception("A fila está vazia");
        }

        return this.topo.getInfo();
    }

    public boolean vazia() {
        return this.qtdElementos == 0;
    }

    public void libera() {
        this.topo = null;
    }

    public T[] toArray(Class<T> c) {
        T[] array = (T[]) Array.newInstance(c, this.qtdElementos);

        if (array.length == 0) {
            return array;
        }

        int indice = 0;
        NoLista<T> noLista = this.topo;
        array[indice] = noLista.getInfo();

        while (Objects.nonNull(noLista.getNext())) {
            indice++;
            noLista = noLista.getNext();

            array[indice] = noLista.getInfo();
        }

        return array;
    }

    public int size() {
        return this.qtdElementos;
    }

    public String toString() {
        StringBuilder elementos = new StringBuilder();
        int indice = 0;

        NoLista<T> noLista = this.topo;

        elementos.append(noLista.getInfo());

        while (Objects.nonNull(noLista.getNext())) {
            elementos.append(" - ");

            indice++;
            noLista = noLista.getNext();

            elementos.append(noLista.getInfo());
        }

        return elementos.toString();
    }
}
