package POO;

public abstract class Sala {

    protected Tresor tresor;
    protected Monstre monstre;
    protected boolean explorada;

    public Sala(Tresor tresor, Monstre monstre) {
        if (valorRandom(0, 1) == 0 && monstre != null) {
            this.monstre = monstre;
        } else {
            this.monstre = null;
        }

        if (valorRandom(0, 1) == 0 && tresor != null) {
            this.tresor = tresor;
        } else {
            this.tresor = null;
        }

        this.explorada = false;
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

    public boolean valorRandom() {
        return (int)(Math.random() * 2) != 0;
    }

    public int valorRandom(int minimo, int maximo) {
        return (int)(Math.random() * (maximo - minimo + 1)) + minimo;
    }

    @Override
    public String toString() {
        return "Sala [tresor=" + tresor + ", monstre=" + monstre + ", explorada=" + explorada + "]";
    }
}