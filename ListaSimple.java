package UsoListas;

public class ListaSimple {
    private Nodo cabeza;
    private int tamaño;

    public ListaSimple() {
        cabeza = null;
        tamaño = 0;
    }

    // Constructor que recibe un Nodo y un tamaño
    public ListaSimple(Nodo cabeza, int tamaño) {
        this.cabeza = cabeza;
        this.tamaño = tamaño;
    }

    // 1. Agregar al inicio
    public void agregarInicio(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamaño++;
    }

    
}
