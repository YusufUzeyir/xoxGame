public class Kazanan extends Main
{
    public static void kazanan(String[][] X)
    {
        String[][] hepsi = xoxSakla(X);  //xoxSakla metodunu Main classdan kalıttık
        String[] satir = hepsi[0], deger = {"XXX", "OOO"}, sutun = hepsi[1], capraz = hepsi[2];
        boolean[] player = {false, false};
        for (int i = 0; i < 3; i++) {
            player[0] = (satir[i].contains(deger[0]) || sutun[i].contains(deger[0]) || capraz[i].contains(deger[0])) ? true : player[0];
            player[1] = (satir[i].contains(deger[1]) || sutun[i].contains(deger[1]) || capraz[i].contains(deger[1])) ? true : player[1];
        }
        int kac = player[0] ? 1 : (player[1] ? 2 : 0);
        System.out.println(kac > 0 ? kac + ". Oyuncu Kazandı" : "Oyun Berabere");
    }

}
