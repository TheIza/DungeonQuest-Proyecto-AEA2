package POO;

import java.util.Random;
import java.util.Scanner;

public class Masmorra {

	public static final int FILES = 6;
	public static final int COLUMNES = 6;

	private static final Random random = new Random();
	final static Scanner teclado = new Scanner(System.in);
	public static Tresor[] tresors;
	public static Monstre[] monstres;
	public static Sala[][] sales;

	public Masmorra() {}

	public static void inicialitzarDades() {
		tresors = new Tresor[] {
				new Tresor("Espada de oro", 150, 4.5),
				new Tresor("Corona real", 300, 2.0),
				new Tresor("Anillo mágico", 200, 0.2),
				new Tresor("Escudo antiguo", 120, 6.0),
				new Tresor("Collar de rubíes", 250, 1.0)
		};

		monstres = new Monstre[] {
				new Monstre(4, 1),
				new Monstre(6, 1),
				new Monstre(8, 2),
				new Monstre(10, 3),
				new Monstre(12, 3)
		};
	}

	public static void crearMasmorra(Monstre finalBoss) {
		sales = new Sala[FILES][COLUMNES];


		for (int i = 0; i < FILES; i++) {
			for (int j = 0; j < COLUMNES; j++) {
				sales[i][j] = generarSalaAleatoria();
			}
		}

		// la primera sala del juego sera 100/100 comuna
		Tresor tresor = generarTresorAleatori();
		Monstre monstre = generarMonstreAleatori();
		sales[0][0] = new SalaComuna(tresor, monstre);
		sales[0][0].setExplorada(true);

		/**
		 * Añadimos un boss final en el medio del tablero, este puede salir en cualquiera de las 4 casillas centrales
		 */


		finalBoss.setNom("ChikiIbai");
		int valorRandom = valorRandom(0,4);

		if(valorRandom == 1) {
			sales[2][2] = new SalaComuna(null, finalBoss);
			/*
			 * x -
			 * - -
			 */
		} else if (valorRandom == 2) {
			sales[2][3] = new SalaComuna(null, finalBoss);
			/*
			 * - x
			 * - -
			 */
		} else if (valorRandom == 3) {
			sales[3][2] = new SalaComuna(null, finalBoss);
			/*
			 * - -
			 * x -
			 */
		} else {
			sales[3][3] = new SalaComuna(null, finalBoss);
			/*
			 * - -
			 * - x
			 */
		}
	}

	private static Sala generarSalaAleatoria() {
		int numero = random.nextInt(100) + 1;
		Tresor tresor = generarTresorAleatori();
		Monstre monstre = generarMonstreAleatori();

		if (numero <= 50) {
			return new SalaComuna(tresor, monstre);
		} else if (numero <= 70) {
			return new SalaPont(tresor, monstre);
		} else if (numero <= 85) {
			return new SalaTeranyina(tresor, monstre);
		} else {
			return new SalaTrampa(tresor, monstre);
			// este en nuestro caso solo tiene un 15% de probabilidad de spawn
		}
	}

	private static Tresor generarTresorAleatori() {
		int rand = random.nextInt(100);
		if (rand < 20) {
			return new TresorMagic("Poción mágica", 100, 0.5, valorRandom(2, 5));
		} else if (rand < 35) {
			return new TresorMaleit("Anillo maldito", 50, 0.3, valorRandom(1, 3));
		} else if (rand < 60) {
			return tresors[random.nextInt(tresors.length)];
		}
		return null;
	}

	private static int valorRandom(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

	private static Monstre generarMonstreAleatori() {
		if (random.nextInt(100) < 35) {
			return monstres[random.nextInt(monstres.length)];
		}
		return null;
	}

	public static void mostrarMasmorra(Personatge personatge) {
		for (int i = 0; i < FILES; i++) {
			for (int j = 0; j < COLUMNES; j++) {
				if (personatge.getPosicio(0) == i && personatge.getPosicio(1) == j) {
					System.out.print("& ");
				} else if (sales[i][j].isExplorada()) {
					System.out.print("* ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
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
				}  else if (sales[i][j] instanceof SalaTrampa) {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}

	public static Sala obtenirSalaActual(Personatge personatge) {
		return sales[personatge.getPosicio(0)][personatge.getPosicio(1)];
	}

	public static void mostrarOpciones(Personatge personatge, Monstre finalBoss) {

		Sala salaActual = Masmorra.obtenirSalaActual(personatge);
		boolean entroInfo = false;

		if(!salaActual.isExplorada()) {
			System.out.println("0. Explorar");
		} 
		System.out.println("1. Mover");

		if(salaActual.getMonstre() != null && salaActual.getMonstre().estaViu()) {
			System.out.println("2. Atacar");
		}
		System.out.println("3. Información");
		System.out.println();
		System.out.print("Opción: ");


		String menu_str = teclado.next();
		int menu_str1 = (int) menu_str.charAt(0);
		int menu = 5;
		if (47 < menu_str1 && menu_str1 < 58) {
			menu = (int) menu_str1-48;
		} 

		System.out.println();

		Monstre monstreSalaActual = salaActual.getMonstre();

		switch (menu) {

		case 0:
			if(salaActual.isExplorada()) {
				System.out.println("Esta sala ya ha sido explorada");
				break;
			}
			System.out.println("          -Explorar-");
			personatge.explorar(salaActual);
			break;

		case 1:
			boolean puedeMover = false;
			System.out.println("          -Mover-");
			if (Masmorra.hasSortitDeLaMasmorra(personatge, finalBoss)) {
				break;
			}
			if (salaActual.getMonstre() != null && salaActual.getMonstre().estaViu()) {
				System.out.println("Hay un monstruo en la sala... intentarás huir de él.");

				if (salaActual instanceof SalaTeranyina) {
					if (salaActual.intentarSortir(personatge.getForsa())) {
						System.out.println("Has escapado! A donde quieres ir?");
						puedeMover = true;
					} else {
						System.out.println("No has podido escapar del monstruo...");
					}
				}

				if (salaActual instanceof SalaPont) {
					if (salaActual.intentarSortir(personatge.getAgilitat())) {
						System.out.println("Has escapado del puente y del monstruo! A donde quieres ir?");
						puedeMover = true;
					} else {
						personatge.rebreDany(1);
						personatge.setCausaMort("Caída del puente");
						System.out.println("Has caído del puente! Vida actual: " + personatge.getVida());

						personatge.rebreDany(monstreSalaActual.getPenalització());
						personatge.setCausaMort("Caída del puente y ataque de " + monstreSalaActual.getNom());
						System.out.println("El monstruo te ha atacado por intentar huir. Vida actual: " + personatge.getVida());
					}
				}

				if (salaActual instanceof SalaComuna) {
					System.out.println("Al ser una sala común has escapado, pero te han hecho daño en el camino...");
					puedeMover = true;
					personatge.rebreDany(monstreSalaActual.getPenalització());
					personatge.setCausaMort("Huida y ataque de " + monstreSalaActual.getNom());
					personatge.setVida(personatge.getVida() - monstreSalaActual.getPenalització());
					System.out.println("Vida actual: " + personatge.getVida());
				}

				if(salaActual instanceof SalaTrampa) {
					System.out.println("Entraste a una sala TRAMPA y con monstruo...");
					System.out.println("Escaparas con dificultad de las trampas y el monstruo, pero no saldras ileso.");
					personatge.setVida((personatge.getVida() - monstreSalaActual.getPenalització()) - SalaTrampa.getDanyTrampa());
					System.out.println("Vida actual: " + personatge.getVida());
				}

			} else if(salaActual instanceof SalaTrampa) {
				System.out.println("Entraste a una sala TRAMPA...");
				System.out.println("Te mueves agilmente entre las trampas pero te clavas una felcha en el pie...");
				personatge.setVida(personatge.getVida() - SalaTrampa.getDanyTrampa());
				System.out.println("Vida actual: " + personatge.getVida());
				puedeMover = true;
			} else {
				puedeMover = true;
			}

			if (puedeMover) {
				System.out.println("N-arriba | S-abajo | E-derecha | O-izquierda");
				char moviment = teclado.next().charAt(0);
				personatge.moure(moviment);
				System.out.println();
				limpiarPantalla();
				System.out.println("Te has movido a una nueva sala.");

				if (Masmorra.hasSortitDeLaMasmorra(personatge, finalBoss)) {
					break;
				}

				salaActual = Masmorra.obtenirSalaActual(personatge);
				monstreSalaActual = salaActual.getMonstre();
			}


			if (salaActual.getMonstre() != null && monstreSalaActual.estaViu()) {
				System.err.println("! ! EN LA SALA ACTUAL HAY UN MONSTRUO ! !");
				System.out.println(monstreSalaActual);
			} else {
				System.out.println("No hay ningún monstruo en la sala a la que te has movido");
			}
			break;

		case 2:
			System.out.println("          -Atacar-");
			if(salaActual.getMonstre() != null && monstreSalaActual.estaViu()) {
				personatge.atacar(monstreSalaActual);

				if(!monstreSalaActual.estaViu()) {
					personatge.setExperencia(personatge.getExperencia() + monstreSalaActual.getValorExperiencia());
					System.out.println("Monstruo muerto! Experiencia ganada: " + monstreSalaActual.getValorExperiencia());
					// AQUI VA EL METODO DE SUBIR DE NIVEL
					personatge.subirNivell(monstreSalaActual);
				} else {
					System.out.println();
					System.out.println("- EL MONSTRUO CONTRAATACA -");
					System.out.println();
					monstreSalaActual.atacar(personatge);

					if(!personatge.estaViu()) {
						System.out.println("Has muerto... ");
						personatge.setCausaMort("Ataque de " + monstreSalaActual.getNom());
					}
				}
			} else {
				System.out.println("No hay ningún monstruo en esta sala.");
			}
			break;

		case 3:
			System.out.println("          -Información-");

			System.out.println();
			System.out.println("=-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-=");
			System.out.println("| Nombre: " + personatge.getNom() + "\n" +
					"| Vida: " + personatge.getVida() + "\n" +
					"| Agilidad: " + personatge.getAgilitat() + "\n" + 
					"| Fuerza/Ataque: " + personatge.getForsa() + "\n" + 
					"| Nivel: " + personatge.getNivell());
			System.out.println("=-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-=");
			System.out.println();
			entroInfo =  true;
			break;
		default:
			System.out.println("OPCIÓN INVÁLIDA");
		}
		
		
		// esto es meramente visual
		if (!entroInfo) {
			System.out.println("- - - - - - - - - - - - - - - - - ");
		}
	}

	public static void mostrarVictoria(Personatge personatge, String nombre) {
		System.out.println("--------------------------------");
		System.out.println("HAS ESCAPADO DE LA MAZMORRA " + nombre + "!!!!");
		System.out.println("--------------------------------");
		System.out.println("Experiencia:       " + personatge.getExperencia());
		System.out.println("Tesoros recogidos: " + personatge.getNumTresors());
		System.out.println("Monedas de oro:      " + personatge.getTotalOr());
		System.out.println("Vida restante:      " + personatge.getVida());
		System.out.println("Masmorra explorada: " + calcularPercentatgeExplorat() + "%");
	}

	public static void mostrarDerrota(Personatge personatge, String nombre) {
		System.out.println("--------------------------------");
		System.out.println("HAS MUERTO " + nombre + "...");
		System.out.println("--------------------------------");
		System.out.println("Experiencia conseguida: " + personatge.getExperencia());
		System.out.println("Causa de la muerte:        " + personatge.getCausaMort());
		System.out.println("Masmorra explorada: " + calcularPercentatgeExplorat() + "%");
	}

	public static boolean hasSortitDeLaMasmorra(Personatge personatge, Monstre chikiIbai) {
		int fila = personatge.getPosicio(0);
		int col  = personatge.getPosicio(1);

		if (((fila <= 0 && col >= 5) || (fila >= 5 && col >= 5) || (fila >= 5 && col <= 0))) {
			if (chikiIbai.getVida() < 1) {
				return true;	
			} else {
				System.out.println("Intentas abrir la puerta, pero te fijas que esta cerrada con llave. \n"
						+ "Debes matar al GRAN JEFE final para poder obtener la llave y escapar de la mazmorra");
				return false;
			}

		}
		return false;
	}

	public static void limpiarPantalla() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}
	
	public static int calcularPercentatgeExplorat() {
		int explorades = 0;
		for (int i = 0; i < FILES; i++) {
			for (int j = 0; j < COLUMNES; j++) {
				if (sales[i][j].isExplorada()) {
					explorades++;
				}
			}
		}
		return (explorades * 100) / (FILES * COLUMNES);
	}

	
}