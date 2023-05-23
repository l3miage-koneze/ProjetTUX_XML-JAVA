/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import env3d.advanced.EnvNode;
import env3d.Env;
import org.lwjgl.input.Keyboard;


/**
 *
 * @author Zeinabou KONE  - Sicong XU
 */


public class Tux extends EnvNode {
    
    //ATTRIBUTS 
    private Env env;
    private Room room; 
    
    
    //CONSTRUCTEUR 
    @SuppressWarnings("empty-statement")
    public Tux(Env env, Room room){
        this.env = env;
        this.room = room;
        setScale(9.0);
        setX(room.getWidth()/2);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(room.getHeight()/2); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/tux.png");
        setModel("models/tux/tux.obj"); 
    }
    
    /*Méthode déplace() :void
    *
    */
    public void deplacer() {
        
        //Nouvelle position (x,z)
        double newX = getX();
        double newZ = getZ();

        //Modification postition
        if (env.getKeyDown(Keyboard.KEY_U) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou U(pour Up)
            // Haut
            this.setRotateY(180);
            newZ = this.getZ() - 1.0;
        }
        if (env.getKeyDown(Keyboard.KEY_L) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou L(pour Left)
            // Gauche
            this.setRotateY(270);
            newX = this.getX() - 1.0;
        }
        if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'bas' ou D (pour Down)
            // Bas
            this.setRotateY(0);
            newZ = this.getZ() + 1.0;
        }
        if (env.getKeyDown(Keyboard.KEY_R) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'droite' ou R (pour Right)
            // Droite
            this.setRotateY(90);
            newX = this.getX() + 1.0;
        }

        //Vérifie la valididé de la postiton du tux
        if (testCollision(newX,newZ)){
            setX(newX);
            setZ(newZ);
        }

    }
    
    
    /**
     *Méthode qui teste qui le tux est rentré en collision avec les murs de la room
     * @param z
     * @return 
     * @param x
     */
    public boolean testCollision(double x,double z){
        return (((x > 2) && (x < room.getWidth() - 2)) && ((z > 2) && (z < room.getDepth() - 2)));
    }
    
}
