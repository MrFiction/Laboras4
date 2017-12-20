package lab4;

import java.util.HashSet;
import laborai.studijosktu.HashType;
import laborai.studijosktu.MapKTUx;
import laborai.gui.MyException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * @author eimutis
 */
public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finishCommand";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private static final String[] TYRIMU_VARDAI = {"removeMapKTUOA", "removeHashSet", "containsMapKTUOA", "containsHashSet"};
    private final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private MapKTUOA<Integer, Kazkas> aSeries;
    private final HashSet<Kazkas> aSeries2 = new HashSet<>();
    private final Queue<String> chainsSizes = new LinkedList<>();

    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void SisteminisTyrimas() throws InterruptedException {
        try {
            //chainsSizes.add(MESSAGES.getString("msg4"));
            //chainsSizes.add("   kiekis      " + TYRIMU_VARDAI[0] + "   " + TYRIMU_VARDAI[1]);
            for (int k : TIRIAMI_KIEKIAI) {
                aSeries = new MapKTUOA<>(k);
                Kazkas[] values = ValueGenerator.generate(k);
                for (Kazkas a : values) {
                    aSeries.put(a.getSerialNumber(), a);
                }
                for (Kazkas a : values) {
                    aSeries2.add(a);
                }
                //aSeries.clear();
                //aSeries2.clear();
                tk.startAfterPause();
                tk.start();
                for (Kazkas a : values) {
                    aSeries.remove(a.getSerialNumber());
                }
                tk.finish(TYRIMU_VARDAI[0]);
                for (Kazkas a : values) {
                    aSeries2.remove(a);
                }
                tk.finish(TYRIMU_VARDAI[1]);
                //---------------------------
                    
                for (Kazkas a : values) {
                    aSeries.contains(a.getSerialNumber());
                    
                }
                tk.finish(TYRIMU_VARDAI[2]);
                for (Kazkas a : values) {
                    aSeries2.contains(a);
                    
                }
                tk.finish(TYRIMU_VARDAI[3]);
                tk.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.stream().forEach(p -> sb.append(p).append(System.lineSeparator()));
            tk.logResult(sb.toString());
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            tk.logResult(e.getMessage());
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
