package POO;

public class TresorMagic extends Tresor{
	private int vidaRecuperada;

	public TresorMagic(String nom, int valor, double pes,  int vidaRecuperada) {
		super(nom, valor, pes);
		this.vidaRecuperada = vidaRecuperada;
	}
    public int getVidaRecuperada() {
        return vidaRecuperada;
    }

    @Override
    public String toString() {
        return super.toString() + " MAGIC - Recupera " + vidaRecuperada + " de vida";
    }
	
}
