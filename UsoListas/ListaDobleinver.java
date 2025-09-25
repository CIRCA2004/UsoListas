package UsoListas;

public class ListaDobleinver<T> {
    private NodoDobleinver<T> cabeza;
    private NodoDobleinver<T> cola;
    private int size;

    public ListaDobleinver() {
        this.cabeza = null;
        this.cola = null;
        this.size = 0;
    }

    // 1. Agregar al inicio
    public void agregarInicio(T dato) {
        NodoDobleinver<T> nuevo = new NodoDobleinver<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        size++;
    }

    // 2. Agregar al final
    public void agregarFinal(T dato) {
        NodoDobleinver<T> nuevo = new NodoDobleinver<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        size++;
    }

    // 3. Agregar en posición específica
    public void agregar(int index, T dato) {
        if (!indiceValido(index)) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        if (index == 0) {
            agregarInicio(dato);
        } else if (index == size) {
            agregarFinal(dato);
        } else {
            NodoDobleinver<T> actual = obtenerNodo(index);
            NodoDobleinver<T> nuevo = new NodoDobleinver<>(dato);
            nuevo.anterior = actual.anterior;
            nuevo.siguiente = actual;
            actual.anterior.siguiente = nuevo;
            actual.anterior = nuevo;
            size++;
        }
    }

    // 4. Obtener valor de un nodo
    public T obtenerValorNodo(int index) {
        return obtenerNodo(index).dato;
    }

    // 5. Obtener nodo
    private NodoDobleinver<T> obtenerNodo(int index) {
        if (!indiceValido(index)) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        NodoDobleinver<T> temp = cabeza;
        for (int i = 0; i < index; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    // 6. Obtener posición de un nodo
    public int obtenerPosicionNodo(T dato) {
        NodoDobleinver<T> temp = cabeza;
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

    // 7. Validar índice
    public boolean indiceValido(int index) {
        return index >= 0 && index <= size;
    }

    // 8. Verificar si está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // 9. Eliminar primero
    public void eliminarPrimero() {
        if (!estaVacia()) {
            if (cabeza == cola) {
                cabeza = cola = null;
            } else {
                cabeza = cabeza.siguiente;
                cabeza.anterior = null;
            }
            size--;
        }
    }

    // 10. Eliminar último
    public void eliminarUltimo() {
        if (!estaVacia()) {
            if (cabeza == cola) {
                cabeza = cola = null;
            } else {
                cola = cola.anterior;
                cola.siguiente = null;
            }
            size--;
        }
    }

    // 11. Eliminar por valor
    public void eliminar(T dato) {
        NodoDobleinver<T> temp = cabeza;
        while (temp != null) {
            if (temp.dato.equals(dato)) {
                if (temp == cabeza) {
                    eliminarPrimero();
                } else if (temp == cola) {
                    eliminarUltimo();
                } else {
                    temp.anterior.siguiente = temp.siguiente;
                    temp.siguiente.anterior = temp.anterior;
                    size--;
                }
                return;
            }
            temp = temp.siguiente;
        }
    }

    // 12. Modificar nodo
    public void modificarNodo(int index, T nuevoDato) {
        NodoDobleinver<T> nodo = obtenerNodo(index);
        nodo.dato = nuevoDato;
    }

    // 13. Ordenar lista (burbuja)
    public void ordenarLista() {
        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                NodoDobleinver<T> actual = cabeza;
                NodoDobleinver<T> siguiente = cabeza.siguiente;
                for (int j = 0; j < size - 1 - i; j++) {
                    Comparable<T> dato1 = (Comparable<T>) actual.dato;
                    if (dato1.compareTo(siguiente.dato) > 0) {
                        T aux = actual.dato;
                        actual.dato = siguiente.dato;
                        siguiente.dato = aux;
                    }
                    actual = siguiente;
                    siguiente = siguiente.siguiente;
                }
            }
        }
    }

    // 14. Imprimir lista
    public void imprimirLista() {
        NodoDobleinver<T> temp = cabeza;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }

    // 15. Iterador simple
    public void iterador() {
        NodoDobleinver<T> temp = cabeza;
        while (temp != null) {
            System.out.println(temp.dato);
            temp = temp.siguiente;
        }
    }

    // 16. Borrar lista
    public void borrarLista() {
        cabeza = cola = null;
        size = 0;
    }

    // 17. Invertir recursivamente
    public void invertir() {
        if (cabeza != null && cabeza.siguiente != null) {
            cabeza = invertirRecursivo(cabeza, null);
            // Reajustar la cola
            cola = cabeza;
            while (cola.siguiente != null) {
                cola = cola.siguiente;
            }
        }
    }

    private NodoDobleinver<T> invertirRecursivo(NodoDobleinver<T> actual, NodoDobleinver<T> anterior) {
        if (actual.siguiente == null) {
            actual.siguiente = anterior;
            if (anterior != null) {
                anterior.anterior = actual;
            }
            actual.anterior = null;
            return actual;
        }

        NodoDobleinver<T> siguiente = actual.siguiente;
        NodoDobleinver<T> nuevaCabeza = invertirRecursivo(siguiente, actual);

        actual.siguiente = anterior;
        if (anterior != null) {
            anterior.anterior = actual;
        }
        actual.anterior = siguiente;

        return nuevaCabeza;
    }
}
