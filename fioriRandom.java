import java.util.Random;
public static void PosizionamentoFiori(int[][] matrice){
    Random random=new Random();
    for (int i=0; i< matrice.length;i++){
        for(int j=0; j<matrice[i].length;j++){
            if (random.nextInt(4)==1)
                matrice[i][j]=9;
        }
    }
}
