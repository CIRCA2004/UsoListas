package UsoListas;

public class Main {
    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();

        lista.agregarFinal(10);
        lista.agregarFinal(20);
        lista.agregarFinal(30);

        lista.imprimirLista();  // 10 -> 20 -> 30 -> null

        System.out.println("Tamaño: " + lista.getTamano()); // 3
        System.out.println("¿Está vacía?: " + lista.estaVacia()); // false
    }
}
