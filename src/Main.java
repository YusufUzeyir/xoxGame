import java.util.Scanner;
public class Main {
    public static void main(String args[]) {

        //region Genel değişken atamaları
        String XveO[] = {"X", "O"};
        String kutular[][] = {{"⬜", "⬜", "⬜"}, {"⬜", "⬜", "⬜"}, {"⬜", "⬜", "⬜"}};
        Scanner input = new Scanner(System.in);
        int Xsirasi = 0, deger;
        //endregion

        do {
            //region Oyunun tahtaya çizdirilmesi
            System.out.println();
            for (int i = 0; i < kutular.length; i++) {
                for (int j = 0; j < kutular[i].length; j++) {
                    System.out.print((j < kutular[i].length) ? "\t" : "");
                    System.out.print(kutular[i][j]);
                }
                System.out.println();
            }
            //endregion

            //region Sıranın yazdırılması
            do {
                System.out.print("Sıra " + (Xsirasi + 1) + ". oyuncuda...");
                deger = input.nextInt();
            }
            while (deger < 1 || deger > 9 || kutuKontrol(deger, kutular) == false);
            kutular = kutuKontrol1(deger, kutular, XveO[Xsirasi]);
            //endregion

            Xsirasi = ((XveO.length) - 1 == Xsirasi ? 0 : Xsirasi + 1);
        }
        while (test(kutular) && xoxKontrol(kutular));

        System.out.println();
        for (int i = 0; i < kutular.length; i++) {
            for (int j = 0; j < kutular[i].length; j++) {
                System.out.print((j < kutular[i].length) ? "\t" : "");
                System.out.print(kutular[i][j]);
            }
            System.out.println();
        }
        kazanan(kutular);
    }

    public static boolean kutuKontrol(int sayi, String X[][]) {
        String k = "⬜";
        int a, b, degeri = sayi, count = 3;
        if (sayi % count == 0)
            sayi = sayi - 1;
        double veri = sayi / count;
        a = (int) Math.floor(veri);
        if (a >= 3)
            a = 2;
        b = (degeri - (a * count)) - 1;
        if (X[a][b] == k)
            return true;
        else
            return false;
    }
    public static String[][] kutuKontrol1(int sayi, String x[][], String boyut) {
        if (kutuKontrol(sayi, x)) {
            int a, b, degeri = sayi, count = 3;
            if (sayi % count == 0)   //dizinin iki boyutlu olması
                sayi = sayi - 1;
            double veri = sayi / count;
            b = (int) Math.floor(veri);
            if (b >= 3)
                b = 2;
            a = (degeri - (b * count)) - 1;
            x[b][a] = boyut;
        }
        return x;
    }
    public static String[][] xoxSakla(String[][] X) {
        int length = 3;
        String[] capraz = {"", "", ""}, sutun = {"", "", ""}, satir = {"", "", ""}, sakla = new String[2];
        //satır-sütun-çapraz adında 3 dizi oluştur ve kontol sağlanması için gerekli atamaları aşağıda yap...


        //matrisin tüm satır ve sütunları tarayabilmek için for içinde for oluşturdum
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < X[i].length; j++) {
                sakla[0] = (X[i][j]);   //Her seferinden matrisin satır ve sütünlarını tarayarak değeri daha sonra atamak için sakla
                sutun[i] = sakla[0] + sutun[i];
                satir[j] = sakla[0] + satir[j];
                if ((i == 0 && j == 0) || (i == 2 && j == 2))
                    capraz[0] = capraz[0] + sakla[0];
                else if ((i == 0 && j == 2) || (i == 2 && j == 0))
                    capraz[1] = sakla[0] + capraz[1];
                if (i == 1 && j == 1) {
                    capraz[1] = capraz[1] + sakla[0];
                    capraz[0] = capraz[0] + sakla[0];

                }
            }
        }
        String sak[][] = {satir, sutun, capraz};
        return sak;
    }
    public static boolean xoxKontrol(String[][] X) {
        String[][] tamami = xoxSakla(X);
        String[] satir = tamami[0], veri = {"XXX", "OOO"}, sutun = tamami[1], capraz = tamami[2];
        boolean dogruluk = true;
        for (int i = 0; i < 3; i++)
            dogruluk = (capraz[1].contains(veri[1]) || capraz[0].contains(veri[0]) || capraz[1].contains(veri[0]) || capraz[0].contains(veri[1]) || satir[i].contains(veri[1]) || satir[i].contains(veri[0]) || sutun[i].contains(veri[1]) || sutun[i].contains(veri[0])) ? false : dogruluk;
        return dogruluk;
    }
    public static void kazanan(String[][] X) {
        String[][] hepsi = xoxSakla(X);
        String[] satir = hepsi[0], deger = {"XXX", "OOO"}, sutun = hepsi[1], capraz = hepsi[2];
        boolean[] player = {false, false};
        for (int i = 0; i < 3; i++) {
            player[0] = (satir[i].contains(deger[0]) || sutun[i].contains(deger[0]) || capraz[i].contains(deger[0])) ? true : player[0];
            player[1] = (satir[i].contains(deger[1]) || sutun[i].contains(deger[1]) || capraz[i].contains(deger[1])) ? true : player[1];
        }
        int kac = player[0] ? 1 : (player[1] ? 2 : 0);
        System.out.println(kac > 0 ? kac + ". Oyuncu Kazandı" : "Oyun Berabere");
    }
    public static boolean test(String[][] X) {
        String k = "⬜";
        boolean dogruluk = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < X[i].length; j++)
                if (X[i][j] == k)
                    dogruluk = true;
        }
        return dogruluk;
    }
}
