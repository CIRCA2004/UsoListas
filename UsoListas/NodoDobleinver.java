package UsoListas;

public class NodoDobleinver<T> {
    T dato;
    NodoDobleinver<T> siguiente;
    NodoDobleinver<T> anterior;

    public NodoDobleinver(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}
