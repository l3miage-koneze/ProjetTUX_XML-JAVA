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

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import xml.XMLUtil;


public class Profil {
    
    public Document _doc;
    
    private ArrayList<Partie> parties;
    private String nom;
    private String avatar;
    private String dateNaissance;
    
    
    //Constructeur par défaut 
    public Profil(){}
    
    
    //Constructeur avec deux paramètres
    public Profil(String nom, String dateNaissance){
        parties= new ArrayList<Partie>();
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar="avatar.png";
    
    }
    
    
    public Profil(String nomFichier) {
        
        //créé le DOM à partir du fichier nomFichier
        _doc = fromXML(nomFichier);
        
        // on récupère l'élément profil
        Element profil = _doc.getDocumentElement();
        
        //on récupère les éléments nom, avatar et dateNaissance
        nom = profil.getElementsByTagName("nom").item(0).getTextContent();
        avatar = profil.getElementsByTagName("avatar").item(0).getTextContent();
        dateNaissance = xmlDateToProfileDate(profil.getElementsByTagName("anniversaire").item(0).getTextContent());
        
        //on récupère l'élément parties
        Element partiesElt = (Element) profil.getElementsByTagName("parties").item(0);
        
        //on crée une liste de noeuds partie
        NodeList partieElt = partiesElt.getElementsByTagName("partie");
        
        //pour toute nouvelle partie, on l'ajoute au profil
        for(int i = 0; i < partieElt.getLength(); i++){
            ajouterPartie(new Partie((Element)partieElt.item(i)));
        }
    }
    
    
    // Sauvegarde un DOM en XML
    private void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(_doc, nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     // Cree un DOM à partir d'un fichier XML
    private Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //permet de rajouter à la liste de parties une partie
    public void ajouterPartie(Partie p){
         this.parties.add(p);
    }
    
    //retourne le dernier niveau de la partie 
     public int getDernierNiveau(){
        int niveau = 1; //niveau par défaut
        
        //on récupère le dernier niveau
        if(!(parties.isEmpty())){
            niveau = parties.get(parties.size()-1).getNiveau();
        }
        
        return niveau;
    }

     
    //on charge le profil du joueur
    boolean charge(String nomJoueur) {
        boolean verifie = false;
        if(nomJoueur.equals(this.nom)){
            verifie = true;
        }
        
        return verifie;
    }
    
    
    
    
    /**
     *permet de sauvegarder le document DOM dans un fichier XML
     * @param nomFichier
     */
    
    public void sauvegarder(String nomFichier) {
        
        //repétoire où se trouvera le profil du joueur (ici xml) 
        String xmlFile ="src/Data/xml/";
        
        //Analyse le document et créer l'arbre DOM
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
        _doc = db.newDocument();
        
        //création des elements à mettre dans le fichier XML
        
        //Création de l'élément profil
        Element elmtProfil=_doc.createElement("profil");
        _doc.appendChild(elmtProfil);
        
        //Création de l'élément nom
        Element elmtNom=_doc.createElement("nom");
        elmtNom.setTextContent(this.nom);
        elmtProfil.appendChild(elmtNom);
        
        //création de l'élément avatar
        Element elmtAvatar=_doc.createElement("avatar");
        elmtAvatar.setTextContent(this.avatar);
        elmtProfil.appendChild(elmtAvatar);
        
        //création de l'élément dateNaissance
        Element elmtAnniversaire=_doc.createElement("dateNaissance");
        elmtAnniversaire.setTextContent(profileDateToXmlDate(this.dateNaissance));
        elmtProfil.appendChild(elmtAnniversaire);
        
        //création de l'élément parties
        Element elmtParties=_doc.createElement("parties");
        elmtProfil.appendChild(elmtParties);
        
        
        //ajout des éléments partie qui seront jouées 
        for(Partie p: parties)
        {
            elmtParties.appendChild(p.getPartie(_doc));
        }
        
        
        //sauvegarder le ficher xml
        toXML(xmlFile + "/" + this.nom + ".xml");
        }
        catch(ParserConfigurationException ex)
        {
            ex.printStackTrace();
        }
        
               
    }
 
    
    
    
    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }
    
    
    /// Takes a date in profile format: dd/mm/yyyy and returns a date
    /// in XML format (i.e. ????-??-??)
    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }

    @Override
    public String toString(){
        String resume = "";
        resume += "le profil du joueur est : " + nom +", né(e) le "+dateNaissance+ " avec l'avatar " + avatar +"\n";

        return resume;
    }

}
