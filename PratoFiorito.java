import java.util.Random;
import java.util.Scanner;

public class PratoFiorito {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lunghezza;
        int larghezza;
        boolean control;
        boolean vittoria = false;
        int j;
        int i;
        System.out.println("Metti la larghezza");
        larghezza = in.nextInt();
        System.out.println("Metti la lunghezza");
        lunghezza = in.nextInt();
        int[][] matrice = creamatrice(larghezza, lunghezza);
        boolean[][] matriceBoolean = Creamatriceboolean(lunghezza, larghezza);
        PosizionamentoFiori(matrice, matriceBoolean);
        do {
            StampaCampo(matrice, matriceBoolean, lunghezza, larghezza);
            System.out.println("Inserisci le coordinate della tua scelta(prima la colonna e poi la riga)");
            System.out.print(">> ");
            i = in.nextInt() - 1;
            j = in.nextInt() - 1;
            control = FlowerControl(matrice, matriceBoolean, i, j);
            matriceBoolean = Scoperchiato(matriceBoolean, i, j);
            vittoria = ControlloMosseDisponibili(matriceBoolean, lunghezza, larghezza);
        } while (!control && !vittoria);
        if (vittoria) {
            System.out.println("Partita finita, ecco il campo scoperto: ");
            gameFinished(matrice, matriceBoolean);
            StampaCampo(matrice, matriceBoolean, lunghezza, larghezza);
            System.out.println("Hai vinto!");
        } else {
            System.out.println("Partita finita, ecco il campo scoperto: ");
            gameFinished(matrice, matriceBoolean);
            StampaCampo(matrice, matriceBoolean, lunghezza, larghezza);
            System.out.println("Hai perso");
        }
    }

    private static int[][] creamatrice(int larghezza, int lunghezza) {
        int[][] matrice = new int[larghezza][lunghezza];
        return matrice;
    }

    private static boolean[][] Creamatriceboolean(int lunghezza, int larghezza) {
        boolean[][] booleanmatrix = new boolean[larghezza][lunghezza];
        return booleanmatrix;
    }

    private static void PosizionamentoFiori(int[][] matrice, boolean[][] matriceBoolean) {
        Random random = new Random();
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (random.nextInt(7) == 1) {
                    matrice[i][j] = 9;
                    //matriceBoolean[i][j] = true;
                    incrementoAdiacenti(matrice, i, j);
                }
            }
        }
    }

    private static void StampaCampo(int[][] matrice, boolean[][] matriceBoolean, int lunghezza, int larghezza) {
        System.out.print("   ");
        for (int i = 0; i < larghezza; i++) {
            System.out.print((i + 1) + "  ");
        }
        System.out.println();
        for (int i = 0; i < larghezza; i++) {
            for (int j = 0; j < lunghezza; j++) {
                char quadrato = 9632;
                if (j == 0 && i < 9) {
                    System.out.print((i + 1) + "  ");
                }
                else if (j == 0 && i >= 9) {
                    
                    System.out.print((i + 1) + " ");
                }
                if (matriceBoolean[i][j] == true) {
                    System.out.print(matrice[i][j] + "  ");
                } else {
                    System.out.print(quadrato + "  ");
                }
            }
            System.out.println();
        }
    }

    private static boolean FlowerControl(int[][] matrice, boolean[][] matriceBoolean, int i, int j) {
        boolean controllo = false;
        if (matrice[i][j] == 9)
            return true;
        else if (matrice[i][j] == 0)
            checkZeros(matrice, matriceBoolean, i, j);
        return false;
    }

    private static void checkZeros(int[][] matrice, boolean[][] matriceBoolean, int i, int j) {
        if (matrice[i][j] == 0) {
            matriceBoolean[i][j] = true;
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0)
                        continue;
                    int new_x = i + x;
                    int new_y = j + y;
                    if (new_x >= 0 && new_x < matrice.length && new_y >= 0 && new_y < matrice[i].length
                            && matriceBoolean[new_x][new_y] == false) {
                        try {
                            checkZeros(matrice, matriceBoolean, new_x, new_y);
                        } catch (StackOverflowError e) {
                            matriceBoolean[i][j] = true;
                        }
                    }
                }
            }
        } else {
            matriceBoolean[i][j] = true;
        }
    }

    private static void gameFinished(int[][] matrice, boolean[][] matriceBoolean) {
        for (int x = 0; x < matrice.length; x++) {
            for (int y = 0; y < matrice[x].length; y++) {
                matriceBoolean[x][y] = true;
            }
        }
    }

    private static boolean ControlloMosseDisponibili(boolean[][] matriceBoolean, int lunghezza, int larghezza) {
        boolean vittoria = true;
        for (int i = 0; i < larghezza; i++) {
            for (int j = 0; j < lunghezza; j++) {
                if (matriceBoolean[i][j] == false) {
                    vittoria = false;
                    break;
                }
            }
        }
        return vittoria;
    }

    private static boolean[][] Scoperchiato(boolean[][] matriceBoolean, int i, int j) {
        matriceBoolean[i][j] = true;
        return matriceBoolean;
    }

    private static void incrementoAdiacenti(int[][] matrice, int i, int j) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0)
                    continue;
                int a = i + x;
                int b = j + y;
                if (a >= 0 && a < matrice.length && b >= 0 && b < matrice[i].length
                        && matrice[a][b] != 9) {
                    matrice[a][b]++;
                }
            }
        }
    }

}
