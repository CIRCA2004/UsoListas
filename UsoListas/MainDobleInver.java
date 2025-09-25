package UsoListas;

public class MainDobleInver {
    public static void main(String[] args) {
        ListaDobleinver<Integer> lista = new ListaDobleinver<>();

        lista.agregarFinal(1);
        lista.agregarFinal(2);
        lista.agregarFinal(3);
        lista.agregarFinal(4);
        lista.agregarFinal(5);

        System.out.println("Lista original:");
        lista.imprimirLista();

        lista.invertir();

        System.out.println("Lista invertida:");
        lista.imprimirLista();
    }
}
