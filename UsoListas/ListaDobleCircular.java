package UsoListas;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDobleCircular<T extends Comparable<T>> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamaño;

    public ListaDobleCircular() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // 1. agregarInicio
    public void agregarInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(cola);
            cabeza.setAnterior(nuevo);
            cola.setSiguiente(nuevo);
            cabeza = nuevo;
        }
        tamaño++;
    }

    // 2. agregarFinal
    public void agregarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            nuevo.setAnterior(cola);
            nuevo.setSiguiente(cabeza);
            cola.setSiguiente(nuevo);
            cabeza.setAnterior(nuevo);
            cola = nuevo;
        }
        tamaño++;
    }

    // 3. agregar en posición específica
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

        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    // 6. obtenerPosicionNodo
    public int obtenerPosicionNodo(T dato) {
        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < tamaño; i++) {
            if (actual.getDato().equals(dato)) return i;
            actual = actual.getSiguiente();
        }
        return -1;
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

        if (tamaño == 1) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(cola);
            cola.setSiguiente(cabeza);
        }
        tamaño--;
    }

    // 10. eliminarUltimo
    public void eliminarUltimo() {
        if (estaVacia()) return;

        if (tamaño == 1) {
            cabeza = cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(cabeza);
            cabeza.setAnterior(cola);
        }
        tamaño--;
    }

    // 11. Eliminar por valor
    public boolean eliminar(T dato) {
        if (estaVacia()) return false;

        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < tamaño; i++) {
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

    // 13. ordenarLista
    public void ordenarLista() {
        if (tamaño <= 1) return;

        boolean cambiado;
        do {
            cambiado = false;
            NodoDoble<T> actual = cabeza;
            for (int i = 0; i < tamaño - 1; i++) {
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
        if (estaVacia()) {
            System.out.println("(lista vacía)");
            return;
        }
        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < tamaño; i++) {
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
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < tamaño;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                contador++;
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