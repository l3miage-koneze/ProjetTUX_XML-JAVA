/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;



/**
 *
 * @author Zeinabou KONE  - Sicong XU
 */

import java.io.IOException;
import org.xml.sax.SAXException;


public class JeuDevineLeMotOrdre extends Jeu {
    
    //ATTRIBUTS 
    private int nbLettresRestantes;
    private final Chronometre chrono;
    
    //CONSTRUCTEUR
    public JeuDevineLeMotOrdre() throws SAXException, IOException {
	super();
	nbLettresRestantes=0;
	chrono=new Chronometre(60000);
    }
    
    /**
     *Méthode qui retourne le nombre de lettres restantes
     * @return
     */
    private int getNblettresRestantes() {
	return this.nbLettresRestantes;
    }
	
    /**
     *Méthode qui retourne le chrono
     * @return
     */
    private int getTemps() {
	return (int) this.chrono.getTime();
    }
    
    
    /**
     *Méthode qui démarre une partie de jeu
     * 
     * @param partie
     */
    @Override
    protected void démarrePartie(Partie partie) {
        this.nbLettresRestantes=partie.getMot().length(); //getLettres().size();
        this.chrono.start();
        
    }

    
    /**
     *Méthode qui applique les règles de jeu sur une partie
     * 
     * @param partie
     */
    @Override
    protected void appliqueRegles(Partie partie){
        
        //Si toutes les lettres n'ont pas encore été trouvées et qu'il reste encore du temps
        if(nbLettresRestantes > 0){
            
        //si tux trouve une lettre
            if(tuxTrouveLettre()){
                
                //on décremente les lettres restants et la dite lettre disparaite de l'environnementS
                env.removeObject(getLettres().get(getLettres().size()-nbLettresRestantes));
                nbLettresRestantes--;
            }
        }
        
        
    }
    
    
    /**
     *Méthode qui met fin à la partie
     * @param partie
     */
    @Override
    protected void terminePartie(Partie partie) {
	chrono.stop();  
        partie.setTrouve(nbLettresRestantes);      
        partie.setTemps(chrono.getSeconds());  
        System.out.println(partie);
    }
    
    /**
     *Méthode qui renvoie vrai si tux a trouvé la lettre, faux sinon
     * @return 
     */
    private boolean tuxTrouveLettre(){
        return collision(getLettres().get(getLettres().size()- nbLettresRestantes));
    }
 
}
    