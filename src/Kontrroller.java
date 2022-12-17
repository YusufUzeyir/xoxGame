public class Kontrroller extends Main
{
    public static String[][] kutuKontrol1(int sayi, String x[][], String boyut)
    {
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

    public static boolean xoxKontrol(String[][] X) {
        String[][] tamami = xoxSakla(X);
        String[] satir = tamami[0], veri = {"XXX", "OOO"}, sutun = tamami[1], capraz = tamami[2];
        boolean dogruluk = true;
        for (int i = 0; i < 3; i++)
            dogruluk = (capraz[1].contains(veri[1]) || capraz[0].contains(veri[0]) || capraz[1].contains(veri[0]) || capraz[0].contains(veri[1]) || satir[i].contains(veri[1]) || satir[i].contains(veri[0]) || sutun[i].contains(veri[1]) || sutun[i].contains(veri[0])) ? false : dogruluk;
        return dogruluk;
    }


}
