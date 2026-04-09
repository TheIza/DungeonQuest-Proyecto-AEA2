package POO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tresor o = new Tresor ("culo", 1, 2.22);
		System.out.println(o);
	
		Monstre p = new Monstre ("Oscar Odena", 3, 1);
		System.out.println(p);
		int agilitat = 5;
		int forsa = 8;
		Personatge d = new Personatge ("Pepe", 3, 3, agilitat, forsa);
		Sala sw = new SalaTeranyina (o, p, false);
		
		System.out.println(sw.intentarSortir(agilitat));
		
		Masmorra(o, p);
	
	}


	public static void Masmorra (Tresor tresor, Monstre monstre) {

		
		for (int fila = 0; fila < 6; fila++) {
			for (int columna = 0; columna < 6; columna++) {
				int Salas = (int) (Math.random() * 10) +1;
				boolean SalaTeranyina = false;
				boolean SalaPont = false;
				boolean SalaComuna = false;
				
				if (Salas <= 2) {
					SalaTeranyina = true;
				} else if (Salas == 3 || Salas == 4){
					SalaPont = true;
				} else {
					SalaComuna = true;
				}
				
				if (SalaTeranyina) {
					System.out.print("1");
				} else if (SalaPont) {
					System.out.print("2");
				} else if (SalaComuna){
					System.out.print("3");
				}
				
				
			}
			System.out.println();
		}
	}
	
	
	
}
