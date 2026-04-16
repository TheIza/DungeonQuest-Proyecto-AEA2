package POO;
import java.util.ArrayList;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Tresor o = new Tresor ("culo", 1, 2.22);
		Monstre p = new Monstre ("Oscar Odena", 3, 1);

		Personatge d = new Personatge ("Pepe");
		Sala sw = new SalaTeranyina (o, p, false);
		
		
		Masmorra(d, o, p);
	
	}


	public static void Masmorra (Personatge personatge,Tresor tresor, Monstre monstre) {

		int[] arrayPersonatge = personatge.getPosicio();
		boolean d = false;
		
		for (int fila = 0; fila < 6; fila++) {
			d = arrayPersonatge[0] == fila;
			for (int columna = 0; columna < 6; columna++) {
				int Salas = (int) (Math.random() * 20) +1;

				boolean SalaTeranyina = false;
				boolean SalaPont = false;
				boolean SalaComuna = false;
				
				if (Salas <= 2) {
					SalaTeranyina = true;
				} else if (Salas == 3 || Salas == 4){
					SalaPont = true;
				} else if (Salas > 4 && Salas <= 10){
					SalaComuna = true;
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
	}
	
	
	
}
