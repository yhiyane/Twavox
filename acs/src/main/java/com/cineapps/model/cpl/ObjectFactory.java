//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.08.19 à 03:39:19 PM CEST 
//


package com.cineapps.model.cpl;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cineapps.model.cpl2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cineapps.model.cpl2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CompositionPlaylist }
     * 
     */
    public CompositionPlaylist createCompositionPlaylist() {
        return new CompositionPlaylist();
    }

    /**
     * Create an instance of {@link CompositionPlaylist.ReelList }
     * 
     */
    public CompositionPlaylist.ReelList createCompositionPlaylistReelList() {
        return new CompositionPlaylist.ReelList();
    }

    /**
     * Create an instance of {@link CompositionPlaylist.ReelList.Reel }
     * 
     */
    public CompositionPlaylist.ReelList.Reel createCompositionPlaylistReelListReel() {
        return new CompositionPlaylist.ReelList.Reel();
    }

    /**
     * Create an instance of {@link CompositionPlaylist.ReelList.Reel.AssetList }
     * 
     */
    public CompositionPlaylist.ReelList.Reel.AssetList createCompositionPlaylistReelListReelAssetList() {
        return new CompositionPlaylist.ReelList.Reel.AssetList();
    }

    /**
     * Create an instance of {@link CompositionPlaylist.ReelList.Reel.AssetList.MainPicture }
     * 
     */
    public CompositionPlaylist.ReelList.Reel.AssetList.MainPicture createCompositionPlaylistReelListReelAssetListMainPicture() {
        return new CompositionPlaylist.ReelList.Reel.AssetList.MainPicture();
    }

    /**
     * Create an instance of {@link CompositionPlaylist.ReelList.Reel.AssetList.MainSound }
     * 
     */
    public CompositionPlaylist.ReelList.Reel.AssetList.MainSound createCompositionPlaylistReelListReelAssetListMainSound() {
        return new CompositionPlaylist.ReelList.Reel.AssetList.MainSound();
    }

    /**
     * Create an instance of {@link CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle }
     * 
     */
    public CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle createCompositionPlaylistReelListReelAssetListMainSubtitle() {
        return new CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle();
    }

}
