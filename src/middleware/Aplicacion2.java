package middleware;

import guis.Aplicacion2GUI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Josue
 */
public class Aplicacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExecutorService executor = Executors.newCachedThreadPool();
        Aplicacion2GUI a2 = new Aplicacion2GUI();
        executor.execute(a2);
    }
    
}
