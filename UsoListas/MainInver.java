package UsoListas;

public class MainInver {
    public static void main(String[] args) {
        ListaInver<Integer> lista = new ListaInver<>();

        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);

        System.out.println("Lista original:");
        lista.imprimir(); // 1 2 3 4 5

        lista.invertirContenido();

        System.out.println("Lista invertida:");
        lista.imprimir(); // 5 4 3 2 1
    }
}
