package UsoListas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimpleCircular<T extends Comparable<T>> implements Iterable<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;

    public ListaSimpleCircular() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    // 1. Agregar al inicio
    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza;
        }
        tamano++;
    }

    // 2. Agregar al final
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
        tamano++;
    }

    // 3. Agregar en una posición específica
    public void agregar(int posicion, T valor) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        if (posicion == 0) {
            agregarInicio(valor);
        } else if (posicion == tamano) {
            agregarFinal(valor);
        } else {
            Nodo<T> nuevo = new Nodo<>(valor);
            Nodo<T> temp = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                temp = temp.siguiente;
            }
            nuevo.siguiente = temp.siguiente;
            temp.siguiente = nuevo;
            tamano++;
        }
    }

    // 4. Obtener valor de un nodo
    public T obtenerValorNodo(int posicion) {
        return obtenerNodo(posicion).valor;
    }

    // 5. Obtener nodo por posición
    private Nodo<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        Nodo<T> temp = cabeza;
        for (int i = 0; i < posicion; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    // 6. Obtener posición de un nodo dado su valor
    public int obtenerPosicionNodo(T valor) {
        if (cabeza == null) return -1;

        Nodo<T> temp = cabeza;
        int index = 0;
        do {
            if (temp.valor.equals(valor)) return index;
            temp = temp.siguiente;
            index++;
        } while (temp != cabeza);
        return -1;
    }

    // 7. Validar índice
    private boolean indiceValido(int posicion) {
        return posicion >= 0 && posicion <= tamano;
    }

    // 8. ¿Está vacía?
    public boolean estaVacia() {
        return cabeza == null;
    }

    // 9. Eliminar primero
    public void eliminarPrimero() {
        if (cabeza == null) return;

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        }
        tamano--;
    }

    // 10. Eliminar último
    public void eliminarUltimo() {
        if (cabeza == null) return;

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.siguiente != cola) {
                temp = temp.siguiente;
            }
            temp.siguiente = cabeza;
            cola = temp;
        }
        tamano--;
    }

    // 11. Eliminar por valor
    public void eliminar(T valor) {
        if (cabeza == null) return;

        if (cabeza.valor.equals(valor)) {
            eliminarPrimero();
            return;
        }

        Nodo<T> temp = cabeza;
        do {
            if (temp.siguiente.valor.equals(valor)) {
                if (temp.siguiente == cola) {
                    cola = temp;
                }
                temp.siguiente = temp.siguiente.siguiente;
                tamano--;
                return;
            }
            temp = temp.siguiente;
        } while (temp != cabeza);
    }

    // 12. Modificar nodo
    public void modificarNodo(int posicion, T nuevoValor) {
        Nodo<T> nodo = obtenerNodo(posicion);
        nodo.valor = nuevoValor;
    }

    // 13. Ordenar lista (burbuja)
    public void ordenarLista() {
        if (tamano <= 1) return;

        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            Nodo<T> siguiente;
            do {
                siguiente = actual.siguiente;
                if (actual.valor.compareTo(siguiente.valor) > 0) {
                    T temp = actual.valor;
                    actual.valor = siguiente.valor;
                    siguiente.valor = temp;
                    cambiado = true;
                }
                actual = actual.siguiente;
            } while (actual != cola);
        } while (cambiado);
    }

    // 14. Imprimir lista
    public void imprimirLista() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo<T> temp = cabeza;
        do {
            System.out.print(temp.valor + " -> ");
            temp = temp.siguiente;
        } while (temp != cabeza);
        System.out.println("(vuelve a la cabeza)");
    }

    // 15. Iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            private boolean primeraVez = true;

            @Override
            public boolean hasNext() {
                return actual != null && (primeraVez || actual != cabeza);
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.valor;
                actual = actual.siguiente;
                primeraVez = false;
                return valor;
            }
        };
    }

    // 16. Borrar lista
    public void borrarLista() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    // Getter de tamaño
    public int getTamano() {
        return tamano;
    }
}
