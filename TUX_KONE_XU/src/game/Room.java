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

public class Room {
    
    //LES ATTRIBUTS 
    private int depth;
    private int height;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureTop;
    private String textureSouth;
    
    
    //CONSTRUCTEUR 
    public Room(){
        this.depth = 100;
        this.height = 60;
        this.width = 100;
        this.textureBottom = "textures/skybox/fantasy/bottom.png";
        this.textureNorth = "textures/skybox/fantasy/north.png";
        this.textureEast = "textures/skybox/fantasy/east.png";
        this.textureWest = "textures/skybox/fantasy/west.png";
    }
    
    
    //GETTERS 
    public int getDepth() {
        return depth;
    }
    
    
    public int getHeight() {
        return height;
    }
     
    public int getWidth() {
        return width;
    }
    
    public String getTextureBottom() {
        return textureBottom;
    }
    
    public String getTextureNorth() {
        return textureNorth;
    }
    
    public String getTextureEast() {
        return textureEast;
    }
    
    public String getTextureWest() {
        return textureWest;
    }
    
    public String getTextureTop() {
        return textureTop;
    }
    
    public String getTextureSouth() {
        return textureSouth;
    }
    
    
    //SETTERS
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTextureBottom(String textureBottom) {
        this.textureBottom = textureBottom;
    }

    public void setTextureNorth(String textureNorth) {
        this.textureNorth = textureNorth;
    }

    
    public void setTextureEast(String textureEast) {
        this.textureEast = textureEast;
    }

    public void setTextureWest(String textureWest) {
        this.textureWest = textureWest;
    }

    public void setTextureTop(String textureTop) {
        this.textureTop = textureTop;
    }
    
    public void setTextureSouth(String textureSouth) {
        this.textureSouth = textureSouth;
    }
    
    
}
