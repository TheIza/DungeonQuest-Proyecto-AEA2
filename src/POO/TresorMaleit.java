package POO;

public class TresorMaleit extends Tresor{
    private int vidaPerduda;

	public TresorMaleit(String nom, int valor, double pes,  int vidaPerduda) {
		super(nom, valor, pes);
		this.vidaPerduda = vidaPerduda;
	}

    public int getVidaPerduda() {
        return vidaPerduda;
    }

    @Override
    public String toString() {
        return super.toString() + " [MALEÏT - Perds " + vidaPerduda + " de vida]";
    }

}
