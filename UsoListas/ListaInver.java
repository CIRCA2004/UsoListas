package UsoListas;

public class ListaInver<T> {
    private NodoInver<T> cabeza;

    public ListaInver() {
        cabeza = null;
    }

    // Agregar al final
    public void agregar(T dato) {
        NodoInver<T> nuevo = new NodoInver<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoInver<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    // Imprimir lista
    public void imprimir() {
        NodoInver<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    // 17. Invertir contenido recursivamente
    public void invertirContenido() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return; // no hace nada si está vacía o tiene un solo elemento
        }
        cabeza = invertirRecursivo(cabeza, null);
    }

    private NodoInver<T> invertirRecursivo(NodoInver<T> actual, NodoInver<T> anterior) {
        // Caso base: si estamos en el último nodo, será la nueva cabeza
        if (actual.getSiguiente() == null) {
            actual.setSiguiente(anterior);
            return actual;
        }

        NodoInver<T> nuevaCabeza = invertirRecursivo(actual.getSiguiente(), actual);
        actual.setSiguiente(anterior);
        return nuevaCabeza;
    }
}
