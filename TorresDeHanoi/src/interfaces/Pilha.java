package interfaces;

public interface Pilha<T> {
    public void push(T element) throws Exception;
    public T pop() throws Exception;
    public T top() throws Exception;
    public boolean vazia();
    public void libera();
    public T[] toArray(Class<T> c);
    public int size();
}
