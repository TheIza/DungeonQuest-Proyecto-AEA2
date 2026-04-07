package POO;


public class Personatge implements Combatent {
	private String nom;
	private int vida;
	private int atac;
	private int experencia;
	private int agilitat;
	private int forsa;
	private Tresor[] equipament = new Tresor[forsa];
	
	
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
