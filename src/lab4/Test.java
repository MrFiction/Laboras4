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

    final static int sampleSize = 10;
    private ValueGenerator gen = new ValueGenerator();
    Random rndInt = new Random();


    static Kazkas[] set;
    static SortedSetADTx<Kazkas> aSerija = new BstSetKTUx(new Kazkas(), Kazkas.byPrice);

     static SortedSetADTx<Kazkas> generuotiAibe(int kiekis, int generN) {
        set = new Kazkas[generN];
        for (int i = 0; i < generN; i++) {
            set[i] = ValueGenerator.randomObject();
        }
        Collections.shuffle(Arrays.asList(set));
        aSerija.clear();
        for (int i = 0; i < kiekis; i++) {
            aSerija.add(set[i]);
        }
        return aSerija;
    }
   
    
    public static void Testing() throws CloneNotSupportedException{
        Kazkas item1 = new Kazkas(1000.2, ValueGenerator.randomDate(), "1st57275", 20714410);
        Kazkas item2 = ValueGenerator.randomObject();
        Kazkas item3 = ValueGenerator.randomObject();
        Kazkas item4 = ValueGenerator.randomObject();
        Kazkas item5 = ValueGenerator.randomObject();
        Kazkas item6 = ValueGenerator.randomObject();
        
        Kazkas[] itemArray = {item1, item2, item3, item4, item5, item6};
        
        Ks.oun("Įrašų Aibė:");
        BstSetKTUx<Kazkas> Set = new BstSetKTUx(new Kazkas());
        
        Collections.shuffle(Arrays.asList(itemArray));

        for (Kazkas a : itemArray) {
            Set.add(a);
            Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + Set.size());
        }
        Kazkas record8 = ValueGenerator.randomObject();
        Set.add(record8);
        Ks.oun(Set.toVisualizedString(""));
            SortedSetADTx<Kazkas> autoAibeKopija
                = (SortedSetADTx<Kazkas>) Set.clone();
        Ks.oun("Kopija: ");
        Ks.oun(autoAibeKopija);
        item5.setPrice(66.66);
        Ks.oun(autoAibeKopija);
        Ks.oun(Set);
     //
        BstSetKTUx<Kazkas> test = new BstSetKTUx(new Kazkas());
        test.add(item1);
        test.add(item5);
        test.add(ValueGenerator.randomObject());
        
        Ks.oun(Set.toVisualizedString(""));
        Ks.oun(item4);


        Ks.oun("floor test: ");
        Ks.oun(Set.floor(item4));
        Ks.oun("----------------");
        Ks.oun(test);
        Ks.oun(Set);

        Ks.oun("containsAll (f) test: ");
        Ks.oun(test.containsAll(Set));
        Ks.oun("----------------");
       
        Ks.oun("containsAll (t)test: ");
        Ks.oun(Set.containsAll(Set));
        Ks.oun("----------------");

        Ks.oun(Set.toVisualizedString(""));
        Ks.oun(item5);
        Ks.oun("----------------");
        
        Ks.oun("headset test: ");
        Ks.oun(Set.headSet(item3, true));
        
        Ks.oun("higher test: ");
        Ks.oun(test.higher(item3));
        Ks.oun("----------------");
        
        Ks.oun("pollLast test: ");
        Ks.oun(test.pollLast());
        Ks.oun("----------------");

    }
    
  

     public static void main(String[] args) throws CloneNotSupportedException{
        Locale.setDefault(Locale.US);
        Testing();
        
    }

}
