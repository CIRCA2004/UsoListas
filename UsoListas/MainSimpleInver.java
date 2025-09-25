package UsoListas;

public class MainSimpleInver {
    public static void main(String[] args) {
        ListaSimpleInver<Integer> lista = new ListaSimpleInver<>();

        lista.agregarFinal(3);
        lista.agregarInicio(1);
        lista.agregar(1, 2);  // en posición 1

        System.out.println("Lista inicial:");
        lista.imprimirLista();  // 1 2 3

        System.out.println("Valor en posición 2: " + lista.obtenerValorNodo(2));
        System.out.println("Posición del valor 2: " + lista.obtenerPosicionNodo(2));

        lista.modificarNodo(1, 5);
        System.out.println("Lista modificada:");
        lista.imprimirLista();  // 1 5 3

        lista.ordenarLista();
        System.out.println("Lista ordenada:");
        lista.imprimirLista();  // 1 3 5

        lista.eliminarUltimo();
        System.out.println("Después de eliminar último:");
        lista.imprimirLista();  // 1 3

        lista.invertir();
        System.out.println("Lista invertida:");
        lista.imprimirLista();  // 3 1

        System.out.println("Recorrido con Iterator:");
        for (Integer val : lista) {
            System.out.print(val + " ");
        }
        System.out.println();

        lista.borrarLista();
        System.out.println("Lista vacía? " + lista.estaVacia());
    }
}
