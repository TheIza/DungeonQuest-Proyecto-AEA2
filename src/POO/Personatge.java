package POO;

import java.util.Arrays;
//TODO cambiar los valores del constructor para que sean aleatorios
public class Personatge implements Combatent {
	private String nom;
	private int vida;
	private int atac;
	private int experencia = 0;
	private int agilitat;
	private int forsa;
	private int[] posicio = new int[2];
	private Tresor[] equipament = new Tresor[forsa];



	public Personatge(String nom, int vida, int atac, int agilitat, int forsa) {

		this.nom = nom;
		if (vida >= 5 && vida <= 20) {
			this.vida = vida;
		} else {
			this.vida = 5;
		}

		if (atac >= 1 && atac <= 4) {
			this.atac = atac;
		} else {
			this.atac = 1;
		}

		if (agilitat >= 4 && agilitat <= 11) {
			this.agilitat = agilitat;
		} else {
			this.agilitat = 4;
		}

		if (forsa >= 4 && forsa <= 11) {
			this.forsa = forsa;
			this.equipament = new Tresor[forsa];
		} else {
			this.forsa = 4;
			this.equipament = new Tresor[4];
		}
		this.posicio[0] = 0;
		this.posicio[1] = 0;

	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		// si la vida aplicada supera el màxim o el mínim, s'aplica una vida mínima
		if (vida >= 5 && vida <= 20) {
			this.vida = vida;
		} else {
			this.vida = 5;
		}
	}

	public int getAtac() {
		return atac;
	}

	public void setAtac(int atac) {
		// si l'atac aplicat supera el màxim o el mínim, s'aplica un atac mínim
		if (atac >= 1 && atac <= 4) {
			this.atac = atac;
		} else {
			this.atac = 1;
		}
	}

	public int getAgilitat() {
		return agilitat;
	}

	public void setAgilitat(int agilitat) {
		// si l'agilitat aplicada supera el màxim o el mínim, s'aplica una agilitat mínima
		if (agilitat >= 4 && agilitat <= 11) {
			this.agilitat = agilitat;
		} else {
			this.agilitat = 4;
		}
	}

	public int getForsa() {
		return forsa;
	}

	public void setForsa(int forsa) {
		// si la força aplicada supera el màxim o el mínim, s'aplica una força mínima
		if (forsa >= 4 && forsa <= 11) {
			this.forsa = forsa;
		} else {
			this.forsa = 4;
		}
	}


	public int[] getPosicio() {
		return posicio;
	}

	public void setPosicio(int posFila, int posCol) {
		this.posicio[0] = posFila;
		this.posicio[1] = posCol;
	}


	// ToString

	public String toString() {
		return "Personatge: " + nom + "\n" + 
				"Vida: " + vida + "\n" +
				"Agilitat: " + agilitat + "\n" + 
				"Forsa: " + forsa  + "\n" +
				"Equipament: " + Arrays.toString(equipament) + "\n" +
				"PosicioFila: " + posicio[0] + "\n" + 
				"PosicioCol: " + posicio[1];
	} //TODO podria marcar en posicion en que tipo de sala esta

	/**
	 * 
	 * @param m
	 */
	public void atacar(Monstre m) {
		int dany = atac;
		// apliquem a la vida del monstre la seva vida actual menys el dany
		m.setVida(m.getVida() - dany);
		// mostrem el dany i la vida restant d'aquest
		System.out.println("Monstre atacat, dany: " + dany);
		System.out.println("Vida restant de " + m.getNom() + ": " + m.getVida());
	}

	// TODO revisa que tipo de sala es y lo que hay en la sala
	public void explorar() {
		System.out.println(); 

	}
	public boolean moure(char direccio){

		if(direccio == 'N' && (posicio[0]-1) >= 0) {
			posicio[0]--;
			return true;
		} else if(direccio == 'E' && (posicio[1]+1) <= 5) {
			posicio[1]++;
			return true;
		} else if(direccio == 'S' && (posicio[0]+1) <= 5) {
			posicio[0]++;
			return true;

		} else if(direccio == 'O' && (posicio[1]-1) >= 0) {
			posicio[1]--;
			return true;
		} else {
			System.out.println("Posicion invalida");
			return false;
		}

	}

	@Override
	//devuelve un valor random entre 1 y la fuerza del personaje
	public int calcularAtac() {
		return (int)(Math.random()*getAtac())+1;
	}

	@Override
	public int rebreDany(int quantitat) {
		setVida(getVida() - quantitat);
		return 0;
	}

	@Override
	public boolean estaViu() {
		if(vida > 0) {
			return true;
		} else {
			return false;
		}
	}





}