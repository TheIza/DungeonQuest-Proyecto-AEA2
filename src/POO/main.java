package POO;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner texto = new Scanner(System.in);


		Masmorra.inicialitzarDades();
		Masmorra.crearMasmorra();

		Personatge personatge = new Personatge("Pepe");

		Masmorra.mostrarMasmorra(personatge);
		System.out.println();
		Masmorra.mostrarMasmorraSinOcultar(personatge);


		System.out.println("\nEscoge algo:");
		System.out.println("0. Explorar");
		System.out.println("1. Moure");
		System.out.println("2. Atacar");

		int menu = texto.nextInt();

		switch (menu) {
		case 0:
			System.out.println("Explorar");
			Sala salaActual = Masmorra.obtenirSalaActual(personatge);
			personatge.explorar(salaActual);
			
			break;
		case 1:
			System.out.println("Moure");
			break;
		case 2:
			System.out.println("Atacar");
			break;
		}

		
	}
}