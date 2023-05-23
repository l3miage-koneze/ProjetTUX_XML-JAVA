/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import env3d.advanced.EnvNode;




/**
 *
 * @author Zeinabou KONE  - Sicong XU
 */

public class Letter extends EnvNode{
    
    //ATTRIBUT
    private char letter;
    
    
    //CONSTRUCTEUR
    public Letter(char l, double x, double y){
        
        this.letter = l;
        setScale(5.0);
        setX(x);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(y); // positionnement au milieu de la profondeur de la room
        
        //Textures pour les lettres spéciales
        switch(l){
            case ' ':
            case '-':
                setTexture("models/letter/cube.png");
                break;
            case 'é':
            case 'è':
            case 'ê':
            case 'ë':
                setTexture("models/letter/e.png");
                break;
            case 'à':
            case 'â':
                setTexture("models/letter/a.png");
                break;
            case 'ç':
                setTexture("models/letter/c.png");
                break;
            case 'ï':
            case 'î':
                setTexture("models/letter/i.png");
                break;
            case 'ô':
            case 'ö': 
                setTexture("models/letter/o.png");
                break;
            case 'ù':
            case 'ü':
            case 'û':
                setTexture("models/letter/u.png");
                break;
            default:
                setTexture("models/letter/"+letter+".png");
        
        }
        setModel("models/letter/cube.obj");
        
    }
    
    /**
     *Getter de l'attribut letter
     * @return 
     */
    public char getLetter(){
        return letter;
    }
}
