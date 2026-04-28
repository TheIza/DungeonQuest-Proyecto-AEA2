package POO;

public class SalaComuna extends Sala {


	public SalaComuna(Tresor tresor, Monstre monstre) {
		super(tresor, monstre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean intentarSortir(int numeroForsaAgilitat) {
		return true;
	}
}
