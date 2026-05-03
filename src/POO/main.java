package POO;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
    	Scanner texto = new Scanner (System.in);
        Masmorra.inicialitzarDades();
        Masmorra.crearMasmorra();

        System.out.print("Pon el nombre de tu personaje: ");
        String nombre = texto.next();
        Personatge personatge = new Personatge(nombre);
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