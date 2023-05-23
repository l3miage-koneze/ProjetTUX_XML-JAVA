<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dico.xsl
    Created on : 25 octobre 2022, 15:54
    Authors     : Zeinabou Bissi KONE - Sicong XU
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:ns1="http://myGame/tux">
    
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    
    <!--...... Ici, nous avons le template de la racine.................-->
    
    <xsl:template match="/">
        <html>
            <head>
                <title>dico.xsl</title>
                <link rel="stylesheet" href="../css/dico.css"/>
            </head>
            <body>
                <h1>Liste des mots du dictionnaire triés par ordre alphabétique</h1><br/><br/>
                <i>Il y'a exactement <xsl:value-of select="count(//ns1:mot)"/> mots dans le dictionnaire.</i><br/><br/>
                
                
                <!--.......Ici, on applique toutes les templates .....-->
                
                Le classement de ces noms de villes par ordre alphabétique nous donne : <br/>
                <hr/>
                <br/>
                <xsl:apply-templates select="//ns1:mot">
                    <xsl:sort select="." order="ascending"/>
                </xsl:apply-templates>
                <br/>
            </body>
        </html>
    </xsl:template>
    
    
    
    <!--...............ici, nous avons la template qui récupère les mots du dictionnaire...........-->
   
    <xsl:template match="ns1:mot">
             <xsl:value-of select="."/><br/>
    </xsl:template>

</xsl:stylesheet>
