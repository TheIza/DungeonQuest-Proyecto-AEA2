package POO;
import java.util.ArrayList;
import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		Scanner texto = new Scanner(System.in);
		// TODO Auto-generated method stub
		
		//UN VALOR QUE SE PODRA CAMBIAR A MEDIDA QUE VAYAMOS JUGANDO 
		boolean explorada = false;
		Tresor Tresor = new Tresor ("culo", 1, 2.22);
		Monstre Monstre = new Monstre (3, 1);

		Personatge Personatge = new Personatge ("Pepe");
		
		ArrayList<Sala> salas = new ArrayList<>();
		Sala SalaComuna = new SalaComuna (Tresor, Monstre,explorada);
		Sala SalaTeranyina = new SalaTeranyina (Tresor, Monstre, explorada);
		Sala SalaPont = new SalaPont (Tresor, Monstre,explorada);
		
		salas.add(SalaComuna);
		salas.add(SalaTeranyina);
		salas.add(SalaPont);
		
		Masmorra(Personatge, Tresor, Monstre, salas);

		System.out.println("escoge algo");
		int menu = texto.nextInt();
		switch (menu) {
			case 0:
			System.out.println("Explorar");
			case 1:
			System.out.println("Moure");
			case 2: 
			System.out.println("Atacar");

		}
		
	}
	/** 
	 * ni idea de que hago fr
	 * @param salas
	 * @param numeroSala
	 * @return
	 */
	
	// yayaya muy bonito, pero como hago ahora que los valores se vayan cambiando 
	// ni idea, mas tarde lo veo 
	public static Sala tipoMasmorra(ArrayList<Sala> salas, int numeroSala) {
	    for (Sala sala : salas) {
	        if (numeroSala <= 2 && sala instanceof SalaComuna) {
	            return (SalaComuna) sala;
	        } else if ((numeroSala == 3 || numeroSala == 4) && sala instanceof SalaPont) {
	            return (SalaPont) sala;
	        } else if (numeroSala > 4 && numeroSala <= 10 && sala instanceof SalaTeranyina) {
	            return (SalaTeranyina) sala;
	        }
	    }
	    return null;
	}
	
	
	public static void Masmorra (Personatge personatge,Tresor tresor, Monstre monstre, ArrayList<Sala> salass) {
		int posicionFilaJugador = personatge.getPosicio(0);
		int posicionColumnaJugador = personatge.getPosicio(1);
		int[] arrayPersonatge = {posicionFilaJugador, posicionColumnaJugador };
		
		boolean d = false;
		/**
		 * for para poder imprimir todo lo de antes 
		 */
		for (int fila = 0; fila < 6; fila++) {
			d = arrayPersonatge[0] == fila;
			for (int columna = 0; columna < 6; columna++) {
				int Salas = (int) (Math.random() * 10) +1;

				boolean SalaTeranyina = false;
				boolean SalaPont = false;
				boolean SalaComuna = false;
				
				if (Salas <= 2) {
					SalaTeranyina = true;
					tipoMasmorra(salass, Salas);
				} else if (Salas == 3 || Salas == 4){
					SalaPont = true;
					tipoMasmorra(salass, Salas);
				} else if (Salas > 4 && Salas <= 10){
					SalaComuna = true;
					tipoMasmorra(salass, Salas);
				}
				
				if (d && arrayPersonatge[1] == columna) {
					System.out.print("&");
				} else if (SalaTeranyina) {
					System.out.print("1");
				} else if (SalaPont) {
					System.out.print("2");
				} else if (SalaComuna){
					System.out.print("3");
				} else {
					System.out.print("-");
				}
				
				
			}
			System.out.println();
			

		}
		System.out.println("1. **Explorar** (si la sala encara no ha sigut explorada).\r\n"
				+ "2. **Moure** (moure cap a una sala adjacent a la que està actualment el personatge).\r\n"
				+ "3. **Atacar** (si en la sala hi ha un monstre)");
	}

	
}
