	package POO;

	import java.util.Random;
import java.util.Scanner;

	public class Masmorra {

		public static final int FILES = 6;
		public static final int COLUMNES = 6;

		private static final Random random = new Random();


		public static Tresor[] tresors;
		public static Monstre[] monstres;
		public static Sala[][] sales;


		public Masmorra() {
		}

		public static void inicialitzarDades() {
			tresors = new Tresor[] {
					new Tresor("Espasa d'or", 150, 4.5),
					new Tresor("Corona reial", 300, 2.0),
					new Tresor("Anell màgic", 200, 0.2),
					new Tresor("Escut antic", 120, 6.0),
					new Tresor("Collaret de rubins", 250, 1.0)
			};

			monstres = new Monstre[] {
					new Monstre(4, 1),
					new Monstre(6, 1),
					new Monstre(8, 2),
					new Monstre(10, 3),
					new Monstre(12, 3)
			};
		}

		public static void crearMasmorra() {
			sales = new Sala[FILES][COLUMNES];

			for (int i = 0; i < FILES; i++) {
				for (int j = 0; j < COLUMNES; j++) {
					sales[i][j] = generarSalaAleatoria();
				}
			}

			sales[0][0].setExplorada(true);
		}

		private static Sala generarSalaAleatoria() {
			int numero = random.nextInt(100) + 1;

			Tresor tresor = generarTresorAleatori();
			Monstre monstre = generarMonstreAleatori();

			if (numero <= 60) {
				return new SalaComuna(tresor, monstre);
			} else if (numero <= 80) {
				return new SalaPont(tresor, monstre);
			} else {
				return new SalaTeranyina(tresor, monstre);
			}
		}
		public static void mostrarMasmorra(Personatge personatge) { 
			for (int i = 0; i < FILES; i++) { 
				for (int j = 0; j < COLUMNES; j++) { 
					if (personatge.getPosicio(0) == i && personatge.getPosicio(1) == j) { 
						System.out.print("& "); 
					} else if (sales[i][j].isExplorada()) { 
						System.out.print("* "); 
					} else { 
						System.out.print("- "); } } System.out.println();
			} }
		private static Tresor generarTresorAleatori() {
			if (random.nextInt(100) < 30) {
				return tresors[random.nextInt(tresors.length)];
			}
			return null;
		}
		
		public static void mostrarMasmorraSinOcultar(Personatge personatge) {
				
				for (int i = 0; i < FILES; i++) {
					for (int j = 0; j < COLUMNES; j++) {
						if (personatge.getPosicio(0) == i && personatge.getPosicio(1) == j) {
							System.out.print("& ");
						} else if (sales[i][j] instanceof SalaComuna) {
							System.out.print("C ");
						} else if (sales[i][j] instanceof SalaPont) {
							System.out.print("P ");
						} else if (sales[i][j] instanceof SalaTeranyina) {
							System.out.print("T ");
						}
					}
					System.out.println();
				}
			}

		private static Monstre generarMonstreAleatori() {
			if (random.nextInt(100) < 35) {
				return monstres[random.nextInt(monstres.length)];
			}
			
			return null;
		}
		public static Sala obtenirSalaActual(Personatge personatge) {
			return sales[personatge.getPosicio(0)][personatge.getPosicio(1)];
		}

		public static void mostrarOpciones(Personatge personatge){
			
			System.out.println("0.Explorar");
			System.out.println("1.Moure");
			System.out.println("2.Atacar");
			System.out.println();
			System.out.println("Opcio:");
			Scanner teclado = new Scanner(System.in);
			int menu = teclado.nextInt();
			System.out.println();
			switch (menu) {
			case 0:
				System.out.println("-Explorar-");
				Sala salaActual = Masmorra.obtenirSalaActual(personatge);
				personatge.explorar(salaActual);

				break;
			case 1:
				System.out.println("-Moure-");
				System.out.println("N-arriba | S-abajo | E-derecha | O-izquierda");
				char moviment = teclado.next().charAt(0);
				personatge.moure(moviment);
				break;
			case 2:
				System.out.println("-Atacar-");
				break;
			}

		}

	}