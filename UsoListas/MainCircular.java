package UsoListas;

public class MainCircular {
    public static void main(String[] args) {
        ListaSimpleCircular<Integer> lista = new ListaSimpleCircular<>();

        System.out.println("¿La lista está vacía? " + lista.estaVacia());

        // Agregar al inicio y final
        lista.agregarInicio(30);
        lista.agregarFinal(50);
        lista.agregarInicio(10);
        lista.imprimirLista(); // 10 -> 30 -> 50 -> (vuelve a la cabeza)

        // Agregar en una posición
        lista.agregar(2, 40); 
        lista.imprimirLista(); // 10 -> 30 -> 40 -> 50 -> (vuelve a la cabeza)

        // Obtener valor y posición
        System.out.println("Valor en posición 2: " + lista.obtenerValorNodo(2)); // 40
        System.out.println("Posición del nodo con valor 50: " + lista.obtenerPosicionNodo(50)); // 3

        // Modificar nodo
        lista.modificarNodo(1, 35);
        lista.imprimirLista(); // 10 -> 35 -> 40 -> 50 -> (vuelve a la cabeza)

        // Eliminar primero y último
        lista.eliminarPrimero();
        lista.imprimirLista(); // 35 -> 40 -> 50 -> (vuelve a la cabeza)

        lista.eliminarUltimo();
        lista.imprimirLista(); // 35 -> 40 -> (vuelve a la cabeza)

        // Eliminar por valor
        lista.eliminar(35);
        lista.imprimirLista(); // 40 -> (vuelve a la cabeza)

        // Agregar más para probar ordenamiento
        lista.agregarFinal(5);
        lista.agregarFinal(20);
        lista.agregarFinal(15);
        lista.imprimirLista(); // 40 -> 5 -> 20 -> 15 -> (vuelve a la cabeza)

        // Ordenar
        lista.ordenarLista();
        lista.imprimirLista(); // 5 -> 15 -> 20 -> 40 -> (vuelve a la cabeza)

        // Usar el iterador
        System.out.print("Recorriendo con Iterator: ");
        for (Integer num : lista) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Tamaño
        System.out.println("Tamaño actual: " + lista.getTamano());

        // Borrar lista
        lista.borrarLista();
        lista.imprimirLista(); // Lista vacía
        System.out.println("¿La lista está vacía? " + lista.estaVacia());
    }
}
