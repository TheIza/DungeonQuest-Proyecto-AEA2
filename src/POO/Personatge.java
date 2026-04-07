package POO;

public class Personatge implements Combatent {
	private String nom;
	private int vida;
	private int atac;
	private int experencia = 0;
	private int agilitat;
	private int forsa;
	private int posicioFila;
	private int posicioCol;
	private Tresor[] equipament = new Tresor[forsa];


	public Personatge(String nom, int vida, int atac, int agilitat, int forsa, int posicioFila, int posicioCol) {

		this.nom = nom;
		this.vida = vida ;
		this.atac = atac;
		this.agilitat = agilitat;
		this.forsa = forsa;
		this.posicioFila = posicioFila;
		this.posicioCol = posicioCol;
		this.equipament = new Tresor[forsa];
	}



	public int getVida() {
		return vida;
	}



	public void setVida(int vida) {

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
		
		if (forsa >= 4 && forsa <= 11) { 
			this.forsa = forsa;
		} else {
			this.forsa = 4;
		}
	}



	@Override
	public void atacar(Monstre m) {
		
	}
	public int calcularAtac() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int rebreDany(int quantitat) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean estaViu() {
		// TODO Auto-generated method stub
		return false;
	}
}
