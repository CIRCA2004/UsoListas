package UsoListas;

public class NodoSimpleInver<T> {
    T dato;
    NodoSimpleInver<T> siguiente;

    public NodoSimpleInver(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
