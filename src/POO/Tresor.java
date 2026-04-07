package POO;

public class Tresor implements Combatent {
	private String nom;
	private int valor;
	private double pes;
	private int valorExperiencia;
	
	public Tresor(String nom, int valor, double pes) {
		this.nom = nom;
		this.valor = valor;
		this.pes = pes;
	}

	@Override
	public String toString() {
		return "Tresor: " + nom + "\n" + "Valor en monedes d'or: "+ valor;
	}

	@Override
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
