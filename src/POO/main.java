package POO;


import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner texto = new Scanner(System.in);

		Masmorra.inicialitzarDades();
		Masmorra.crearMasmorra();

		Personatge personatge = new Personatge("Pepe");
		boolean fin = false;
		while(!fin) {
		System.out.println("-------------------------------");
		Masmorra.mostrarMasmorra(personatge);
		System.out.println();
		Masmorra.mostrarOpciones(personatge);
		System.out.println();
		}
		
		
		
		
	}
}
