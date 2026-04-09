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



	public Personatge(String nom) {

		this.nom = nom;
		this.vida = valorRandom(5, 20);
		this.atac = valorRandom(1, 4);
		this.agilitat = valorRandom(4, 11);
		this.forsa = valorRandom(4, 11);
		this.equipament = new Tresor[forsa];
		this.posicio[0] = 0;
		this.posicio[1] = 0;

	}



	public int getVida() {
		return vida;
	}

	//si la vida es mayor que el maximo(20), ponemos el maximo permitido, para el minimo no porque debe poder morir
	public void setVida(int vida) {
		if(vida > 20) {
			this.vida = 20;
		} else {
			this.vida = vida;
		}
	}

	
	public int getAtac() {
		return atac;
	}

	// si el ataque es mayor o menor de los maximos se les podra el valor mas cercano dentro de los limites
	public void setAtac(int atac) {
		if(atac < 1) {
		this.atac = 1;
		} else if(atac > 4) {
			this.atac = 4;
		} else {
			this.atac = atac;
		}
	}

	public int getAgilitat() {
		return agilitat;
	}

	public void setAgilitat(int agilitat) {
		if(agilitat < 4) {
			this.agilitat = 4;
			} else if(agilitat > 11) {
				this.agilitat = 11;
			} else {
				this.agilitat = agilitat;
			}
	}


	public int getForsa() {
		return forsa;
	}

	public void setForsa(int forsa) {
		if(forsa < 4) {
			this.forsa = 4;
			} else if(forsa > 11) {
				this.forsa = 11;
			} else {
				this.forsa = forsa;
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
	    return getVida();
	}

	@Override
	public boolean estaViu() {
		if(vida > 0) {
			return true;
		} else {
			return false;
		}
	}
	public int valorRandom(int minimo, int maximo) {
		 return (int)(Math.random() * (maximo - minimo + 1)) + minimo;
	}





}