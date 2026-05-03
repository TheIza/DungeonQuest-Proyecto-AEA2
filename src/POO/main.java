package POO;

public class main {

    public static void main(String[] args) {

        Masmorra.inicialitzarDades();
        Masmorra.crearMasmorra();

        Personatge personatge = new Personatge("Pepe");
        boolean fin = false;

        while (!fin) {

            if (!personatge.estaViu()) {
                Masmorra.mostrarDerrota(personatge);
                fin = true;
            } else if (Masmorra.hasSortitDeLaMasmorra(personatge)) {
                Masmorra.mostrarVictoria(personatge);
                fin = true;
            } else {
                Masmorra.mostrarMasmorra(personatge);
                System.out.println();
                Masmorra.mostrarOpciones(personatge);
                System.out.println();
            }
        }
        
        
        
    }
}