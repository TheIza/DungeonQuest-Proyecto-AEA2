package POO;

public class SalaTeranyina extends Sala {

	public SalaTeranyina (Tresor tresor, Monstre monstre) {
	super(tresor, monstre);	
	}
	
	@Override
	public boolean intentarSortir(int numeroForsaAgilitat) {
		boolean exit = false;
		int aleatori = (int) (Math.random() * 12) + 1;
		if (numeroForsaAgilitat >= aleatori) {
			exit = true;
		} else {
			exit = false;
		}
		return exit;	}	
}
