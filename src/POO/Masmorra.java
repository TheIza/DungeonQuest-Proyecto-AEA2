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

	// iniciamos tesoros y monstruos
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

	//creamos la mazmorra con sus cofres, monstruos, salas y jefe final.
	public static void crearMasmorra(Monstre finalBoss) {
		sales = new Sala[FILES][COLUMNES];

		for (int i = 0; i < FILES; i++) {
			for (int j = 0; j < COLUMNES; j++) {
				sales[i][j] = generarSalaAleatoria();
			}
		}

		// la primera sala del juego sera 100/100 comuna, sin monstruo ni tesoro
		
		sales[0][0] = new SalaComuna(null, null);
		sales[0][0].setExplorada(true);

		/**
		 * Añadimos un boss final en el medio del tablero
		 * 
		 * Si el tablero es par:
		 * el boss puede salir en cualquiera de las 4 casillas centrales.
		 * 
		 * Si el tablero es impar:
		 * el boss puede aparecer alrededor del centro:
		 * arriba, abajo, izquierda o derecha.
		 */
		finalBoss.setNom("ChikiIbai");
		int valorRandom = valorRandom(0,4);

		// las filas y columnas deben ser iguales
		int fila = FILES;
		int mitad = fila / 2;


		if(fila % 2 == 0) { //es par
			if(valorRandom == 1) {
				sales[mitad-1][mitad-1] = new SalaComuna(null, finalBoss);
				/*
				 * x -
				 * - -
				 */
			} else if (valorRandom == 2) {
				sales[mitad-1][mitad] = new SalaComuna(null, finalBoss);
				/*
				 * - x
				 * - -
				 */
			} else if (valorRandom == 3) {
				sales[mitad][mitad-1] = new SalaComuna(null, finalBoss);
				/*
				 * - -
				 * x -
				 */
			} else {
				sales[mitad][mitad] = new SalaComuna(null, finalBoss);
				/*
				 * - -
				 * - x
				 */
			}
		} else if (valorRandom == 1) { // es impar
			sales[mitad - 1][mitad] = new SalaComuna(null, finalBoss);

			/*  - x -
			 * 	- - -
			 *  - - -
			 */
		} else if (valorRandom == 2) {
			sales[mitad][mitad + 1] = new SalaComuna(null, finalBoss);
			/*  - - -
			 * 	- - x
			 *  - - -
			 */
		} else if (valorRandom == 3) {
			sales[mitad + 1][mitad] = new SalaComuna(null, finalBoss);

			/*  - - -
			 * 	- - -
			 *  - x -
			 */

		} else if (valorRandom == 4) {
			sales[mitad][mitad - 1] = new SalaComuna(null, finalBoss);

			/*  - - -
			 * 	x - -
			 *  - - -
			 */

		}

	}


	// generador de salas aleatorias para el metodo crearMasmorra
	private static Sala generarSalaAleatoria() {

		// numero random para decidir el tipo de sala
		int numero = random.nextInt(100) + 1;
		Tresor tresor = generarTresorAleatori();
		Monstre monstre = generarMonstreAleatori();
		if (numero <= 50) { // 50% de probabilidad
			return new SalaComuna(tresor, monstre);
		} else if (numero <= 70) { // 20% de probabilidad
			return new SalaPont(tresor, monstre);
		} else if (numero <= 85) { // 15% de probabilidad
			return new SalaTeranyina(tresor, monstre);
		} else {
			// este ultimo caso tiene un 15% de probabilidad de spawn
			return new SalaTrampa(tresor, monstre);
		}
	}
	
	// que generemos tesoro o monstruo no significa que en esa sala vaya a haber 100/100 alguno de esos 2, porque el "filtro" final es en el constructor de Sala. 
	
	// generador de tesoros aleatorios 
	private static Tresor generarTresorAleatori() {
		int rand = random.nextInt(100);
		if (rand < 15) { // 20% de probabilidad
			return new TresorMagic("Poción mágica", 100, 0.5, valorRandom(2, 5));
		} else if (rand < 35) { // 15% de probabilidad
			return new TresorMaleit("Anillo maldito", 50, 0.3, valorRandom(1, 3));
		} else { // 65% de probabilidad tesoro generico
			return tresors[random.nextInt(tresors.length)];
		}
	}

	private static Monstre generarMonstreAleatori() {
		if (random.nextInt(100) < 35) {
			return monstres[random.nextInt(monstres.length)];
		}
		return null;
	}

	private static int valorRandom(int min, int max) {
		return random.nextInt(max - min + 1) + min;
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

	// metodo para obtener la sala actual
	public static Sala obtenirSalaActual(Personatge personatge) {
		return sales[personatge.getPosicio(0)][personatge.getPosicio(1)];
	}

	// mostramos las opciones principales
	public static void mostrarOpciones(Personatge personatge, Monstre finalBoss) {

		Sala salaActual = Masmorra.obtenirSalaActual(personatge);
		boolean entroInfo = false;

		// mostramos opciones dependiendo del estado de la sala
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


		// recogemos la opcion por string
		String menu_str = teclado.next();
		// la pasamos a numero gracias al codigo ascii
		int menu_str1 = (int) menu_str.charAt(0);
		// valor predeterminado de menu (saltaria el DEFAULT del switch)
		int menu = 5;
		// miramos si esta entre 47 y 58 , que son los numeros 0-9 pero en ascii
		if (menu_str1 > 47 && menu_str1 < 58) {
			// volvemos a traducir si es un numero
			menu = menu_str1-48;
		} 

		System.out.println();

		//buscamos que monstruo hay en la sala actual, si no hay sale null
		Monstre monstreSalaActual = salaActual.getMonstre();

		switch (menu) {

		case 0:
			// exploramos la sala si no esta explorada
			if(salaActual.isExplorada()) {
				System.out.println("Esta sala ya ha sido explorada");

			} else {
				System.out.println("          -Explorar-");
				personatge.explorar(salaActual);
			}
			break;

		case 1:
			// movimiento
			boolean puedeMover = false;
			System.out.println("          -Mover-");
			// si el personaje sigue estando en la mazmorra Y hay un monstruo vivo en la sala intentamos escapar
			if (!Masmorra.hasSortitDeLaMasmorra(personatge, finalBoss, true)) {
				if (salaActual.getMonstre() != null && salaActual.getMonstre().estaViu()) {
					System.out.println("Hay un monstruo en la sala... intentarás huir de él.");

					// sala Teranyina
					if (salaActual instanceof SalaTeranyina) {
						if (salaActual.intentarSortir(personatge.getForsa())) {
							System.out.println("Has escapado! A donde quieres ir?");
							puedeMover = true;
						} else {
							System.out.println("No has podido escapar del monstruo...");
						}
					}

					// sala pont
					if (salaActual instanceof SalaPont) {
						if (salaActual.intentarSortir(personatge.getAgilitat())) {
							System.out.println("Has escapado del puente y del monstruo! A donde quieres ir?");
							puedeMover = true;
						} else {
							// en esta sala si no consigues escapar recibes daño
							personatge.rebreDany(1);
							personatge.setCausaMort("Caída del puente");
							System.out.println("Has caído del puente! Vida actual: " + personatge.getVida());

							personatge.rebreDany(monstreSalaActual.getPenalització());
							personatge.setCausaMort("Caída del puente y ataque de " + monstreSalaActual.getNom());
							System.out.println("El monstruo te ha atacado por intentar huir. Vida actual: " + personatge.getVida());
						}
					}
					
					// sala comuna
					if (salaActual instanceof SalaComuna) {
						System.out.println("Al ser una sala común has escapado, pero te han hecho daño en el camino...");
						puedeMover = true;
						// en esta sala escapas si o si, pero recibes daño
						personatge.rebreDany(monstreSalaActual.getPenalització());
						personatge.setCausaMort("Huida y ataque de " + monstreSalaActual.getNom());
						personatge.setVida(personatge.getVida() - monstreSalaActual.getPenalització());
						System.out.println("Vida actual: " + personatge.getVida());
					}

					// sala trampa
					if(salaActual instanceof SalaTrampa) {
						// si caes en esta sala con monstruo recibes daño de la trampa + monstruo
						System.out.println("Entraste a una sala TRAMPA y con monstruo...");
						System.out.println("Escaparas con dificultad de las trampas y el monstruo, pero no saldras ileso.");
						personatge.setVida((personatge.getVida() - monstreSalaActual.getPenalització()) - SalaTrampa.getDanyTrampa());
						System.out.println("Vida actual: " + personatge.getVida());
					}

					// si no hay monstruo en la sala continuamos
					
					// sala trampa sin monstruo
				} else if(salaActual instanceof SalaTrampa) {
					// si caes en esta sala recibes daño de la trampa
					System.out.println("Entraste a una sala TRAMPA...");
					System.out.println("Te mueves agilmente entre las trampas pero te clavas una felcha en el pie...");
					personatge.setVida(personatge.getVida() - SalaTrampa.getDanyTrampa());
					System.out.println("Vida actual: " + personatge.getVida());
					puedeMover = true;
				} else {
					puedeMover = true;
				}

				if (puedeMover) {
					// impresion de movimiento
					// si insertas un movimiento invalido perderas el turno, y deberas volver a intentar escapar de la sala
					System.out.println("N-arriba | S-abajo | E-derecha | O-izquierda");
					char moviment = teclado.next().charAt(0);
					personatge.moure(moviment);
					System.out.println();
					limpiarPantalla();
					System.out.println("Te has movido a una nueva sala.");
					System.out.println();

					// si no ha escapado de la sala continuamos y guardamos datos de la sala y monstruos nuevos
					if (!Masmorra.hasSortitDeLaMasmorra(personatge, finalBoss, true)) {
						salaActual = Masmorra.obtenirSalaActual(personatge);
						monstreSalaActual = salaActual.getMonstre();
					}
				}

// sabiendo los datos de la sala actual   nueva, comprovamos si hay monstruo, si lo hay imprimimos datos del monstruo
				if (salaActual.getMonstre() != null && monstreSalaActual.estaViu()) {
					System.err.println("! ! EN LA SALA ACTUAL HAY UN MONSTRUO ! !");
					System.out.println(monstreSalaActual);
				} else {
					System.out.println("No hay ningún monstruo en la sala a la que te has movido");
				}
			}
			break;

		case 2:
			// atacar
			System.out.println("          -Atacar-");
			// si hay monstruo en la sala atacamos
			if(salaActual.getMonstre() != null && monstreSalaActual.estaViu()) {
				personatge.atacar(monstreSalaActual);

				// si no sigue vivo ganamos exp y subimos de nivel si llegamos a 30 de exp
				if(!monstreSalaActual.estaViu()) {
					personatge.setExperencia(personatge.getExperencia() + monstreSalaActual.getValorExperiencia());
					System.out.println("Monstruo muerto! Experiencia ganada: " + monstreSalaActual.getValorExperiencia());
					personatge.subirNivell(monstreSalaActual);
				} else {
					// si no muere el monstruo, este nos atacara 
					System.out.println();
					System.out.println("- EL MONSTRUO CONTRAATACA -");
					System.out.println();
					monstreSalaActual.atacar(personatge);

					// si morimos perdemos y guardamos causa de muerte
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
			// info, mostramos infromacion del personaje
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
			// si se seleciona un numero o letra erronea salta opcion invalida
			System.out.println("OPCIÓN INVÁLIDA");
		}


		// esto es meramente visual
		if (!entroInfo) {
			System.out.println("- - - - - - - - - - - - - - - - - ");
		}
	}

	// mostramos victoria
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
 
	// mostramos derrota
	public static void mostrarDerrota(Personatge personatge, String nombre) {
		System.out.println("--------------------------------");
		System.out.println("HAS MUERTO " + nombre + "...");
		System.out.println("--------------------------------");
		System.out.println("Experiencia conseguida: " + personatge.getExperencia());
		System.out.println("Causa de la muerte:        " + personatge.getCausaMort());
		System.out.println("Masmorra explorada: " + calcularPercentatgeExplorat() + "%");
	}

	// miramos si el personaje consiguio salir de la sala 
	public static boolean hasSortitDeLaMasmorra(Personatge personatge, Monstre chikiIbai, boolean noImprimir) {
		int fila = personatge.getPosicio(0);
		int col  = personatge.getPosicio(1);

		// si el personaje se encuentra en las 3 esquinas (0, 0 no puede ser una salida), Y el jefe final esta muerto, devuelve true
		if (((fila <= 0 && col >= COLUMNES-1) || (fila >= FILES-1 && col >= COLUMNES-1) || (fila >= FILES-1 && col <= 0))) {
			if (chikiIbai.getVida() < 1) {
				return true;	
			} else {
				// si esta vivo saldra un mensaje de guia para saber como ganar
				if(!noImprimir) {
				System.out.println("Intentas abrir la puerta, pero te fijas que esta cerrada con llave. \n"
						+ "Debes matar al GRAN JEFE final para poder obtener la llave y escapar de la mazmorra, deberia encontrarse cerca del centro");
				return false;
				}
			}

		}
		return false;
	}

	public static void limpiarPantalla() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	// metodo para calcular el % de la mazmorra explorada
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