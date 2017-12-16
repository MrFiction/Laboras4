package lab4;

import java.util.HashSet;
import laborai.gui.MyException;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finish";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");

    private static final String[] TYRIMU_VARDAI = {"addTreeSet", "addHashSet", "containsTreeSet", "containsHashSet"};
    private static final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;
    private final String[] errors;

    private final TreeSet<Integer> aSeries = new TreeSet<>();
    private final HashSet<Integer> aSeries2 = new HashSet<>();
    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
        errors = new String[]{
            MESSAGES.getString("error1"),
            MESSAGES.getString("error2"),
            MESSAGES.getString("error3"),
            MESSAGES.getString("error4")
        };
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
            int[] testn = new int[1000];
            for (int k : TIRIAMI_KIEKIAI) {
                //Automobilis[] autoMas = AutoGamyba.generuotiIrIsmaisyti(k, 1.0);
                int[] values = ValueGenerator.generateInt(k);
                for (int i = 0; i < 1000; i++) {
                    testn[i] = ValueGenerator.randomSerialNumber();
                }
                aSeries.clear();
                aSeries2.clear();
                tk.startAfterPause();
                tk.start();
                for (int a : values) {
                    aSeries.add(a);
                }
                tk.finish(TYRIMU_VARDAI[0]);
                for (int a : values) {
                    aSeries2.add(a);
                }
                tk.finish(TYRIMU_VARDAI[1]);
                //---------------------------
                    
                for (int i = 0; i < 1000; i++) {
                    aSeries.contains(testn[i]);
                    tk.finish(TYRIMU_VARDAI[2]);
                }
                for (int i = 0; i < 1000; i++) {
                    aSeries.contains(testn[i]);
                    tk.finish(TYRIMU_VARDAI[3]);
                }
               
                tk.seriesFinish();
            
            }
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                tk.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                tk.logResult(MESSAGES.getString("msg3"));
            } else {
                tk.logResult(e.getMessage());
            }
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
