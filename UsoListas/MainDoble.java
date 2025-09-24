package UsoListas;
public class MainDoble {
    public static void main(String[] args) {
        ListaDoble<Integer> lista = new ListaDoble<>();

        lista.agregarInicio(30);
        lista.agregarInicio(10);
        lista.agregarFinal(50);
        lista.agregar(1, 20); // Insertar en posición específica

        System.out.println("Lista inicial:");
        lista.imprimirLista(); // 10 20 30 50

        System.out.println("Obtener valor en posición 2: " + lista.obtenerValorNodo(2)); // 30
        System.out.println("Posición del valor 50: " + lista.obtenerPosicionNodo(50)); // 3

        lista.modificarNodo(0, 5);
        System.out.println("Lista después de modificar:");
        lista.imprimirLista(); // 5 20 30 50

        lista.eliminar(30);
        System.out.println("Lista después de eliminar 30:");
        lista.imprimirLista(); // 5 20 50

        lista.ordenarLista();
        System.out.println("Lista ordenada:");
        lista.imprimirLista(); // 5 20 50

        System.out.println("Recorriendo con Iterator:");
        for (Integer valor : lista) {
            System.out.print(valor + " ");
        }

        lista.borrarLista();
        System.out.println("\nLista después de borrar: ");
        lista.imprimirLista(); // vacía
    }
}