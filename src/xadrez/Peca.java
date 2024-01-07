package xadrez;

public class Peca {

    protected int x;
    protected int y;
    public String simbolo;
    public String cor;
    public int primeiroLance;

    public Peca(int x, int y, String cor, String simb) {
        this.x = x;
        this.y = y;
        this.cor = cor;
        this.simbolo = simb;
        this.primeiroLance = 0;
    }

    public void mover(Peca peca, Peca destino, int lance) {

        destino.cor = peca.cor;
        destino.simbolo = peca.simbolo;
        if(peca.primeiroLance==0){
        destino.primeiroLance = lance;}
        peca.cor = "vazio";
        peca.simbolo = "  0  ";

    }

    public void mover(Peca peca, Peca destino) {

        destino.cor = peca.cor;
        destino.simbolo = peca.simbolo;
        peca.cor = "vazio";
        peca.simbolo = "  0  ";

    }

    //tal peça pode ir para tal coordenada?
    static boolean aceitavel(int x, int y) {
        if (x < 8 && x >= 0 && y >= 0 && y < 8) {
            return true;
        } else {
            return false;
        }
    }

    boolean temPeca() {
        if ("  0  ".equals(this.simbolo)) {
            return false;
        }
        return true;
    }
}
