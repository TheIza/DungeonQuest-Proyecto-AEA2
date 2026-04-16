package POO;

public class Monstre implements Combatent{
	private String nom;
	private int vida;
	private int penalització;
	private int valorExperiencia;
	
	private final String[] arrayNombres = {
		    "Gorvax",
		    "Zulmar",
		    "Thrakon",
		    "Mordak",
		    "Vexor",
		    "Kragnar",
		    "Blutox",
		    "Nargul",
		    "Zerath",
		    "Drokul"
		};
	
	public Monstre(int vida, int penalització) {
	
		this.nom = nombreAleatorio();
		this.vida = vida;
		
		this.penalització = valorRandom(0,3);
		
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
		this.penalització = valorRandom(0,3);
	}

	public int getValorExperiencia() {
		return valorExperiencia;
	}

	public void setValorExperiencia(int valorExperiencia) {
		this.valorExperiencia = valorExperiencia;
	}

	public String toString() {
		return "Monstre: " + nom + "\n" + "Vida: " + vida;
	}
	
	public int valorRandom(int minimo, int maximo) {
		return (int)(Math.random() * (maximo - minimo + 1)) + minimo;
	}
	public String nombreAleatorio(){
	
		int random = (int)(Math.random() * arrayNombres.length);
		return(arrayNombres[random]);
	}
}
