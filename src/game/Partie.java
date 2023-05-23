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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class Partie {
    
    //ATTRIBUTS
    private String date;
    private String mot;
    private int niveau;
    private int trouvé;
    private int temps;
    
    //CONSTRUCTEUR 1
    public Partie(String date, String mot, int niveau){
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
        this.trouvé = 0;
        this.temps = 0;
    }
    
    
    //CONSTRUCTEUR 2
    public Partie(Element partieElt){
        
        //on récupère l'élément mot
        Element motElt = (Element)partieElt.getElementsByTagName("mot").item(0);
        
        //on récupère la valeur des éléments
        mot = partieElt.getElementsByTagName("mot").item(0).getTextContent();
        niveau = Integer.parseInt(motElt.getAttribute("niveau"));
        temps  = (int) Integer.parseInt(partieElt.getElementsByTagName("temps").item(0).getTextContent());
        trouvé = Integer.parseInt(partieElt.getAttribute("trouvé"));
        date   = Profil.xmlDateToProfileDate(partieElt.getAttribute("date"));
    }
    
    
    
    /**
     * Méthode qui renvoie l'élement partie d'une partie
     * 
     * @param doc
     * @return 
     */
    public Element getPartie(Document doc){
        
        //création de l'élément partie 
        Element partieElt = doc.createElement("partie");
        partieElt.setAttribute("trouvé", ""+ trouvé);
        partieElt.setAttribute("date", Profil.profileDateToXmlDate(date));

        //création de l'élément temps
        Element tempsElt = doc.createElement("temps");
        tempsElt.setTextContent(""+ temps);
        partieElt.appendChild(tempsElt);
        
        //création de l'élément mot
        Element motElt = doc.createElement("mot");
        motElt.setTextContent("" + mot);
        partieElt.appendChild(motElt);
        motElt.setAttribute("niveau", ""+niveau);
        
        
        return partieElt;

    }
    
   
    
    //SETTERS
    public void setTrouve(int nbLettresRestantes){
        trouvé = 100 - (nbLettresRestantes * 100)/mot.length();
    }
    
    public void setTemps(int temps){
        this.temps=temps;
    }
    
    //GETTERS
    public int getNiveau(){
        return this.niveau;
    }
    

    public String getMot(){
        return this.mot;
    }
    
    public int getTemps(){
        return this.temps;
    }
    
    
    @Override
    public String toString(){
        return String.format("Partie du %s : Tux a trouvé %d%% du mot %s en %d secondes", date, trouvé, mot, temps);
    }
}
