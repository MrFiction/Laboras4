/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.*;
import laborai.studijosktu.*;



/**
 *
 * @author Laptopas
 */
public class Test {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        atvaizdzioTestas();
        //greitaveikosTestas();
    }

    public static void atvaizdzioTestas() {
        Kazkas item1 = new Kazkas(1000.2, ValueGenerator.randomDate(), "1st57275", 20714410);
        Kazkas item2 = ValueGenerator.randomObject();
        Kazkas item3 = ValueGenerator.randomObject();
        Kazkas item4 = ValueGenerator.randomObject();
        Kazkas item5 = ValueGenerator.randomObject();
        Kazkas item6 = ValueGenerator.randomObject();

        // Raktų masyvas
        String[] Id = {"RA4212", "RA4574", "AY31252", "TA17531", "NA74575", "DG523623", "TW34743", "BA61613"};
        int id = 0;
        MapKTUx<String, Kazkas> atvaizdis
                = new MapKTUx(new String(), new Kazkas(), HashType.DIVISION);
        // Reikšmių masyvas
        Kazkas[] items = {item1, item2, item3, item4, item5, item6};
        for (Kazkas a : items) {
            atvaizdis.put(Id[id++], a);
        }
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(atvaizdis.contains(Id[6]));
        Ks.oun(atvaizdis.contains(Id[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(atvaizdis.remove(Id[1]));
        Ks.oun(atvaizdis.remove(Id[7]));
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(atvaizdis.get(Id[2]));
        Ks.oun(atvaizdis.get(Id[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(atvaizdis);
    }

    //Konsoliniame režime
    private static void greitaveikosTestas() {
        System.out.println("Greitaveikos tyrimas:\n");
        GreitaveikosTyrimas gt = new GreitaveikosTyrimas();
        //Šioje gijoje atliekamas greitaveikos tyrimas
        new Thread(() -> gt.pradetiTyrima(),
                "Greitaveikos_tyrimo_gija").start();
        try {
            String result;
            while (!(result = gt.getResultsLogger().take())
                    .equals(GreitaveikosTyrimas.FINISH_COMMAND)) {
                System.out.println(result);
                gt.getSemaphore().release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gt.getSemaphore().release();
    }

}
