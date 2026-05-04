package POO;

public class Tresor{
	private String nom;
	private int valor;
	private double pes;
	
	public Tresor(String nom, int valor, double pes) {
		this.nom = nom;
		this.valor = valor;
		this.pes = pes;
	}

	public String toString() {
		return "Tesoro: " + nom + "\n" + "Valor en monedas de oro: "+ valor;
	}

	public int getValor() {
		return valor;
	}


		
	
	
}
