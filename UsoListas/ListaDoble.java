package UsoListas;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoble<T extends Comparable<T>> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamaño;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // 1. agregarInicio
    public void agregarInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamaño++;
    }

    // 2. agregarFinal
    public void agregarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamaño++;
    }

    // 3. Agregar en posición específica
    public void agregar(int index, T dato) {
        if (!indiceValido(index)) throw new IndexOutOfBoundsException("Índice inválido");

        if (index == 0) {
            agregarInicio(dato);
        } else if (index == tamaño) {
            agregarFinal(dato);
        } else {
            NodoDoble<T> actual = obtenerNodo(index);
            NodoDoble<T> nuevo = new NodoDoble<>(dato);
            NodoDoble<T> anterior = actual.getAnterior();

            anterior.setSiguiente(nuevo);
            nuevo.setAnterior(anterior);
            nuevo.setSiguiente(actual);
            actual.setAnterior(nuevo);

            tamaño++;
        }
    }

    // 4. obtenerValorNodo
    public T obtenerValorNodo(int index) {
        return obtenerNodo(index).getDato();
    }

    // 5. obtenerNodo
    public NodoDoble<T> obtenerNodo(int index) {
        if (!indiceValido(index)) throw new IndexOutOfBoundsException("Índice inválido");

        NodoDoble<T> actual;
        if (index < tamaño / 2) {
            actual = cabeza;
            for (int i = 0; i < index; i++) {
                actual = actual.getSiguiente();
            }
        } else {
            actual = cola;
            for (int i = tamaño - 1; i > index; i--) {
                actual = actual.getAnterior();
            }
        }
        return actual;
    }

    // 6. obtenerPosicionNodo
    public int obtenerPosicionNodo(T dato) {
        NodoDoble<T> actual = cabeza;
        int index = 0;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return index;
            actual = actual.getSiguiente();
            index++;
        }
        return -1; // no encontrado
    }

    // 7. indiceValido
    public boolean indiceValido(int index) {
        return index >= 0 && index <= tamaño;
    }

    // 8. estaVacia
    public boolean estaVacia() {
        return tamaño == 0;
    }

    // 9. eliminarPrimero
    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
        }
        tamaño--;
    }

    // 10. eliminarUltimo
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        tamaño--;
    }

    // 11. Eliminar por valor
    public boolean eliminar(T dato) {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                if (actual == cabeza) {
                    eliminarPrimero();
                } else if (actual == cola) {
                    eliminarUltimo();
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                    tamaño--;
                }
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    // 12. modificarNodo
    public void modificarNodo(int index, T nuevoDato) {
        NodoDoble<T> nodo = obtenerNodo(index);
        nodo.setDato(nuevoDato);
    }

    // 13. ordenarLista (Burbuja simple)
    public void ordenarLista() {
        if (tamaño <= 1) return;

        boolean cambiado;
        do {
            cambiado = false;
            NodoDoble<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                if (actual.getDato().compareTo(actual.getSiguiente().getDato()) > 0) {
                    T temp = actual.getDato();
                    actual.setDato(actual.getSiguiente().getDato());
                    actual.getSiguiente().setDato(temp);
                    cambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    // 14. imprimirLista
    public void imprimirLista() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    // 15. Iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }

    // 16. borrarLista
    public void borrarLista() {
        cabeza = cola = null;
        tamaño = 0;
    }
}