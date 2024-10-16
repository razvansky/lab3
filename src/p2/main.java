package p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Produs> produses = new ArrayList<Produs>();
        Scanner sc = new Scanner(new File("produse.csv"));
        Scanner sc2 = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(",");
            Produs produs = new Produs(split[0],Float.valueOf(split[1]),Integer.valueOf(split[2]),split[3]);
            produses.add(produs);
        }
        int n;
        while(true)
        {
            System.out.println("1.Afisare lista");
            System.out.println("2.Afisare lista prod expirate");
            System.out.println("3.Vanzare produs");
            System.out.println("4.Verificare incasari");
            System.out.println("5.Afisare pret minim");
            System.out.println("6.Salvare produse in fisier in fct de cant");
            n=sc2.nextInt();
            switch(n)
            {
                case 1: {
                    for (Produs produs : produses) {
                        System.out.println(produs);
                    }
                    break;
                }
                case 2:{
                    System.out.println("Produse exp:");
                    for(Produs produs: produses) {
                        if(produs.verifExp()==true)
                            System.out.println(produs);
                    }
                    break;
                }
                case 3: {
                    String nume;
                    System.out.print("Alegeti ce produs doriti sa cumparati:");
                    nume = sc2.next();
                    for(Produs produs: produses) {
                        if(produs.getNume().equals(nume)) {
                            vanzareProdus(produs);
                            if(produs.getCantitate()==0){
                                produses.remove(produs);
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Total incasari:"+Produs.getIncasari());
                    break;
                }
                case 5:{
                    pretMinim(produses);
                    break;
                }
                case 6:{
                    System.out.print("Cantitate:");
                    int cantitate = sc2.nextInt();
                    PrintStream flux_out = new PrintStream ("out.csv");
                    for(Produs produs: produses) {
                        if(produs.getCantitate()<cantitate) {
                            String line = produs.toString();
                            line.replace(" ",",");
                            flux_out.println(line);
                        }
                    }
                    flux_out.close();
                    break;
                }
                default:break;
            }
        }

    }


    public static void vanzareProdus(Produs prod){
        Scanner sc = new Scanner(System.in);
        int n=0;
        boolean ok=false;
        while(ok==false) {
            System.out.print("Cate produse doriti sa cumparati:");
            n = sc.nextInt();
            if(prod.getCantitate()>=n) {
                ok = true;
            }
            else {
                System.out.print("\nnr introdus trebuie sa fie mai mic sau egal cu " + prod.getCantitate());
            }

        }
        int nrnou = prod.getCantitate()-n;
        prod.setCantitate(nrnou);
        Produs.setIncasari(n*prod.getPret());

    }
    public static void pretMinim(List<Produs> lista){
        float minim=lista.get(0).getPret();
        for(Produs produs: lista) {
            if(minim>produs.getPret())
                minim=produs.getPret();;
        }
        for(Produs produs: lista) {
            if(minim==produs.getPret())
                System.out.println(produs.toString());
        }
    }

}
