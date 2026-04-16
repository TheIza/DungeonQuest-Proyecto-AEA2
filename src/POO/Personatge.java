package POO;
import java.util.ArrayList;
import java.util.Arrays;
public class Personatge implements Combatent {
	private String nom;
	private int vida;
	private int atac;
	private int experencia = 0;
	private int agilitat;
	private int forsa;
	private int[] posicio = new int[2];
	private Tresor[] equipament;




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

	// pos 0 es la fila y pos 1 es la columna
	// 0 1 2
	// 1 2
	// 2
	//personatge.getPosicio(0); ~ 
	public int getPosicio(int posicion) {
		return posicio[posicion];
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
	} 

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

	// TODO El jugador executarà el seu mètode “explorar” en la sala en què es troba actualment 
	// i trobarà el tresor que té la sala (si en té) i l’afegirà al seu equipament (si té lloc encara).

	public void explorar(Sala sala) { // true la sala esta explorada || false la sala no esta explorada

		//si la sala no esta explorada 
		if(!sala.isExplorada() ) {
			//si la sala tiene tesoro y hay espacio en el inevntario este se guarda
			if(sala.ishayTesoro() && hayEspacioInventario()) {
				System.out.println("La sala te 1 tresor ~ guardat al inventari ");
				guardarTresor(sala.getTresor());
			} else if(sala.ishayTesoro() ) {
				System.out.println("La sala te 1 tesor, pero no hi ha espai a l'inventari ~ tresor perdut...");
			}			
			sala.setExplorada(true);
		} else { // la sala esta exploraada
			System.out.println("Aquesta sala ja esta explorada");
		}

	}
	
	public boolean hayEspacioInventario() {
		int cont = 0;
		for (int i = 0; i < equipament.length; i++) {
			if(equipament[i] != null) {
				cont++;
			}
		}
		if(cont < forsa) {
			return true;
		} else {
			return false;
		}
	}

	public void guardarTresor(Tresor tresor) {
		boolean fin = false;
		for (int i = 0; i < equipament.length; i++) {
			if(equipament[i] == null && !fin) {
				equipament[i] = tresor;
				fin = true;
			}
		}
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