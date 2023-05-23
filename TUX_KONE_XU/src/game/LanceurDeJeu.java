/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;



/**
 *
 * @author Zeinabou KONE  - Sicong XU
 */

import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author zeinabou
 */
public class LanceurDeJeu {
    public static void main (String args[]) throws SAXException, IOException{
       // Declare un Jeu
        JeuDevineLeMotOrdre jeu;
        
        //Instancie un nouveau jeu
        jeu= new JeuDevineLeMotOrdre();
        
        //Execute le jeu
        jeu.execute();
    }
   
    
}
