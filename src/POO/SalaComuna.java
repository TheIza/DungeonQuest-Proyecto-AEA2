package POO;

public class SalaComuna extends Sala {

	public SalaComuna(Tresor tresor, Monstre monstre, boolean explorada) {
		super(tresor, monstre, explorada);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean intentarSortir(int numeroForsaAgilitat) {
		return true;
	}
}
