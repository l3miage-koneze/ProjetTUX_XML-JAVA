/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;


/**
 *
 * @author Zeinabou KONE  - Sicong XU
 */

import java.util.ArrayList;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Dico {
    //LES ATTRIBUTS 
    
    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String cheminFichierDico;
    
    //CONSTRUCTEUR
    
    public Dico(String cheminFichierDico){
        
        this.cheminFichierDico = cheminFichierDico;
        this.listeNiveau1 = new ArrayList<String>();
        this.listeNiveau2 = new ArrayList<String>();
        this.listeNiveau3 = new ArrayList<String>();
        this.listeNiveau4 = new ArrayList<String>();
        this.listeNiveau5 = new ArrayList<String>();

    }
    
    
    
    /**
    * Méthode qui retourne un mot depuis une liste de mots
    *@return
    */
    private String getMotDepuisListe(ArrayList<String> list){
        
        //variable qui prends l'indice 'un mot de la liste
        int result;

        //on choisit un indice de façon aléatoire dans la liste
        result = (int) (Math.random()*list.size());

        //retourne le mot qui est a l'indice result
        return list.get(result);
    }
    
     
    /**
     *Méthode qui renvoie un mot de la liste de niveau @param niveau
     * @param niveau
     * @return
     */
    
    public String getMotDepuisListeNiveaux(int niveau){
        
        String mot = new String();
        
        switch(verifieNiveau(niveau)){
            case 1:
                mot = getMotDepuisListe(this.listeNiveau1);
                break;
            case 2 :
                mot = getMotDepuisListe(this.listeNiveau2);
                break;
            case 3 : 
                mot = getMotDepuisListe(this.listeNiveau3);
                break;
            case 4 : 
                mot = getMotDepuisListe(this.listeNiveau4);
                break;
            case 5 :
                mot = getMotDepuisListe(this.listeNiveau5);
                break;
            default :
                System.out.println("Impossible de récupérer ce mot :(");
                break;
        }
        
        return mot;
    }
    
    
    /**
     *Méthode qui permet d'ajouter un mot à une liste de mot en fonction de son niveau
     * @param niveau
     * @param mot
     * @void 
     */
    public void ajouteMotADico(int niveau, String mot){
      
        switch(niveau){
            case 1 :
                this.listeNiveau1.add(mot);
                break;
            case 2 :
                this.listeNiveau2.add(mot);
                break;
            case 3 :
                this.listeNiveau3.add(mot);
                break;
            case 4 : 
                this.listeNiveau4.add(mot);
                break;
            case 5 :
                this.listeNiveau5.add(mot);
                break;
            default : 
                System.out.println("Ce mot ne peut pas être ajouté :(");
                break;
        }
      
    }
    
    
 
    /**
     *Getter de l'attribut @cheminFichierDico
     * @return 
     */
    public String getCheminFichierDico(){
        return this.cheminFichierDico;
    }
    
    
    /**
     *Méthode qui permet de vérifier le niveau fourni
     * @return le niveau s'il est compris dans l'intervalle [1,5], -1 sinon
     */
    private int verifieNiveau(int niveau){
        int niv;
        if(niveau>=1 && niveau<=5){
            niv = niveau;
        }else{
            niv = -1;
        }
        return niv;
    }
    
    
    /**
     *Méthode qui charge les mots du dictionnaire dans la liste de mots
     */
    public void lireDictionnaireDOM(String path, String filename) throws SAXException, IOException {
        //initialise un dom
        DOMParser parser;
        Document doc;

        try {

            parser = new DOMParser();
            
            //parse avec le path(chemin) et le filename(nom du fichier)
            parser.parse(path + filename);
            doc = parser.getDocument();

            //création de la nodeList mot
            NodeList motNL = doc.getElementsByTagName("mot");

            //parcours les éléments de la nodeList mot
            for (int i = 0; i < motNL.getLength(); i++) {
                //si le noeud du fils courant est un element
            	Node node = motNL.item(i);
            	if(node.getNodeType() == Node.ELEMENT_NODE) {
                    
                    //on récupère la valeur du niveau de ce fils courant et en fonction de celle-ci, on ajoute le mot doc si possible
                    char rapport = ((Element) node).getAttribute("niveau").charAt(0);
                    switch(rapport){
                        
                        case '1':
                            this.ajouteMotADico(1,node.getTextContent().toLowerCase());
                            break;
                            
                        case '2':
                            this.ajouteMotADico(2,node.getTextContent().toLowerCase());
                            break;
                            
                        case '3':
                            this.ajouteMotADico(3,node.getTextContent().toLowerCase());
                            break;
                            
                        case '4':
                            this.ajouteMotADico(4,node.getTextContent().toLowerCase());
                            break;
                            
                        case '5':
                            this.ajouteMotADico(5,node.getTextContent().toLowerCase());
                            break;
                    }   
                }    
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    
        
    }
    
    

}
