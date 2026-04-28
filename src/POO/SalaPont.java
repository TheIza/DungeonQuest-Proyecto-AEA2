package POO;

public class SalaPont extends Sala{

	public SalaPont(Tresor tresor, Monstre monstre) {
		super(tresor, monstre);
		// TODO Auto-generated constructor stub
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
		return exit;
	}
	
}
