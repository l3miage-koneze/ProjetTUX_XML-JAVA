/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import game.Dico;

import java.io.IOException;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


public class TestDico {
    public static void main(String args[]) throws SAXException, IOException {
        
        Dico dictionnaire = new Dico("src/Data/xml/dico.xml");
        dictionnaire.lireDictionnaireDOM("src/Data/xml/", "dico.xml");
        
        System.out.println("Un mot de niveau 1 : "+dictionnaire.getMotDepuisListeNiveaux(1));
        System.out.println("Un mot de niveau 1 : "+dictionnaire.getMotDepuisListeNiveaux(1));
        System.out.println("Un mot de niveau 2 : "+dictionnaire.getMotDepuisListeNiveaux(2));
        System.out.println("Un mot de niveau 2 : "+dictionnaire.getMotDepuisListeNiveaux(2));
        System.out.println("Un mot de niveau 3 : "+dictionnaire.getMotDepuisListeNiveaux(3));
        System.out.println("Un mot de niveau 3 : "+dictionnaire.getMotDepuisListeNiveaux(3));
        System.out.println("Un mot de niveau 4 : "+dictionnaire.getMotDepuisListeNiveaux(4));
        System.out.println("Un mot de niveau 4 : "+dictionnaire.getMotDepuisListeNiveaux(4));
        System.out.println("Un mot de niveau 5 : "+dictionnaire.getMotDepuisListeNiveaux(5));
        System.out.println("Un mot de niveau 5 : "+dictionnaire.getMotDepuisListeNiveaux(5));
       
  
        
     
        
        /*String mot;
        Dico dico = new Dico(" ");
        dico.ajouteMotADico(1, "Pau");
        dico.ajouteMotADico(1, "Aix");
        dico.ajouteMotADico(1, "Gap");
        dico.ajouteMotADico(2, "Lyon");
        dico.ajouteMotADico(2, "Nice");
        dico.ajouteMotADico(2, "Metz");
        dico.ajouteMotADico(3, "Paris");
        dico.ajouteMotADico(3, "Lille");
        dico.ajouteMotADico(4, "Orléans");
        dico.ajouteMotADico(5, "Grenoble");
        dico.ajouteMotADico(4, "Pessac");
        dico.ajouteMotADico(5, "Marseille");
        
        for(int i=1; i<=5; i++){
            //for(int j=1; j<5; j++){
                mot = dico.getMotDepuisListeNiveaux(i);
                System.out.println("Mot :" + mot);
            
        }*/


    }
    
}
