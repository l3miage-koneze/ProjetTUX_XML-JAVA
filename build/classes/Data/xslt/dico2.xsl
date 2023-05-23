<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dico2.xsl
    Created on : 25 octobre 2022, 17:47
    Authors     : Zeinabou Bissi KONE - Sicong XU
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:tux="http://myGame/tux">
    
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    
    <!--....................Cette template va trier les mots du dictionnaire par ordre croissant du niveau et par ordre alphabétique.........-->
    
    <xsl:template match="/">
        <html>
            <head>
                <title>trie dictionnaire</title>
                <link rel="stylesheet" href="../css/dico.css"/>
            </head>
            <body>
                <h1> CLASSEMENT PAR NIVEAU ET ORDRE ALPHABETIQUE DES MOTS DU DICTIONNAIRE</h1>
                <div class="container">
                    
                    
                        <!--.....Mots du niveau 1 triés par ordre alphabétique..........-->
                        
                        <div id="item1">
                            <i>Niveau 1:</i><br/>
                            <xsl:apply-templates select="//tux:mot[@niveau='1']"><br/>
                            <xsl:sort select="."/>
                            </xsl:apply-templates><br/>
                        </div>


                        <!--.....Mots du niveau 2 triés par ordre alphabétique..........-->
                        
                        <div id="item2">
                            <i>Niveau 2:</i><br/>
                            <xsl:apply-templates select="//tux:mot[@niveau='2']"><br/>
                            <xsl:sort select="."/>
                            </xsl:apply-templates><br/>
                        </div>
                        
                        
                        <!--.....Mots du niveau 3 triés par ordre alphabétique..........-->

                        <div id="item3">
                            <i>Niveau 3:</i><br/>
                            <xsl:apply-templates select="//tux:mot[@niveau='3']"><br/>
                            <xsl:sort select="."/>
                            </xsl:apply-templates><br/>
                        </div>
                        
                        
                        
                        <!--.....Mots du niveau 4 triés par ordre alphabétique..........-->
                        
                        <div id="item4">
                            <i>Niveau 4:</i><br/>
                            <xsl:apply-templates select="//tux:mot[@niveau='4']"><br/>
                            <xsl:sort select="."/>
                            </xsl:apply-templates><br/>
                        </div>
                        
                        
                        <!--.....Mots du niveau 5 triés par ordre alphabétique..........-->
                        
                        <div id="item5">
                            <i>Niveau 5:</i><br/>
                            <xsl:apply-templates select="//tux:mot[@niveau='5']"><br/>
                            <xsl:sort select="."/>
                            </xsl:apply-templates><br/>
                        </div>
                </div>
            </body>
        </html>
    </xsl:template>


  
    
     <!--....... Template qui récupère les mots de mon dictionnaire...... -->
     
    <xsl:template match="tux:mot">
        <xsl:value-of select="."/><br/>
    </xsl:template>
    
</xsl:stylesheet>
