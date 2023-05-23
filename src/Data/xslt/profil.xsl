<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : profil.xsl
    Created on : 1 novembre 2022, 16:55
    Authors     : Zeinabou Bissi KONE - Sicong XU
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:tux="http://myGame/tux">
    
    <xsl:output method="html"/>

    
    
    
 <!--.................Template qui affiche la page profil du joueur........................-->    
 
    <xsl:template match="/">
        <html>
            <head>
                <title>profil du joueur</title>
                <link rel="stylesheet" href="../css/profil.css"/>
            </head>
            <body>
                <div id="header">
                    <div id="titre">
                        
                        <span>----------------------------------------------------------------------------------------------------------------------------</span>
                           <h1>Page de profil du joueur </h1>                                                                                         
                        <span>----------------------------------------------------------------------------------------------------------------------------</span>
                    </div>
                    <h2>
                        <p><i id="decoration">Nom du joueur</i> : <xsl:value-of select="//tux:nom"/><br/></p>
                        <p><i id="decoration">Avatar du joueur</i> : <xsl:value-of select="//tux:avatar"/><br/></p>
                        <p><i id="decoration">Anniversaire du joueur</i> : <xsl:value-of select="//tux:anniversaire"/><br/></p>
                    </h2>
                </div>
                <br/>
                
                
                <p id="paragraph">
                    Ce joueur a joué  <xsl:value-of select="count(//tux:partie)"/> parties au total ! <br/>
                    Les tableaux suivants récapitulent ces parties :
                </p>
                
                <div id="image"/>
                
                  <!--..................ICI, on applique toutes les templates................-->
                <div id="partieContainer">
                    <xsl:apply-templates select="//tux:partie"/>
                </div>
                          
            </body>
        </html>
    </xsl:template>
    
    
    
    
    <!--................ICI, on déclare la template qui s'applique sur l'élément parties.........................-->
    <!--....Elle va crée un tableau recapitulatif pour chaque élément partie de parties..........................-->
    
    <xsl:template match="tux:partie">
        
        <table border="1" class="center">
            
            <!--................Ligne d'en-tête du tableau.................-->
            
            <p>--------------------------------------------------------------------------------------------------------</p>

      
            <tr>
                <br/><h2> Partie N°<xsl:value-of select="position()"/></h2><br/>
            </tr>
            
           <!--.................Première ligne du tableau.................--> 
            
            <tr>
                <th>Date</th>
                <th>Temps</th>
                <th>Mot</th>
            </tr>
            
            <!--..........Lignes suivantes du tableau...................-->
            
            <tr>
                <td>
                    <xsl:value-of select="@date"/>
                </td>
                <td>
                    <xsl:value-of select="tux:temps/text()"/>
                </td>
                <td>
                    <xsl:value-of select="tux:mot/text()"/> (niveau <xsl:value-of select="tux:mot/@niveau"/>)
                </td>
            </tr>
            
            
        </table>
    </xsl:template>

</xsl:stylesheet>
