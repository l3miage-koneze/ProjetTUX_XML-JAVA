/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author Zeinabou KONE  - Sicong XU
 */


public class Chronometre {
    private long begin;
    private long end;
    private long current;
    private final int limite;

    public Chronometre(int limite) {
        //intialisation
        this.begin=0;
        this.end=0;
        this.current=0;
        this.limite = limite;
    }
    
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    public long getTime() {
        return end-begin;
    }
 
    public long getMilliseconds() {
        return end-begin;
    }
 
    public int getSeconds() {
        return (int) ((end - begin) / 1000.0);
    }
 
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
    
    /**
    * Methode to know if it remains time
     * @return 
    */
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        int tempsEcoule;
        tempsEcoule = (int) ((current)/1000.0);
        return (tempsEcoule < limite);
    }
     
}

