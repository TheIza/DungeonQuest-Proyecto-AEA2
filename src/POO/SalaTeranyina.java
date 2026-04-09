package POO;

public class SalaTeranyina extends Sala {

	public SalaTeranyina (Tresor tresor, Monstre monstre, boolean explorada) {
	super(tresor, monstre, explorada);	
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
