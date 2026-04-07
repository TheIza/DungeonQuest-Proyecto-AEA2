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
		return "Tresor: " + nom + "\n" + "Valor en monedes d'or: "+ valor;
	}
	
	
}
