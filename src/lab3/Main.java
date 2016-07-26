package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Funkcije f = new Funkcije();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Unesite celobrojni podatak koji treba sifrirati: ");
            String kri = in.readLine();

            System.out.println();

            System.out.print("Elementi super-rastuceg niza P: ");
            int[] nizP = f.kreirajNizP(3, (int)Math.pow(2, kri.length()));
            for (int i = 0; i < nizP.length; i++)
                System.out.print(nizP[i] + " ");

            System.out.println();

            System.out.print("Da li zelite da izmenite elemente niza P? (D/N) ");
            String odg = in.readLine();
            if(odg.equals("D"))
                nizP = f.azurirajNizP(nizP);

            System.out.println();

            System.out.print("Predlozena vrednost n: ");
            int n = f.n;
            System.out.println(n);

            System.out.print("Predlozena vrednost m: ");
            int m = f.vratiM(n);
            System.out.println(m);

            System.out.print("Predlozena vrednost im: ");
            int im = f.vratiIM(m, n);
            System.out.println(im);

            System.out.print("Javni kljuc J: ");
            int[] nizJ = f.vratiJavniKljuc(nizP, m, n);
            for (int i = 0; i < nizJ.length; i++)
                System.out.print(nizJ[i] + " ");
            
            System.out.println();

            // sifriranje:
            int C = f.kriptovanje(Integer.parseInt(kri), nizJ);
            System.out.println("C = " + C);

            System.out.println("Dekriptovanje podatka C: ");
            int[] out = f.dekriptovanje(C, nizP, m, im);
            String sOut = Arrays.toString(out).replace(", ", "").replace("[", "").replace("]", "");
            System.out.print("M = " +  Integer.parseInt(sOut, 2));
            
            System.out.println();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}