package POO;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner texto = new Scanner (System.in);
		Masmorra.inicialitzarDades();
		Masmorra.crearMasmorra();
		System.out.println();
		System.out.println("   - - - DUNGEON QUEST - - - ");
		System.out.println();







		System.out.print("Posa el nom del teu personatge: ");
		String nombre = texto.next();
		System.out.println();
		Personatge personatge = new Personatge(nombre);
		boolean fin = false;

		while (!fin) {

			if (!personatge.estaViu()) {
				Masmorra.mostrarDerrota(personatge, nombre);
				fin = true;
			} else if (Masmorra.hasSortitDeLaMasmorra(personatge)) {
				Masmorra.mostrarVictoria(personatge, nombre);
				fin = true;

			} else {
				Masmorra.mostrarMasmorra(personatge);
				System.out.println();
				Masmorra.mostrarOpciones(personatge);
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("Creditos:");
		System.out.println("Hecho por \n \\n    - Marcos.J e Izarbe.L - ");




	}
}