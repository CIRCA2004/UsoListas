package UsoListas;

public class NodoInver<T> {
    private T dato;
    private NodoInver<T> siguiente;

    public NodoInver(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoInver<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoInver<T> siguiente) {
        this.siguiente = siguiente;
    }
}
