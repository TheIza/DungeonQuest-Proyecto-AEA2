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

    public Masmorra() {}

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
        
        /**
         * añadimos un tipo de final boss en el medio de todo 
         */
        Monstre finalBoss = new Monstre(30, 3);
        finalBoss.setNom("ChikiIbai");
        sales[FILES/2][COLUMNES/2] = new SalaComuna(null, finalBoss);
        
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
            return new TresorMagic("Poció màgica", 100, 0.5, valorRandom(2, 5));
        } else if (rand < 35) {
            return new TresorMaleit("Anell maleït", 50, 0.3, valorRandom(1, 3));
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

    public static void mostrarOpciones(Personatge personatge) {

        Sala salaActual = Masmorra.obtenirSalaActual(personatge);

    		if(!salaActual.isExplorada()) {
    		    System.out.println("0. Explorar");
    		} 
    		System.out.println("1. Moure");
    		if(salaActual.getMonstre() != null && salaActual.getMonstre().estaViu()) {
    		    System.out.println("2. Atacar");
    		}
    	
        System.out.println();
        System.out.print("Opcio: ");
        Scanner teclado = new Scanner(System.in);
        int menu = teclado.nextInt();
        System.out.println();

        Monstre monstreSalaActual = salaActual.getMonstre();

        switch (menu) {

            case 0:
            		if(salaActual.isExplorada()) {
                        System.out.println("Esta sala ya ha sido explorada");
                        break;
            		}
                System.out.println("-Explorar-");
                personatge.explorar(salaActual);
                break;

            case 1:
                boolean puedeMover = false;
                System.out.println("-Moure-");
                if (Masmorra.hasSortitDeLaMasmorra(personatge)) {
                    break;
                }
                if (salaActual.getMonstre() != null && salaActual.getMonstre().estaViu()) {
                    System.out.println("Hay un monstruo en la sala... Intentaras escapar de el.");

                    if (salaActual instanceof SalaTeranyina) {
                        if (salaActual.intentarSortir(personatge.getForsa())) {
                            System.out.println("Escapaste! A donde quieres ir");
                            puedeMover = true;
                        } else {
                            System.out.println("No pudiste escapar del monstruo...");
                        }
                    }

                    if (salaActual instanceof SalaPont) {
                        if (salaActual.intentarSortir(personatge.getAgilitat())) {
                            System.out.println("Escapaste del puente y del mounstruo! A donde quieres ir");
                            puedeMover = true;
                        } else {
                        	 personatge.rebreDany(1);
                             personatge.setCausaMort("Caida del puenteeee");
                             System.out.println("Has caido del puente! Vida actual: " + personatge.getVida());
                             /**
                              * mas que nada, porque si te vas no le vas a dejar al mounstruo ahi tal cual, asi que te pega un guantazo
                              */
                         personatge.rebreDany(monstreSalaActual.getPenalització());
                         personatge.setCausaMort("Caida del puente y ataque de " + monstreSalaActual.getNom());
                             System.out.println("El mounstruo te ha atacado por intentar huir, Vida actual: " + personatge.getVida());
                        }
                    }

                    if (salaActual instanceof SalaComuna) {
                        System.out.println("Al ser una sala comuna escapaste, pero te daño por el camino...");
                        puedeMover = true;
                        personatge.rebreDany(monstreSalaActual.getPenalització());
                        personatge.setCausaMort("Huida y ataque de " + monstreSalaActual.getNom());
                        personatge.setVida(personatge.getVida() - monstreSalaActual.getPenalització());
                        System.out.println("Vida actual: " + personatge.getVida());
                    }

                } else {
                    puedeMover = true;
                }

                if (puedeMover) {
                    System.out.println("N-arriba | S-abajo | E-derecha | O-izquierda");
                    char moviment = teclado.next().charAt(0);
                    personatge.moure(moviment);
                    
                    if (Masmorra.hasSortitDeLaMasmorra(personatge)) {
                        break;
                    }

                    salaActual = Masmorra.obtenirSalaActual(personatge);
                    monstreSalaActual = salaActual.getMonstre();
                }
               
                System.out.println("Te moviste a una nueva sala.");
                if (salaActual.getMonstre() != null && monstreSalaActual.estaViu()) {
                    System.out.println("En la sala actual hay un monstruo");
                    System.out.println(monstreSalaActual);
                } else {
                    System.out.println("No hay monstruo en la sala que te moviste");
                }
                break;

            case 2:
                System.out.println("-Atacar-");
                if(salaActual.getMonstre() != null && monstreSalaActual.estaViu()) {
                    personatge.atacar(monstreSalaActual);

                    if(!monstreSalaActual.estaViu()) {
                        personatge.setExperencia(personatge.getExperencia() + monstreSalaActual.getValorExperiencia());
                        System.out.println("Mounstruo muerto! Experiencia ganada: " + monstreSalaActual.getValorExperiencia());
                    } else {
                    		System.out.println("EL MOUNSTRUO CONTRAATACA");
                        monstreSalaActual.atacar(personatge);
                        		if(!personatge.estaViu()) {
                        			System.out.println("Has muerto...");
                                    personatge.setCausaMort("Ataque de " + monstreSalaActual.getNom());
                        		}
                    }
                } else {
                    System.out.println("No hay ningun mounstruo en esta sala.");
                }
                break;

            default:
                System.out.println("OPCION INVALIDA");
        }

        System.out.println("- - - - - - - - - - - - - - - - - ");
    }
    
    public static void mostrarVictoria(Personatge personatge) {
        System.out.println("--------------------------------");
        System.out.println("HAS ESCAPADO DE LA MASMORRA");
        System.out.println("--------------------------------");
        System.out.println("Experiencia:       " + personatge.getExperencia());
        System.out.println("Tesoros recojidos: " + personatge.getNumTresors());
        System.out.println("Monedas de oro:      " + personatge.getTotalOr());
        System.out.println("Vida restante:      " + personatge.getVida());
        System.out.println("Masmorra explorada: " + calcularPercentatgeExplorat() + "%");
    }

    public static void mostrarDerrota(Personatge personatge) {
        System.out.println("--------------------------------");
        System.out.println("HAS MORT...");
        System.out.println("--------------------------------");
        System.out.println("Experiencia conseguida: " + personatge.getExperencia());
        System.out.println("Causa de la muerte:        " + personatge.getCausaMort());
        System.out.println("Masmorra explorada: " + calcularPercentatgeExplorat() + "%");
    }

    public static boolean hasSortitDeLaMasmorra(Personatge personatge) {
        int fila = personatge.getPosicio(0);
        int col  = personatge.getPosicio(1);
        
        if ((fila <= 0 && col >= 5) || (fila >= 5 && col >= 5) || (fila >= 5 && col <= 0)) {
        	return true;
        } else {
        	return false;
        }
        
//        return fila < 0 || fila >= FILES || col < 0 || col >= COLUMNES;
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