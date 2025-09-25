package UsoListas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimpleInver<T extends Comparable<T>> implements Iterable<T> {
    private NodoSimpleInver<T> cabeza;
    private int size;

    public ListaSimpleInver() {
        cabeza = null;
        size = 0;
    }

    // 1. agregarInicio
    public void agregarInicio(T dato) {
        NodoSimpleInver<T> nuevo = new NodoSimpleInver<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        size++;
    }

    // 2. agregarFinal
    public void agregarFinal(T dato) {
        NodoSimpleInver<T> nuevo = new NodoSimpleInver<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimpleInver<T> temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
        size++;
    }

    // 3. Agregar en posición específica
    public void agregar(int posicion, T dato) {
        if (!indiceValido(posicion) || posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (posicion == 0) {
            agregarInicio(dato);
        } else {
            NodoSimpleInver<T> nuevo = new NodoSimpleInver<>(dato);
            NodoSimpleInver<T> temp = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                temp = temp.siguiente;
            }
            nuevo.siguiente = temp.siguiente;
            temp.siguiente = nuevo;
            size++;
        }
    }

    // 4. obtenerValorNodo
    public T obtenerValorNodo(int posicion) {
        return obtenerNodo(posicion).dato;
    }

    // 5. obtenerNodo
    public NodoSimpleInver<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        NodoSimpleInver<T> temp = cabeza;
        for (int i = 0; i < posicion; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    // 6. obtenerPosicionNodo
    public int obtenerPosicionNodo(T dato) {
        NodoSimpleInver<T> temp = cabeza;
        int index = 0;
        while (temp != null) {
            if (temp.dato.equals(dato)) {
                return index;
            }
            temp = temp.siguiente;
            index++;
        }
        return -1;
    }

    // 7. indiceValido
    public boolean indiceValido(int indice) {
        return indice >= 0 && indice < size;
    }

    // 8. estaVacia
    public boolean estaVacia() {
        return cabeza == null;
    }

    // 9. eliminarPrimero
    public void eliminarPrimero() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
            size--;
        }
    }

    // 10. eliminarUltimo
    public void eliminarUltimo() {
        if (cabeza == null) return;
        if (cabeza.siguiente == null) {
            cabeza = null;
        } else {
            NodoSimpleInver<T> temp = cabeza;
            while (temp.siguiente.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = null;
        }
        size--;
    }

    // 11. Eliminar por valor
    public void eliminar(T dato) {
        if (cabeza == null) return;
        if (cabeza.dato.equals(dato)) {
            eliminarPrimero();
            return;
        }
        NodoSimpleInver<T> temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.dato.equals(dato)) {
            temp = temp.siguiente;
        }
        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
            size--;
        }
    }

    // 12. modificarNodo
    public void modificarNodo(int posicion, T nuevoDato) {
        NodoSimpleInver<T> nodo = obtenerNodo(posicion);
        nodo.dato = nuevoDato;
    }

    // 13. ordenarLista (simple burbuja)
    public void ordenarLista() {
        if (cabeza == null || cabeza.siguiente == null) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoSimpleInver<T> actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.dato.compareTo(actual.siguiente.dato) > 0) {
                    T temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    cambiado = true;
                }
                actual = actual.siguiente;
            }
        } while (cambiado);
    }

    // 14. imprimirLista
    public void imprimirLista() {
        NodoSimpleInver<T> temp = cabeza;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }

    // 15. Iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            NodoSimpleInver<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }

    // 16. borrarLista
    public void borrarLista() {
        cabeza = null;
        size = 0;
    }

    // 17. invertir recursivamente (como pediste)
    public void invertir() {
        if (cabeza != null && cabeza.siguiente != null) {
            cabeza = invertirRecursivo(cabeza, null);
        }
    }

    private NodoSimpleInver<T> invertirRecursivo(NodoSimpleInver<T> actual, NodoSimpleInver<T> anterior) {
        if (actual.siguiente == null) {
            actual.siguiente = anterior;
            return actual;
        }
        NodoSimpleInver<T> siguiente = actual.siguiente;
        NodoSimpleInver<T> nuevaCabeza = invertirRecursivo(siguiente, actual);
        actual.siguiente = anterior;
        return nuevaCabeza;
    }
}
