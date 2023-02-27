/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package middleware;

import guis.Aplicacion3GUI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author xmnislas
 */
public class Aplicacion3 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExecutorService executor = Executors.newCachedThreadPool();
        Aplicacion3GUI a3 = new Aplicacion3GUI();
        executor.execute(a3);
    }
}
