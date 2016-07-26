package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Funkcije
{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public int n = 0;

    public int[] kreirajNizP(int poc, int br)
    {
        int[] niz = new int[br+1];
        niz[0] = poc;

        int[] retNiz = new int[br];

        Random generator = new Random();

        for(int i=1; i<br+1; i++)
            for(int j=0; j<niz.length; j++)
                niz[i] += niz[j] + generator.nextInt(3);
        
        n = niz[br]; // vrednost N je zadnji element generisanog niza P

        for(int i=0; i<br; i++)
            retNiz[i] = niz[i];

        return retNiz;
    }

    public int vratiM(int n)
    {
        Random generator = new Random();
        int m = generator.nextInt(n-n%100);
        return m;
    }

    public int vratiIM(int m, int n)
    {
        Random generator = new Random();
        int x = 0;
        int im;

        do
        {
            im = generator.nextInt(n+1);
            x = (im*m) % n;
        }
        while (x != 1);
        
        return im;
    }

    public int[] vratiJavniKljuc(int[] nizP, int m, int n)
    {
        int[] nizJ = new int[nizP.length];

        for(int i=0; i<nizJ.length; i++)
            nizJ[i] = (nizP[i] * m) % n;

        return nizJ;
    }

    public int kriptovanje(int ulaz, int[] nizJ)
    {
        String sUlaz = Integer.toBinaryString(ulaz);
        int[] iUlaz = new int[sUlaz.length()];

        for(int i=0; i<sUlaz.length(); i++)
            iUlaz[i] = sUlaz.charAt(i)%48;
        
        int C = 0;

        for (int i = 0; i<iUlaz.length; i++)
            C += nizJ[i] * iUlaz[i];

        return C;
    }

    public int[] dekriptovanje(int ulaz, int[] nizP, int m, int im)
    {
        BigInteger bUlaz = new BigInteger(String.valueOf(ulaz));
        BigInteger bim = new BigInteger(String.valueOf(im));
        BigInteger bn = new BigInteger(String.valueOf(n));

        String sTC = bUlaz.multiply(bim).mod(bn).toString();
        int iTC = Integer.valueOf(sTC);
        System.out.println("TC = " + iTC);
        
        int pLen = nizP.length - 1;
        int[] out = new int[nizP.length];

        do
        {
            if(iTC >= nizP[pLen])
            {
                iTC = iTC - nizP[pLen];
                out[pLen] = 1;
                pLen = pLen - 1;
            }
            else if(iTC < nizP[pLen])
            {
                out[pLen] = 0;
                pLen = pLen - 1;
            }
        }
        while(iTC>0);

        return out;
    }

    public int[] azurirajNizP(int[] nizP)
    {
        Funkcije f = new Funkcije();
        int[] novNizP = new int[nizP.length];
        int[] pomNizP = new int[nizP.length];
        
        try
        {
            System.out.println("Unesite redni broj elementa niza koji zelite da promenite:");
            int br = Integer.parseInt(in.readLine());
            System.out.println("Unesite novu vrednost elementa:");
            int vr = Integer.parseInt(in.readLine());
            
            for (int i = 0; i < br; i++)
                novNizP[i] = nizP[i];

            pomNizP = f.kreirajNizP(vr, nizP.length-br);

            int cou = 0;
            for (int i = br; i < novNizP.length; i++)
            {
                novNizP[i] = pomNizP[cou];
                cou++;
            }

            System.out.print("Novi niz P je: ");
            for (int i = 0; i < novNizP.length; i++)
                System.out.print(novNizP[i] + " ");
        }
        catch (IOException ex)
        {
            Logger.getLogger(Funkcije.class.getName()).log(Level.SEVERE, null, ex);
        }

        return novNizP;
    }
}
