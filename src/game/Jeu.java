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

import env3d.Env;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.lwjgl.input.Keyboard;
import org.xml.sax.SAXException;



public abstract class Jeu {

    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }

    /**
     *LES ATTRIBUTS
     */
    protected final Env env;
    private Tux tux;
    private final Room mainRoom;
    private final Room menuRoom;
    private Letter letter;
    ArrayList<Letter> lettres;
    private Profil profil;
    private final Dico dico;
    protected EnvTextMap menuText;  //text (affichage des texte du jeu)
    
    
    
    public Jeu() throws SAXException, IOException {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        mainRoom = new Room();

        // Instancie une autre Room pour les menus
        menuRoom = new Room();
        menuRoom.setTextureEast("textures/top.png");
        menuRoom.setTextureWest("textures/top.png");
        menuRoom.setTextureNorth("textures/top.png");
        menuRoom.setTextureBottom("textures/top.png");

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(true);

        // Instancie un profil par défaut
        //profil = new Profil();
        
        // Dictionnaire
        dico= new Dico("src/Data/xml/dico.xml");
        dico.lireDictionnaireDOM("src/Data/xml/", "dico.xml");

        // instancie le menuText
        menuText = new EnvTextMap(env);

        //Instancie la liste de lettres
        lettres = new ArrayList<Letter>();
         
        
        // Textes affichés à l'écran
        menuText.addText("Voulez vous ?", "Question", 200, 300);
        menuText.addText("1. Commencer une nouvelle partie ?", "Jeu1", 250, 280);
        menuText.addText("2. Charger une partie existante ?", "Jeu2", 250, 260);
        menuText.addText("3. Sortir de ce jeu ?", "Jeu3", 250, 240);
        menuText.addText("4. Quitter le jeu ?", "Jeu4", 250, 220);
        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);
        //menuText.addText("Saisissez l'avatar de votre choix : ", "Avatar", 200, 300);
        menuText.addText("1. Charger un profil de joueur existant ?", "Principal1", 250, 280);
        menuText.addText("2. Créer un nouveau joueur ?", "Principal2", 250, 260);
        menuText.addText("3. Sortir du jeu ?", "Principal3", 250, 240);
        menuText.addText("Choisissez un niveau 1 - 2 - 3 - 4 - 5 : ", "Niveau", 200, 300);
        menuText.addText("Trouve ce mot : ", "Mot à trouver", 250, 300);
        
    }

    /**
     * Gère le menu principal
     *
     */
     public void execute() {

        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        this.env.setDisplayStr("Au revoir !", 300, 30);
        env.exit();
    }

    
    

    // fourni
    private String getNomJoueur() {
        String nomJoueur = "";
        menuText.getText("NomJoueur").display();
        nomJoueur = menuText.getText("NomJoueur").lire(true);
        menuText.getText("NomJoueur").clean();
        return nomJoueur;
    }
    
    
    /*private String getAvatar() {
        String avatar = "";
        menuText.getText("Avatar").display();
        avatar = menuText.getText("Avatar").lire(true);
        menuText.getText("Avatar").clean();
        return avatar;
    }*/
    
    /*private String getBirthDay() {
        String birthDay = "";
        menuText.getText("DateNaissance").display();
        birthDay = menuText.getText("DateNaissance").lire(true);
        menuText.getText("DateNaissance").clean();
        return birthDay;
    }*/

    /**
     *Méthode qui permet à l'utilisateur de saisir le niveau du mot qu'il veut deviner. 
     * S'il ne saisit pas un niveau valide, le tux jouera un mot du niveau 1 par défaut
     */
    private int getNiveau(){
        int niveau=1;
        menuText.getText("Niveau").display();
        try{
            niveau = Integer.parseInt(menuText.getText("Niveau").lire(true));
            
            if(niveau<1 || niveau>5){
                niveau=1;
            }
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        menuText.getText("Niveau").clean();
        env.advanceOneFrame();
        return niveau;
    }
    
    
     /**
     * Méthode qui permet d'afficher pendant 5 secondes le mot à deviner dans l'environnement
     * @param mot : le mot à deviner
     */
    private void afficherMotATrouver(String mot){
        menuText.getText("Mot à trouver").addTextAndDisplay("", mot);
        env.advanceOneFrame();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            menuText.getText("Mot à trouver").clean();
            env.advanceOneFrame();
        }
    }
    
    
    // fourni, à compléter
    
    //Menu du joueur
    private MENU_VAL menuJeu(String nomJoueur) {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
                
                //restaure la room du menu
                env.setRoom(menuRoom);
                
                // affiche menu
                menuText.getText("Question").display();
                menuText.getText("Jeu1").display();
                menuText.getText("Jeu2").display();
                menuText.getText("Jeu3").display();
                menuText.getText("Jeu4").display();
                
                // vérifie qu'une touche 1, 2, 3 ou 4 est pressée : il faut que l'utilisateur clique sur une touche pour avancer
                int touche = 0;
                while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4)) {
                    touche = env.getKey();
                    env.advanceOneFrame();
                }
                
                // nettoie l'environnement du texte
                menuText.getText("Question").clean();
                menuText.getText("Jeu1").clean();
                menuText.getText("Jeu2").clean();
                menuText.getText("Jeu3").clean();
                menuText.getText("Jeu4").clean();
                
                // restaure la room du jeu
                env.setRoom(mainRoom);
                
                // et décide quoi faire en fonction de la touche pressée
                switch (touche) {
                    // -----------------------------------------
                    // Touche 1 : Commencer une nouvelle partie
                    // -----------------------------------------
                    case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                        // .......... dico.******
                        
                        //on initialise (change) la liste des lettres
                        lettres = new ArrayList<Letter>();
                        
                         //on choisit un niveau
                        int niveau = getNiveau(); 
                        
                        //on récupère un mot du niveau choisi dans le dico
                        String mot = dico.getMotDepuisListeNiveaux(niveau);   
                        
                        //on affcihe le mot à deviner dans la console
                        System.out.println(mot);    
                        
                        //on formate la date 
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        
                        //on affiche le mot à trouver dans l'environnement pendant 5 secondes
                        afficherMotATrouver(mot); 
                        
                        //Ici, on s'occupe de la disposition des cubes (lettres) dans l'environnement 
                        Random r = new Random();    //on génère un nombre de façon aléatoire
                        for(char c : mot.toCharArray()){
                            letter = new Letter(c, r.nextInt(mainRoom.getWidth()-2)+1, r.nextInt(mainRoom.getDepth()-2)+1); //on choisit des coordonnées de dispositions en fonction du random
                            lettres.add(letter);    //on ajoute la lettre à l'arrayList
                            env.addObject(letter);  //on ajoute la lettre dans l'environnement
                        }
                        
                        
                        //on crée un nouvelle partie à la date courante avec le mot choisi
                        partie = new Partie(sdf.format(new Date()), mot, niveau);
                        
                        // on joue
                        joue(partie);
                        
                        // enregistre la partie dans le profil --> enregistre le profil
                        profil.sauvegarder("src/Data/xml/" + nomJoueur + ".xml");
                        
                        //on affiche un récapitulatif du profil du joueur dans la console
                        profil.toString();
                        System.out.println(profil);
                        
                        playTheGame = MENU_VAL.MENU_JOUE;
                        break;
                        
                        // -----------------------------------------
                        // Touche 2 : Charger une partie existante
                        // -----------------------------------------
                    case Keyboard.KEY_2: // charge une partie existante
                        
                        //on initialise la liste de lettres 
                        lettres = new ArrayList<Letter>();
                        
                        //on crée une partie par défaut
                        partie = new Partie("19/12/2022", "lyon", 2); 
                        
                        // Recupère le mot de la partie existante 
                        String word = partie.getMot();
                        
                        //ajoute toutes les lettres du mot dans l'environnement
                        Random l = new Random();
                        for(char c : word.toCharArray()){
                            letter = new Letter(c, l.nextInt(mainRoom.getWidth()-2)+1, l.nextInt(mainRoom.getDepth()-2)+1); //on choisit des coordonnées de dispositions en fonction du random
                            lettres.add(letter);    //on ajoute la lettre à l'arrayList
                            env.addObject(letter);      //on ajoute la lettre dans l'environnement
                        }
                        
                        // on joue la partie
                        joue(partie);
                        
                        //on enregistre la partie dans le profil --> enregistre le profil
                        profil.sauvegarder("src/Data/xml/" + nomJoueur + ".xml");
                        
                        //on affiche un récapitulatif du profil du joueur dans la console
                        profil.toString();
                        System.out.println(profil);
                        
                        playTheGame = MENU_VAL.MENU_JOUE;
                        break;
                        
                        // -----------------------------------------
                        // Touche 3 : Sortie de ce jeu
                        // -----------------------------------------
                    case Keyboard.KEY_3:
                        playTheGame = MENU_VAL.MENU_CONTINUE;
                        break;
                        
                        // -----------------------------------------
                        // Touche 4 : Quitter le jeu
                        // -----------------------------------------
                    case Keyboard.KEY_4:
                        playTheGame = MENU_VAL.MENU_SORTIE;
                }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        
        return playTheGame;
    }

    
    /*MENU PRINCIPAL DU JEU*/
    private MENU_VAL menuPrincipal() {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;
        //String dateNaissance;
        // restaure la room du menu
        env.setRoom(menuRoom);

        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("Principal3").display();
               
        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                
                // demande le nom du joueur existant
                nomJoueur = getNomJoueur();
                
                //demande la date de naissance du joueur
                //dateNaissance = getBirthDay();
                
                // charge le profil de ce joueur si possible, sinon, revient sur le menu principal
                profil = new Profil("src/Data/xml/" + nomJoueur + ".xml");
                if (profil.charge(nomJoueur)) {
                    choix = menuJeu(nomJoueur);
                } else {
                    choix = MENU_VAL.MENU_CONTINUE;//CONTINUE;
                }
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                
                //dateNaissance = getBirthDay();
                
                // crée un profil avec le nom d'un nouveau joueur et une date de naissance par défaut
                profil = new Profil(nomJoueur, "21/10/2001");
                choix = menuJeu(nomJoueur);
                break;

            // -------------------------------------
            // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_3:
                choix = MENU_VAL.MENU_SORTIE;
        }
        return choix;
    }

    
    public void joue(Partie partie) {

        // Instancie un Tux
        tux = new Tux(env, mainRoom);
        env.addObject(tux);


        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);

        // Boucle de jeu
        Boolean finished;
        finished = false;
        while (!finished) {

            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;
            }

            // Contrôles des déplacements de Tux (gauche, droite, ...)
            tux.deplacer();

            // Ici, on applique les regles
            appliqueRegles(partie);

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);
        
        //on ajoute la partie dans le fichier xml du profil du joueur
        profil.ajouterPartie(partie);

    }
    
    
    /**
     *Méthode qui calcule la distance entre le tux et une lettre dans l'environnement
     * @param letter
     * @return 
     */
     protected double distance(Letter letter) {
        double distanceTUXLettre = Math.sqrt(Math.pow(this.tux.getX() - letter.getX(), 2) + 
                                             Math.pow(this.tux.getY() - letter.getY(), 2) +
                                             Math.pow(this.tux.getZ() - letter.getZ(), 2));
        return distanceTUXLettre;
    }
    
    
     /**
      *Méthode qui vérifie si le tux est rentré en collision avec une lettre
     * @param letter
     * @return 
      */
    protected boolean collision(Letter letter) {
        boolean coll = false;
        if (distance(letter) < letter.getScale() + tux.getScale()) {
            coll = true;
            System.out.println("Bravo :) ! Tux a trouvé la lettre  " + letter.getLetter()+ "  Youhouuuuu :) !! ");

        }
        return coll;
    }
   
    
    /**
     *Méthode qui renvoie la liste de lettres
     * 
     * @return 
     */
    public ArrayList<Letter> getLettres() {
        return this.lettres;
    }

    
    //Méthodes abstraites
    
    protected abstract void démarrePartie(Partie partie);

    protected abstract void appliqueRegles(Partie partie);

    protected abstract void terminePartie(Partie partie);

}
