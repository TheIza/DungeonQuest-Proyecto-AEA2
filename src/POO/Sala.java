package POO;

public abstract class Sala {

	protected Tresor tresor;
	protected Monstre monstre;
	protected boolean explorada;
	
	public Sala(Tresor tresor, Monstre monstre, boolean explorada) {
		this.tresor = tresor;
		this.monstre = monstre;
		this.explorada = explorada;
	}
	
	public Tresor getTresor() {
		return tresor;
	}

	public void setTresor(Tresor tresor) {
		this.tresor = tresor;
	}

	public Monstre getMonstre() {
		return monstre;
	}

	public void setMonstre(Monstre monstre) {
		this.monstre = monstre;
	}

	public boolean isExplorada() {
		return explorada;
	}

	public void setExplorada(boolean explorada) {
		this.explorada = explorada;
	}

	public abstract boolean intentarSortir(int numeroForsaAgilitat);
	
	

	@Override
	public String toString() {
		return "Sala [tresor=" + tresor + ", monstre=" + monstre + ", explorada=" + explorada + "]";
	}
}
