package xadrez;

import java.util.Scanner;
import static xadrez.Peca.aceitavel;

public class Tabuleiro {
  
    static int numeroLance;
    static String vezDeQuem;
    public static Peca[][] casas = new Peca[8][8];
    String nome = new String();
    String cor = new String();
    boolean jogada = true;
    boolean reiAtacando = false;
    int reiBrancoX = 4;
    int reiBrancoY = 7;
    int reiPretoX = 4;
    int reiPretoY = 0;
    boolean xeque = false;
    static Peca quemAtaca;
    static boolean possivelBloquear = false;
    Peca inexistente = new Peca(100, 100, "vazio", "  0  ");
    int fixo;
    int maximo;
    int minimo;
    char colunaOuFileiraFixa;
    int reiX;
    int reiY;
    int atacanteX;
    int atacanteY;
    String tipoDeAtaque;
    boolean enPassant = false;
    boolean xequeMate = false;

    public Tabuleiro() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (i < 2) {
                    cor = "Preta";
                } else if (i > 5) {
                    cor = "Branca";
                } else {
                    cor = "vazio";
                }
                switch (i) {
                    case 1, 6:
                        this.nome = " Peão";
                        break;

                    case 0, 7:
                        switch (j) {
                            case 0, 7:
                                this.nome = " Torre";
                                break;
                            case 1, 6:
                                this.nome = "Cavalo";
                                break;
                            case 2, 5:
                                this.nome = "Bispo";
                                break;
                            case 3:
                                this.nome = "Dama";
                                break;
                            case 4:
                                this.nome = "Rei";
                                break;
                        }
                        break;
                    default:
                        this.nome = "  0  ";
                }
                this.casas[j][i] = new Peca(j, i, cor, nome);
            }
        }
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < 8; i++) {
            System.out.println("\n");
            for (int j = 0; j < 8; j++) {
                System.out.print(casas[j][i].simbolo + "");
            }
        }
    }

    boolean diagonal(Peca peca, Peca atacada) {
        boolean bloqueado1 = false;
        boolean bloqueado2 = false;
        boolean bloqueado3 = false;
        boolean bloqueado4 = false;
        for (int i = 1; i <= 7; i++) {

            if (aceitavel(peca.x + i, peca.y + i)) {
                //mesma cor
                if (casas[peca.x + i][peca.y + i].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x + i && atacada.y == peca.y + i) {

                        return false;
                    }
                    bloqueado1 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x + i][peca.y + i].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x + i && atacada.y == peca.y + i && !bloqueado1) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x + i && atacada.y == peca.y + i && !bloqueado1) {
                        return true;
                    }
                    bloqueado1 = true;
                }
            }
            if (aceitavel(peca.x + i, peca.y - i)) {
                //mesma cor
                if (casas[peca.x + i][peca.y - i].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x + i && atacada.y == peca.y - i) {

                        return false;
                    }
                    bloqueado2 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x + i][peca.y - i].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x + i && atacada.y == peca.y - i && !bloqueado2) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x + i && atacada.y == peca.y - i && !bloqueado2) {
                        return true;
                    }
                    bloqueado2 = true;
                }
            }
            if (aceitavel(peca.x - i, peca.y - i)) {
                //mesma cor
                if (casas[peca.x - i][peca.y - i].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x - i && atacada.y == peca.y - i) {

                        return false;
                    }
                    bloqueado3 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x - i][peca.y - i].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x - i && atacada.y == peca.y - i && !bloqueado3) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x - i && atacada.y == peca.y - i && !bloqueado3) {
                        return true;
                    }
                    bloqueado3 = true;
                }
            }
            if (aceitavel(peca.x - i, peca.y + i)) {
                //mesma cor
                if (casas[peca.x - i][peca.y + i].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x - i && atacada.y == peca.y + i) {

                        return false;
                    }
                    bloqueado4 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x - i][peca.y + i].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x - i && atacada.y == peca.y + i && !bloqueado4) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x - i && atacada.y == peca.y + i && !bloqueado4) {
                        return true;
                    }
                    bloqueado4 = true;
                }
            }
        }

        return false;
    }

    boolean horizontalEVertical(Peca peca, Peca atacada) {
        boolean bloqueado1 = false;
        boolean bloqueado2 = false;
        boolean bloqueado3 = false;
        boolean bloqueado4 = false;
        for (int i = 1; i <= 7; i++) {

            if (aceitavel(peca.x + i, peca.y)) {
                //mesma cor
                if (casas[peca.x + i][peca.y].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x + i && atacada.y == peca.y) {

                        return false;
                    }
                    bloqueado1 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x + i][peca.y].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x + i && atacada.y == peca.y && !bloqueado1) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x + i && atacada.y == peca.y && !bloqueado1) {
                        return true;
                    }
                    bloqueado1 = true;
                }
            }
            if (aceitavel(peca.x, peca.y - i)) {
                //mesma cor
                if (casas[peca.x][peca.y - i].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x && atacada.y == peca.y - i) {

                        return false;
                    }
                    bloqueado2 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x][peca.y - i].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x && atacada.y == peca.y - i && !bloqueado2) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x && atacada.y == peca.y - i && !bloqueado2) {
                        return true;
                    }
                    bloqueado2 = true;
                }
            }
            if (aceitavel(peca.x - i, peca.y)) {
                //mesma cor
                if (casas[peca.x - i][peca.y].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x - i && atacada.y == peca.y) {

                        return false;
                    }
                    bloqueado3 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x - i][peca.y].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x - i && atacada.y == peca.y && !bloqueado3) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x - i && atacada.y == peca.y && !bloqueado3) {
                        return true;
                    }
                    bloqueado3 = true;
                }
            }
            if (aceitavel(peca.x, peca.y + i)) {
                //mesma cor
                if (casas[peca.x][peca.y + i].cor.equals(peca.cor)) {
                    //essa casa mesmo
                    if (atacada.x == peca.x && atacada.y == peca.y + i) {

                        return false;
                    }
                    bloqueado4 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x][peca.y + i].cor)) {
                    //essa mesmo
                    if (atacada.x == peca.x && atacada.y == peca.y + i && !bloqueado4) {

                        return true;
                    }
                    //cor contraria
                } else {
                    //essa casa
                    if (atacada.x == peca.x && atacada.y == peca.y + i && !bloqueado4) {
                        return true;
                    }
                    bloqueado4 = true;
                }
            }
        }

        return false;
    }
    
    public boolean movimentoPossivel(Peca peca, Peca atacada) {
        switch (peca.simbolo) {
            case " Torre":
                if (horizontalEVertical(peca, atacada)) {
                    return true;
                }

                break;
            case "Cavalo":
                int diferenca = 3;
                int outraDiferenca = 1;
                for (int i = 2; i > -3; i--) {
                    if (i != 0) {
                        if (aceitavel(peca.x + i, peca.y + i - outraDiferenca)) {
                            if (atacada.x == peca.x + i && atacada.y == peca.y + i - outraDiferenca && !peca.cor.equals(atacada.cor)) {
                                return true;
                            }
                        }
                        if (aceitavel(peca.x + i, peca.y + i - diferenca)) {
                            if (atacada.x == peca.x + i && atacada.y == peca.y + i - diferenca && !peca.cor.equals(atacada.cor)) {
                                return true;
                            }
                        }
                        diferenca *= outraDiferenca;
                        outraDiferenca *= -1;
                    }

                }

                break;
            case "Bispo":
                if (diagonal(peca, atacada)) {
                    return true;
                }
                break;
            case "Dama":
                if (diagonal(peca, atacada) || horizontalEVertical(peca, atacada)) {
                    return true;
                }
                break;
            case "Rei":
                for (int i = 1; i > -2; i--) {
                    for (int j = 1; j > -2; j--) {
                        if (!sendoAtacada(atacada, peca.cor)) {
                            if (aceitavel(atacada.x + j, atacada.y + i) && (j != 0 || i != 0)) {
                                if ((atacada.x == peca.x + j && atacada.y == peca.y + i && !atacada.cor.equals(peca.cor))) {

                                    return true;

                                }
                            }
                        }
                    }
                }
                if (roque(peca, atacada)) {
                    return true;
                }
                break;
            case " Peão":
                if ("Preta".equals(peca.cor)) {
                   if(peca.x<7){
                    if ((casas[peca.x + 1][peca.y].simbolo.equals(" Peão") && casas[peca.x + 1][peca.y].primeiroLance == numeroLance - 1) && !peca.cor.equals(casas[peca.x + 1][peca.y].cor) && (peca.y == 3 || peca.y == 4)) {
                        if ((atacada.x == peca.x + 1 && atacada.y == peca.y - 1) || (atacada.x == peca.x + 1 && atacada.y == peca.y + 1)) {
                            enPassant = true;
                            return true;
                        }
                    }
                }
                    if(peca.x>0){
                    if ((casas[peca.x - 1][peca.y].simbolo.equals(" Peão") && casas[peca.x - 1][peca.y].primeiroLance == numeroLance - 1) && !peca.cor.equals(casas[peca.x - 1][peca.y].cor) && (peca.y == 3 || peca.y == 4)) {
                        if ((atacada.x == peca.x + 1 && atacada.y == peca.y - 1) || (atacada.x == peca.x + 1 && atacada.y == peca.y + 1)) {
                            enPassant = true;
                            return true;
                        }
                    }
                    }
                    if (peca.y == 1 && atacada.x == peca.x && atacada.y == peca.y + 2 && !atacada.temPeca()) {
                        return true;
                    }
                    if (atacada.x == peca.x && atacada.y == peca.y + 1 && !atacada.temPeca()) {
                        return true;
                    }

                    if (aceitavel(atacada.x, atacada.y) && atacada.x == peca.x + 1 && atacada.y == peca.y + 1 && atacada.temPeca() && "Branca".equals(atacada.cor)) {
                        return true;

                    }
                    if (aceitavel(atacada.x, atacada.y) && atacada.x == peca.x - 1 && atacada.y == peca.y + 1 && atacada.temPeca() && "Branca".equals(atacada.cor)) {
                        return true;
                    }
                }
                if ("Branca".equals(peca.cor)) {
                    if(peca.x<7){
                    if ((casas[peca.x + 1][peca.y].simbolo.equals(" Peão") && casas[peca.x + 1][peca.y].primeiroLance == numeroLance - 1) && !peca.cor.equals(casas[peca.x + 1][peca.y].cor) && (peca.y == 3 || peca.y == 4)) {
                        if ((atacada.x == peca.x + 1 && atacada.y == peca.y - 1 && peca.y == 3) || (atacada.x == peca.x + 1 && atacada.y == peca.y + 1 && peca.y == 4)) {
                            enPassant = true;
                            return true;
                        }
                    }
                    }
                     if(peca.x>0){
                    if ((casas[peca.x - 1][peca.y].simbolo.equals(" Peão") && casas[peca.x - 1][peca.y].primeiroLance == numeroLance - 1) && !peca.cor.equals(casas[peca.x - 1][peca.y].cor) && (peca.y == 3 || peca.y == 4)) {
                        if ((atacada.x == peca.x + 1 && atacada.y == peca.y - 1) || (atacada.x == peca.x + 1 && atacada.y == peca.y + 1)) {
                            enPassant = true;
                            return true;
                        }
                    }
                     }
                    if (peca.y == 6 && atacada.x == peca.x && atacada.y == peca.y - 2 && !atacada.temPeca()) {
                        return true;
                    }
                    if (atacada.x == peca.x && atacada.y == peca.y - 1 && !atacada.temPeca()) {
                        return true;
                    }
                    if (aceitavel(atacada.x, atacada.y) && atacada.x == peca.x + 1 && atacada.y == peca.y - 1 && atacada.temPeca() && "Preta".equals(atacada.cor)) {
                        return true;

                    }
                    if (aceitavel(atacada.x, atacada.y) && atacada.x == peca.x - 1 && atacada.y == peca.y - 1 && atacada.temPeca() && "Preta".equals(atacada.cor)) {
                        return true;

                    }
                }
        }
        return false;

    }
    
    void selecionar() {
        numeroLance++;
            int X;
            int Y;
            Scanner leia;
        while (!xequeMate) {
            

            if (jogada) {
                vezDeQuem = "Branca";
            } else {
                vezDeQuem = "Preta";
            }
            leia = new Scanner(System.in);
            mostrarTabuleiro();
            
            if (xeque) {
                System.out.println("\nxeque");
                boolean bloquear = false;
                boolean mexerRei = false;
                boolean tomar = false;
                if (jogada) {
                    //rei pode mexer
                    if (temMovimentosPossiveis(casas[reiBrancoX][reiBrancoY])) {
                        System.out.println("rei pode mexer");
                        mexerRei = true;
                    }
                    casas[0][0].mover(quemAtaca, inexistente);
                    //tem mais de um atacante
                    if (sendoAtacada(casas[reiBrancoX][reiBrancoY], "Branca")) {

                        casas[0][0].mover(inexistente, quemAtaca);
                    } else {
                        casas[0][0].mover(inexistente, quemAtaca);
                        //dá pra tomar o atacante
                        if (sendoAtacada(quemAtaca, "Preta") && !reiAtacando) {
                            tomar = true;
                            System.out.println("da pra tomar atacante"+quemAtaca.simbolo);
                        }
                        //dá pra bloquear o ataque?
                        if (possivelBloquear) {
                            System.out.println("dá pra bloquear");
                            bloquear = true;
                        }
                        if(!mexerRei && !tomar && !bloquear){
                            xequeMate=true;
                        break;
                        }
                    }
                } else {
                    //rei pode mexer
                    if (temMovimentosPossiveis(casas[reiPretoX][reiPretoY])) {
                        System.out.println("rei pode mexer");
                        mexerRei = true;
                    }
                    casas[0][0].mover(quemAtaca, inexistente);
                    //tem mais de um atacante
                    if (sendoAtacada(casas[reiPretoX][reiPretoY], "Preta")) {

                        casas[0][0].mover(inexistente, quemAtaca);
                    } else {
                        casas[0][0].mover(inexistente, quemAtaca);
                        //dá pra tomar o atacante
                        if (sendoAtacada(quemAtaca, "Branca")&& !reiAtacando) {
                            System.out.println("da pra tomar atacante "+ quemAtaca.simbolo);

                            tomar = true;
                        }
                        //dá pra bloquear o ataque?
                        if (possivelBloquear) {
                            System.out.println("dá pra bloquear");
                            bloquear = true;
                        }

                    }
                }
                if(!mexerRei && !tomar && !bloquear){
                    xequeMate=true;
                    break;
                }
                System.out.println("\nEscolha uma peça(dê as coordenadas x e y");
                int x = leia.nextInt();
                int y = leia.nextInt();
                if (!casas[x][y].cor.equals(vezDeQuem) || !temMovimentosPossiveis(casas[x][y])) {

                } else {
                    mostraMovimentosPossiveisXeque(casas[x][y], mexerRei, bloquear, tomar);
                    System.out.println("\nEscolha para qual casas ir(dê as coordenadas x e y)");
                    X = leia.nextInt();
                    Y = leia.nextInt();
                    if (movimentoPossivelXeque(casas[x][y], casas[X][Y], mexerRei, bloquear, tomar) && casas[x][y].cor.equals(vezDeQuem)) {
                        casas[x][y].mover(casas[x][y], casas[X][Y], numeroLance);
                        
                        promocao(casas[X][Y]);
                        
                        if ("Rei".equals(casas[X][Y].simbolo)) {
                            if (jogada) {
                                reiBrancoX = X;
                                reiBrancoY = Y;
                            } else {
                                reiPretoX = X;
                                reiPretoY = Y;
                            }
                        }
                        xeque = false;
                        if (sendoAtacada(casas[reiBrancoX][reiBrancoY], "Branca") || sendoAtacada(casas[reiPretoX][reiPretoY], "Preta")) {
                            xeque = true;
                        }
                        mostrarTabuleiro();
                        jogada = !jogada;
                    }
                }
            } else {
                System.out.println("\nEscolha uma peça(dê as coordenadas x e y");
                int x = leia.nextInt();
                int y = leia.nextInt();
                if (!casas[x][y].cor.equals(vezDeQuem) || !temMovimentosPossiveis(casas[x][y])) {

                } else {
                    if ("Rei".equals(casas[x][y].simbolo)) {
                        System.out.println("fodase");
                    }
                    mostraMovimentosPossiveis(casas[x][y]);

                    System.out.println("\nEscolha para qual casas ir(dê as coordenadas x e y");
                    X = leia.nextInt();
                    Y = leia.nextInt();

                    if (movimentoPossivel(casas[x][y], casas[X][Y]) && casas[x][y].cor.equals(vezDeQuem)) {
                        if (roque(casas[x][y], casas[X][Y])) {
                            if (X == 7) {
                                casas[x][y].mover(casas[x][y], casas[6][Y], numeroLance);
                                casas[x][y].mover(casas[X][Y], casas[5][Y], numeroLance);
                            } else {
                                casas[x][y].mover(casas[x][y], casas[2][Y], numeroLance);
                                casas[x][y].mover(casas[X][Y], casas[3][Y], numeroLance);
                            }
                        } else {
                            casas[x][y].mover(casas[x][y], casas[X][Y], numeroLance);
                             promocao(casas[X][Y]);
                        }
                        if ("Rei".equals(casas[x][y].simbolo)) {
                            if (jogada) {
                                reiBrancoX = X;
                                reiBrancoY = Y;
                            } else {
                                reiPretoX = X;
                                reiPretoY = Y;
                            }
                        }
                        if (sendoAtacada(casas[reiBrancoX][reiBrancoY], "Branca") || sendoAtacada(casas[reiPretoX][reiPretoY], "Preta")) {
                            xeque = true;
                        }
                        
                        jogada = !jogada;
                    }
                }
            }
        }
        System.out.println("\nMate");
    }
    
    boolean xequeMate(){
        return false;
    }
    
    void mostraMovimentosPossiveis(Peca peca) {

        for (int i = 0; i < 8; i++) {
            System.out.println("\n");

            for (int j = 0; j < 8; j++) {
                if (movimentoPossivel(peca, casas[j][i])) {
                    System.out.print("  X  ");
                } else {
                    System.out.print(casas[j][i].simbolo + "");
                }
            }
        }
    }
    void mostraMovimentosPossiveisXeque(Peca peca, boolean mexerRei, boolean bloquear, boolean tomar) {

        for (int i = 0; i < 8; i++) {
            System.out.println("\n");

            for (int j = 0; j < 8; j++) {
                if (movimentoPossivelXeque(peca, casas[j][i], mexerRei, bloquear, tomar)) {
                    if (mexerRei && "Rei".equals(peca.simbolo)) {

                        System.out.print("  X  ");

                    } else if (bloquear && daPraBloquear(casas[j][i])) {
                        System.out.print("  X  ");
                    } else if (tomar && casas[j][i] == quemAtaca) {
                        System.out.print("  X  ");
                    } else {
                        System.out.print(casas[j][i].simbolo + "");
                    }
                } else {
                    System.out.print(casas[j][i].simbolo + "");
                }
            }
        }
    }

    boolean movimentoPossivelXeque(Peca peca, Peca atacada, boolean mexerRei, boolean bloquear, boolean tomar) {

        if (movimentoPossivel(peca, atacada)) {
            if (roque(peca, atacada)) {
                return false;
            }
            if (mexerRei && "Rei".equals(peca.simbolo)) {

                return true;

            } else if (bloquear && daPraBloquear(atacada)) {

                return true;

            } else if (tomar && atacada == quemAtaca) {

                return true;
            }

        }

        return false;
    }

    boolean daPraBloquear(Peca peca) {
        if ("Diagonal".equals(tipoDeAtaque)) {
            if (reiX > atacanteX) {
                if (reiY > atacanteY) {
                    int j = atacanteX;
                    for (int i = atacanteY; i < reiY; i++) {

                        if (peca.x == j && peca.y == i) {
                            return true;
                        }
                        j++;
                    }
                } else {
                    int j = atacanteX;
                    for (int i = atacanteY; i > reiY; i--) {

                        if (peca.x == j && peca.y == i) {
                            return true;
                        }
                        j++;
                    }
                }
            } else {
                if (reiY > atacanteY) {
                    int j = atacanteX;
                    for (int i = atacanteY; i < reiY; i++) {

                        if (peca.x == j && peca.y == i) {
                            return true;
                        }
                        j--;
                    }
                } else {
                    int j = atacanteX;
                    for (int i = atacanteY; i > reiY; i--) {

                        if (peca.x == j && peca.y == i) {
                            return true;
                        }
                        j--;
                    }
                }
            }
        } else {
            if ('x' == colunaOuFileiraFixa && peca.x == fixo && peca.y < maximo && peca.y > minimo) {
                return true;
            }
            if ('y' == colunaOuFileiraFixa && peca.y == fixo && peca.x < maximo && peca.x > minimo) {
                return true;
            }
        }
        return false;
    }

    boolean temMovimentosPossiveis(Peca peca) {

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (movimentoPossivel(peca, casas[j][i])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean sendoAtacada(Peca peca, String cor) {
        //Cavalo
        int diferenca = 3;
        int outraDiferenca = 1;
        for (int i = 2; i > -3; i--) {
            if (i != 0) {
                if (aceitavel(peca.x + i, peca.y + i - outraDiferenca)) {
                    if ("Cavalo".equals(casas[peca.x + i][peca.y + i - outraDiferenca].simbolo) && !cor.equals(casas[peca.x + i][peca.y + i - outraDiferenca].cor)) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x + i][peca.y + i - outraDiferenca];
                        }
                        return true;

                    }
                }
                if (aceitavel(peca.x + i, peca.y + i - diferenca)) {
                    if ("Cavalo".equals(casas[peca.x + i][peca.y + i - diferenca].simbolo) && !cor.equals(casas[peca.x + i][peca.y + i - diferenca].cor)) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x + i][peca.y + i - diferenca];
                        }
                        return true;
                    }
                }
                diferenca *= outraDiferenca;
                outraDiferenca *= -1;
            }

        }
        //Vertical ou Horizontal
        boolean VHbloqueado1 = false;
        boolean VHbloqueado2 = false;
        boolean VHbloqueado3 = false;
        boolean VHbloqueado4 = false;
        for (int i = 1; i <= 7; i++) {

            if (aceitavel(peca.x + i, peca.y)) {
                //mesma cor
                if (casas[peca.x + i][peca.y].cor.equals(cor)) {

                    VHbloqueado1 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x + i][peca.y].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Torre".equals(casas[peca.x + i][peca.y].simbolo) || "Dama".equals(casas[peca.x + i][peca.y].simbolo)) && !VHbloqueado1) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x + i][peca.y];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x + j][peca.y], casas[peca.x + i][peca.y].cor)) {
                                    fixo = peca.y;
                                    colunaOuFileiraFixa = 'y';
                                    maximo = peca.x + i;
                                    minimo = peca.x;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "horizontal";
                                }
                            }
                        }
                        return true;
                    }
                    VHbloqueado1 = true;
                }
            }
            if (aceitavel(peca.x, peca.y - i)) {
                //mesma cor
                if (casas[peca.x][peca.y - i].cor.equals(cor)) {

                    VHbloqueado2 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x][peca.y - i].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Torre".equals(casas[peca.x][peca.y - i].simbolo) || "Dama".equals(casas[peca.x][peca.y - i].simbolo)) && !VHbloqueado2) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x][peca.y - i];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x][peca.y - j], casas[peca.x][peca.y - i].cor)) {
                                    fixo = peca.x;
                                    colunaOuFileiraFixa = 'x';
                                    maximo = peca.y;
                                    minimo = peca.y - i;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "horizontal";
                                }
                            }
                        }
                        return true;
                    }
                    VHbloqueado2 = true;
                }
            }
            if (aceitavel(peca.x - i, peca.y)) {
                //mesma cor
                if (casas[peca.x - i][peca.y].cor.equals(cor)) {

                    VHbloqueado3 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x - i][peca.y].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Torre".equals(casas[peca.x - i][peca.y].simbolo) || "Dama".equals(casas[peca.x - i][peca.y].simbolo)) && !VHbloqueado3) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x - i][peca.y];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x - j][peca.y], casas[peca.x - i][peca.y].cor)) {
                                    fixo = peca.y;
                                    colunaOuFileiraFixa = 'y';
                                    maximo = peca.x;
                                    minimo = peca.x - i;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "horizontal";
                                }
                            }
                        }
                        return true;
                    }
                    VHbloqueado3 = true;
                }
            }
            if (aceitavel(peca.x, peca.y + i)) {
                //mesma cor
                if (casas[peca.x][peca.y + i].cor.equals(cor)) {

                    VHbloqueado4 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x][peca.y + i].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Torre".equals(casas[peca.x][peca.y + i].simbolo) || "Dama".equals(casas[peca.x][peca.y + i].simbolo)) && !VHbloqueado4) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x][peca.y + i];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x][peca.y + j], casas[peca.x][peca.y + i].cor)) {
                                    fixo = peca.x;
                                    colunaOuFileiraFixa = 'x';
                                    maximo = peca.y + i;
                                    minimo = peca.y;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "horizontal";
                                }
                            }
                        }
                        return true;
                    }
                    VHbloqueado4 = true;
                }
            }
        }
        //Diagonal
        boolean Dbloqueado1 = false;
        boolean Dbloqueado2 = false;
        boolean Dbloqueado3 = false;
        boolean Dbloqueado4 = false;
        for (int i = 1; i <= 7; i++) {

            if (aceitavel(peca.x + i, peca.y - i)) {
                //mesma cor
                if (casas[peca.x + i][peca.y - i].cor.equals(cor)) {

                    Dbloqueado1 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x + i][peca.y - i].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Bispo".equals(casas[peca.x + i][peca.y - i].simbolo) || "Dama".equals(casas[peca.x + i][peca.y - i].simbolo)) && !Dbloqueado1) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x + i][peca.y - i];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x + j][peca.y - j], casas[peca.x + i][peca.y - i].cor)) {
                                    reiX = peca.x;
                                    reiY = peca.y;
                                    atacanteX = quemAtaca.x;
                                    atacanteY = quemAtaca.y;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "Diagonal";
                                }
                            }
                        }
                        return true;
                    }
                    Dbloqueado1 = true;
                }
            }
            if (aceitavel(peca.x - i, peca.y - i)) {
                //mesma cor
                if (casas[peca.x - i][peca.y - i].cor.equals(cor)) {

                    Dbloqueado2 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x - i][peca.y - i].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Bispo".equals(casas[peca.x - i][peca.y - i].simbolo) || "Dama".equals(casas[peca.x - i][peca.y - i].simbolo)) && !Dbloqueado2) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x - i][peca.y - i];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x - j][peca.y - j], casas[peca.x - i][peca.y - i].cor)) {
                                    reiX = peca.x;
                                    reiY = peca.y;
                                    atacanteX = quemAtaca.x;
                                    atacanteY = quemAtaca.y;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "Diagonal";
                                }
                            }
                        }
                        return true;
                    }
                    Dbloqueado2 = true;
                }
            }
            if (aceitavel(peca.x - i, peca.y + i)) {
                //mesma cor
                if (casas[peca.x - i][peca.y + i].cor.equals(cor)) {

                    Dbloqueado3 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x - i][peca.y + i].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Bispo".equals(casas[peca.x - i][peca.y + i].simbolo) || "Dama".equals(casas[peca.x - i][peca.y + i].simbolo)) && !Dbloqueado3) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x - i][peca.y + i];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x - j][peca.y + j], casas[peca.x - i][peca.y + i].cor)) {
                                    reiX = peca.x;
                                    reiY = peca.y;
                                    atacanteX = quemAtaca.x;
                                    atacanteY = quemAtaca.y;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "Diagonal";
                                }
                            }
                        }
                        return true;
                    }
                    Dbloqueado3 = true;
                }
            }
            if (aceitavel(peca.x + i, peca.y + i)) {
                //mesma cor
                if (casas[peca.x + i][peca.y + i].cor.equals(cor)) {

                    Dbloqueado4 = true;
                    //casa sem peça
                } else if ("vazio".equals(casas[peca.x + i][peca.y + i].cor)) {

                    //cor contraria
                } else {
                    //essa casa
                    if (("Bispo".equals(casas[peca.x + i][peca.y + i].simbolo) || "Dama".equals(casas[peca.x + i][peca.y + i].simbolo)) && !Dbloqueado4) {
                        if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[peca.x + i][peca.y + i];

                            for (int j = i - 1; j > 0; j--) {
                                if (sendoAtacada2(casas[peca.x + j][peca.y + j], casas[peca.x + i][peca.y + i].cor)) {
                                    reiX = peca.x;
                                    reiY = peca.y;
                                    atacanteX = quemAtaca.x;
                                    atacanteY = quemAtaca.y;
                                    possivelBloquear = true;
                                    tipoDeAtaque = "Diagonal";
                                }
                            }
                        }
                        return true;
                    }
                    Dbloqueado4 = true;
                }
            }
        }
        //Rei
        reiAtacando = false;
        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (aceitavel(peca.x + j, peca.y + i) && (j != 0 || i != 0)) {
                    if ("Rei".equals(casas[peca.x + j][peca.y + i].simbolo) && !cor.equals(casas[peca.x + j][peca.y + i].cor)) {
                        if(sendoAtacada(peca, casas[peca.x + j][peca.y + i].cor)){
                            reiAtacando = true;
                        }
                        return true;
                    }
                }
            }
        }
        //Peão
        if ("Branca".equals(cor)) {
            if (aceitavel(peca.x + 1, peca.y + 1) && "Peão".equals(casas[peca.x + 1][peca.y + 1].simbolo) && !casas[peca.x + 1][peca.y + 1].cor.equals(cor)) {
                if ("Rei".equals(peca.simbolo)) {
                    quemAtaca = casas[peca.x + 1][peca.y + 1];
                }
                return true;

            }
            if (aceitavel(peca.x - 1, peca.y + 1) && "Peão".equals(casas[peca.x - 1][peca.y + 1].simbolo) && !casas[peca.x - 1][peca.y + 1].cor.equals(cor)) {
                if ("Rei".equals(peca.simbolo)) {
                    quemAtaca = casas[peca.x - 1][peca.y + 1];
                }
                return true;

            }
        } else {
            if (aceitavel(peca.x + 1, peca.y - 1) && "Peão".equals(casas[peca.x + 1][peca.y - 1].simbolo) && !casas[peca.x + 1][peca.y - 1].cor.equals(cor)) {
                if ("Rei".equals(peca.simbolo)) {
                    quemAtaca = casas[peca.x + 1][peca.y - 1];
                }
                return true;

            }
            if (aceitavel(peca.x - 1, peca.y - 1) && "Peão".equals(casas[peca.x - 1][peca.y - 1].simbolo) && !casas[peca.x - 1][peca.y - 1].cor.equals(cor)) {
                if ("Rei".equals(peca.simbolo)) {
                    quemAtaca = casas[peca.x - 1][peca.y - 1];
                }
                return true;

            }
        }
        return false;
    }

    boolean sendoAtacada2(Peca peca, String cor){
        for (int i = 0; i < 8; i++) {
            

            for (int j = 0; j < 8; j++) {
                if (movimentoPossivel(casas[j][i],peca )&&!casas[j][i].cor.equals(cor)) {
                    if ("Rei".equals(peca.simbolo)) {
                            quemAtaca = casas[j][i];
                        }
                    return true;
                } else {
                    
                }
            }
        }
        return false;
    }
    
    void promocao(Peca peca){
        Scanner leia;
        String nome;
        leia = new Scanner(System.in);
          if(" Peão".equals(peca.simbolo)&&(peca.y==0&&"Branca".equals(peca.cor))||(peca.y==7&&"Preta".equals(peca.cor))){
              System.out.println("Escolha uma promoção escrevendo a inicial da peça que deseja: ");
              nome = leia.nextLine();
              switch(nome){
                  case "c","C":
                     peca.simbolo="Cavalo";
                  break;
                  case "D","d":
                     peca.simbolo="Dama";
                  break;
                  case "B","b":
                     peca.simbolo="Bispo";
                  break;
                  case "t","T":
                     peca.simbolo=" Torre";
                  break;
                  default:
                     System.out.println("Opção inválida");
                     promocao(peca);
          }
              
          }
    }
            
    boolean roque(Peca peca, Peca atacada) {
        if ("Rei".equals(peca.simbolo) && peca.primeiroLance == 0 && atacada.primeiroLance == 0 && " Torre".equals(atacada.simbolo) && peca.y == atacada.y) {
            if (peca.x > atacada.x) {
                for (int i = 1; i < 4; i++) {
                    if (!sendoAtacada(casas[i][peca.y], peca.cor) && !casas[i][peca.y].temPeca()) {
                        return true;
                    }
                }
            } else {
                for (int i = 5; i < 7; i++) {
                    if (!sendoAtacada(casas[i][peca.y], peca.cor) && !casas[i][peca.y].temPeca()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
