package middleware;

import guis.Aplicacion1GUI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Josue
 */
public class Aplicacion1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExecutorService executor = Executors.newCachedThreadPool();
        Aplicacion1GUI a1 = new Aplicacion1GUI();
        executor.execute(a1);
    }
    
}
