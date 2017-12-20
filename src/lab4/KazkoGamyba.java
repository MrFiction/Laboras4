package lab4;

import laborai.gui.MyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class KazkoGamyba {

    private static final String ID_CODE = "TA";      //  ***** nauja
    private static int serNr = 10000;               //  ***** nauja

    private Kazkas[] kazkas;
    private String[] raktai;
    private int kiekis = 0, idKiekis = 0;

    public static Kazkas[] gamintiKazka(int kiekis) {
        Kazkas[] knygos = IntStream.range(0, kiekis)
                .mapToObj(i -> ValueGenerator.randomObject())
                .toArray(Kazkas[]::new);
        Collections.shuffle(Arrays.asList(knygos));
        return knygos;
    }

    public static String[] gamintiKazkoID(int kiekis) {
        String[] raktai = IntStream.range(0, kiekis)
                .mapToObj(i -> ID_CODE + (serNr++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(raktai));
        return raktai;
    }

    public Kazkas[] gamintiIrParduotiKazka(int aibesDydis,
            int aibesImtis) throws MyException {
        if (aibesImtis > aibesDydis) {
            aibesImtis = aibesDydis;
        }
        kazkas = gamintiKazka(aibesDydis);
        raktai = gamintiKazkoID(aibesDydis);
        this.kiekis = aibesImtis;
        return Arrays.copyOf(kazkas, aibesImtis);
    }

    // Imamas po vienas elementas iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojama
    // nuosava situacija ir išmetamas pranešimas.
    public Kazkas parduotiKazka() {
        if (kazkas == null) {
            throw new MyException("carsNotGenerated");
        }
        if (kiekis < kazkas.length) {
            return kazkas[kiekis++];
        } else {
            throw new MyException("allSetStoredToMap");
        }
    }

    public String gautiIsBazesKazkaId() {
        if (raktai == null) {
            throw new MyException("carsIdsNotGenerated");
        }
        if (idKiekis >= raktai.length) {
            idKiekis = 0;
        }
        return raktai[idKiekis++];
    }
}