package POO;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner texto = new Scanner (System.in);
		Masmorra.inicialitzarDades();
		Monstre finalBoss = new Monstre(20, 3);
		Masmorra.crearMasmorra(finalBoss);
		
		System.out.println();
		System.out.println("   - - - DUNGEON QUEST - - - ");
		System.out.println();

		System.out.print("Pon el nombre de tu personaje: ");
		String nombre = texto.next();
		System.out.println();
		Personatge personatge = new Personatge(nombre);
		boolean fin = false;

		while (!fin) {

			if (!personatge.estaViu()) {
				Masmorra.mostrarDerrota(personatge, nombre);
				fin = true;
			} else if (Masmorra.hasSortitDeLaMasmorra(personatge, finalBoss)) {
				Masmorra.mostrarVictoria(personatge, nombre);
				fin = true;

			} else {
				
				Masmorra.mostrarMasmorra(personatge);
				System.out.println();
				
				Masmorra.mostrarOpciones(personatge, finalBoss);
				System.out.println();
				
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("          - - - Creditos - - -" + "\n");
		System.out.println("       - Marcos.J e Izarbe.L - ");

	}
	
	

}