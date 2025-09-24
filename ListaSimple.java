package UsoListas;

public class ListaSimple<T extends Comparable<T>> {
    private Nodo<T> cabeza;
    private int Tamano;

    public ListaSimple() {
        cabeza = null;
        Tamano = 0;
    }


    // 1. Agregar al inicio
    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        Tamano++;
    }

    // 2. Agregar al final
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
        Tamano++;
    }

    // 3. Agregar en posición específica
    public void agregar(T valor, int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Posición inválida.");
        }
        if (posicion == 0) {
            agregarInicio(valor);
        } else {
            Nodo<T> nuevo = new Nodo<>(valor);
            Nodo<T> temp = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                temp = temp.siguiente;
            }
            nuevo.siguiente = temp.siguiente;
            temp.siguiente = nuevo;
            Tamano++;
        }
    }

    // 4. Obtener valor de nodo
    public T obtenerValorNodo(int posicion) {
        Nodo<T> nodo = obtenerNodo(posicion);
        return (nodo != null) ? nodo.valor : null;
    }

    // 5. Obtener nodo
    public Nodo<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) return null;
        Nodo<T> temp = cabeza;
        for (int i = 0; i < posicion; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    // 6. Obtener posición por valor
    public int obtenerPosicionNodo(T valor) {
        Nodo<T> temp = cabeza;
        int index = 0;
        while (temp != null) {
            if (temp.valor.equals(valor)) return index;
            temp = temp.siguiente;
            index++;
        }
        return -1;
    }

    // 7. Validar índice
    public boolean indiceValido(int posicion) {
        return (posicion >= 0 && posicion < Tamano);
    }

    // 8. Verificar si está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // 9. Eliminar primero
    public void eliminarPrimero() {
        if (!estaVacia()) {
            cabeza = cabeza.siguiente;
            Tamano--;
        }
    }

    // 10. Eliminar último
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (cabeza.siguiente == null) {
            cabeza = null;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.siguiente.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = null;
        }
        Tamano--;
    }

    // 11. Eliminar por valor
    public void eliminar(T valor) {
        if (estaVacia()) return;
        if (cabeza.valor.equals(valor)) {
            eliminarPrimero();
            return;
        }
        Nodo<T> temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.valor.equals(valor)) {
            temp = temp.siguiente;
        }
        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
            Tamano--;
        }
    }

    // 12. Modificar nodo
    public void modificarNodo(int posicion, T nuevoValor) {
        Nodo<T> nodo = obtenerNodo(posicion);
        if (nodo != null) nodo.valor = nuevoValor;
    }

    // 13. Ordenar lista (burbuja)
    public void ordenarLista() {
        if (estaVacia()) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.valor.compareTo(actual.siguiente.valor) > 0) {
                    T temp = actual.valor;
                    actual.valor = actual.siguiente.valor;
                    actual.siguiente.valor = temp;
                    cambiado = true;
                }
                actual = actual.siguiente;
            }
        } while (cambiado);
    }

    // 14. Imprimir lista
    public void imprimirLista() {
        Nodo<T> temp = cabeza;
        while (temp != null) {
            System.out.print(temp.valor + " -> ");
            temp = temp.siguiente;
        }
        System.out.println("null");
    }

    // 15. Iterador simple
    public void iterator() {
        Nodo<T> temp = cabeza;
        while (temp != null) {
            System.out.println(temp.valor);
            temp = temp.siguiente;
        }
    }

    // 16. Borrar lista
    public void borrarLista() {
        cabeza = null;
        Tamano = 0;
    }
    public int getTamano() {
    return Tamano; 
    }
}

