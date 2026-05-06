package POO;

import java.util.Arrays;

public class Personatge implements Combatent {

	private String nom;
	private int vida;
	private int atac;
	private int experencia = 0;
	private int nivell = 0;
	private int agilitat;
	private int forsa;
	private int[] posicio = new int[2];
	private Tresor[] equipament;
	private String causaMort = "";

	public Personatge(String nom) {

		this.nom = nom;
		this.vida = valorRandom(5, 20);
		this.agilitat = valorRandom(4, 11);
		this.forsa = valorRandom(4, 11);
		this.atac = forsa;
		this.equipament = new Tresor[forsa];
		this.posicio[0] = 0;
		this.posicio[1] = 0;
		this.nivell = 0;
	}

	public String getNom() {
		return nom;
	}

	public int getVida() {
		return vida;
	}

	// si la vida es mayor que el máximo (20), ponemos el máximo permitido, para el mínimo no porque debe poder morir
	public void setVida(int vida) {
		if (vida > 20) {
			this.vida = 20;
		} else {
			this.vida = vida;
		}
	}

	public int getAtac() {
		return atac;
	}

	public int getAgilitat() {
		return agilitat;
	}

	public void setAgilitat(int agilitat) {
		if (agilitat < 4) {
			this.agilitat = 4;
		} else if (agilitat > 11) {
			this.agilitat = 11;
		} else {
			this.agilitat = agilitat;
		}
	}

	public int getForsa() {
		return forsa;
	}

	public void setForsa(int forsa) {
		if (forsa < 4) {
			this.forsa = 4;
		} else if (forsa > 11) {
			this.forsa = 11;
		} else {
			this.forsa = forsa;
		}
	}

	// pos 0 es la fila y pos 1 es la columna
	// 0 1 2
	// 1 2
	// 2

	public int getPosicio(int posicion) {
		return posicio[posicion];
	}

	public void setPosicio(int posFila, int posCol) {
		this.posicio[0] = posFila;
		this.posicio[1] = posCol;
	}



	public int getNivell() {
		return nivell;
	}

	public void setNivell(int nivell) {
		this.nivell = nivell;
	}

	public int getExperencia() {
		return experencia;
	}

	public void setExperencia(int experencia) {
		this.experencia = experencia;
	}

	public String getCausaMort() {
		return causaMort;
	}

	public void setCausaMort(String causaMort) {
		this.causaMort = causaMort;
	}

	public String toString() {
		return "Personaje: " + nom + "\n" +
				"Vida: " + vida + "\n" +
				"Agilidad: " + agilitat + "\n" +
				"Fuerza: " + forsa + "\n" +
				"Equipamiento: " + Arrays.toString(equipament) + "\n" +
				"PosicionFila: " + posicio[0] + "\n" +
				"PosicionCol: " + posicio[1];
	}

	/**
	 * @param m
	 */
	public void atacar(Monstre m) {
		int dany = atac;
		m.setVida(m.getVida() - dany);
		System.out.println("Monstruo atacado, daño: " + dany);
		System.out.println("Vida restante de " + m.getNom() + ": " + m.getVida());
	}

	// TODO El jugador ejecutará su método “explorar” en la sala en la que se encuentra actualmente 
	// y encontrará el tesoro que tiene la sala (si tiene) y lo añadirá a su equipamiento (si tiene espacio aún).

	public void explorar(Sala sala) {

		if (!sala.isExplorada()) {

			if (sala.ishayTesoro() && hayEspacioInventario()) {
				Tresor tresor = sala.getTresor();
				guardarTresor(tresor);

				if (tresor instanceof TresorMagic) {
					int vida = ((TresorMagic) tresor).getVidaRecuperada();
					rebreDany(-vida);
					System.out.println("TESORO MÁGICO! Recuperas " + vida + " de vida. Vida actual: " + getVida());

				} else if (tresor instanceof TresorMaleit) {
					int dany = ((TresorMaleit) tresor).getVidaPerduda();
					rebreDany(dany);
					setCausaMort("Tesoro maldito");
					System.out.println("TESORO MALDITO! Pierdes " + dany + " de vida. Vida actual: " + getVida());

				} else {
					System.out.println("Tesoro guardado en el inventario: " + tresor);
				}

			} else if (sala.ishayTesoro()) {
				System.out.println("Hay un tesoro, pero no suficiente espacio en el inventario. mala suerte");
			} else {
				System.out.println("No hay ningún tesoro en la sala...");
			}

			if (sala instanceof SalaTrampa) {
				int dany = ((SalaTrampa) sala).getDanyTrampa();
				rebreDany(dany);
				setCausaMort("trampa en la sala");
				System.out.println("TRAMPA! Pierdes " + dany + " de vida. Vida actual: " + getVida());
			}

			sala.setExplorada(true);

		} else {
			System.out.println("Esta sala ya está explorada");
		}
	}

	public boolean hayEspacioInventario() {
		int cont = 0;
		for (int i = 0; i < equipament.length; i++) {
			if (equipament[i] != null) {
				cont++;
			}
		}
		return cont < forsa;
	}

	public void guardarTresor(Tresor tresor) {
		boolean fin = false;
		for (int i = 0; i < equipament.length; i++) {
			if (equipament[i] == null && !fin) {
				equipament[i] = tresor;
				fin = true;
			}
		}
	}

	public void moure(char direccio) {
		int fila = getPosicio(0);
		int col = getPosicio(1);

		direccio = Character.toUpperCase(direccio);

		if (direccio == 'N' && fila > 0) {
			posicio[0]--;
		} else if (direccio == 'E' && col < 5) {
			posicio[1]++;
		} else if (direccio == 'S' && fila < 5) {
			posicio[0]++;
		} else if (direccio == 'O' && col > 0) {
			posicio[1]--;
		} else {
			System.out.println("Dirección inválida");
		}
	}

	@Override
	public int calcularAtac() {
		return (int) (Math.random() * getForsa()) + 1;
	}

	@Override
	public int rebreDany(int quantitat) {
		setVida(getVida() - quantitat);
		return getVida();
	}

	@Override
	public boolean estaViu() {
		return vida > 0;
	}

	public int valorRandom(int minimo, int maximo) {
		return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
	}

	public int getNumTresors() {
		int cont = 0;
		for (int i = 0; i < equipament.length; i++) {
			if (equipament[i] != null) {
				cont++;
			}
		}
		return cont;
	}

	public int getTotalOr() {
		int total = 0;
		for (int i = 0; i < equipament.length; i++) {
			if (equipament[i] != null) {
				total += equipament[i].getValor();
			}
		}
		return total;
	}

	public void subirNivell(Monstre monstre) {
		int experienciaSubirNvl = 30;
		int expRestante = 0;

		if (experencia >= experienciaSubirNvl) {

			//sube de nivel
			nivell++;

			//sube sus stats	
			System.out.println();
			System.out.println("!!!SUBISTE DE NIVEL!!!");
			System.out.println("Tu nivel actual es de: " + nivell);
			System.out.println();
			System.out.println("<STATS>");

			expRestante = experencia - 30;

			if(expRestante >= 10) {
				System.out.println("Ganaste experiencia de mas, te veras recompensado");
				System.out.println(
						"Vida: " + vida + " +5" +
								" | Agilidad: " + agilitat + " +5" +
								" | Fuerza/Ataque: " + forsa + " +5");
				vida += 5; 
				agilitat += 5;
				forsa += 5;
				atac += 5;

			} else {
				System.out.println(
						"Vida: " + vida + " +3" +
								" | Agilidad: " + agilitat + " +3" +
								" | Fuerza/Ataque: " + forsa + " +3");

				vida += 3; 
				agilitat += 3;
				forsa += 3;
				atac += 3;

			}
			experencia = 0;
		}

	}
}