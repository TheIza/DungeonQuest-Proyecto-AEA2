package POO;

public class SalaTrampa extends Sala{
	private int danyTrampa;
	
	public SalaTrampa(Tresor tresor, Monstre monstre) {
		super(tresor, monstre);
        this.danyTrampa = valorRandom(1, 3);
	}

	@Override
	public boolean intentarSortir(int numeroForsaAgilitat) {
		return true;
	}

    public int getDanyTrampa() {
        return danyTrampa;
    }
}
