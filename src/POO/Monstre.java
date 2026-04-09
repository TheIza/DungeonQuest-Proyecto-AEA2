package POO;

public class Monstre implements Combatent{
	private String nom;
	private int vida;
	private int penalització;
	private int valorExperiencia;
	
	
	
	public Monstre(String nom, int vida, int penalització) {
	
		this.nom = nom;
		this.vida = vida;
		
		if (penalització >= 0 || penalització <= 3 ) {
			this.penalització = penalització;
		} else {
			this.penalització = 0;
		}
		
		this.valorExperiencia = (vida * 2);
	}
	
	public int calcularAtac() {
		return 1 + (int)(Math.random() * this.vida);
	}
	
	public int rebreDany(int quantitat) {
		vida -= quantitat;
		if (vida < 0) {
			vida = 0;
		}
		return vida;
	}
	
	public boolean estaViu() {
		boolean estatViu = (vida > 0) ?  true : false; 
		return estatViu;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getPenalització() {
		return penalització;
	}

	public void setPenalització(int penalització) {
		this.penalització = penalització;
	}

	public int getValorExperiencia() {
		return valorExperiencia;
	}

	public void setValorExperiencia(int valorExperiencia) {
		this.valorExperiencia = valorExperiencia;
	}

	public String toString() {
		return "Nom: " + nom + "\n" + "Vida: " + vida;
	}
	
	
	
}
