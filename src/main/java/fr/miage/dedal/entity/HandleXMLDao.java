/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.entity;

import fr.miage.dedal.core.Handle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fr.miage.dedal.controller.ConfigurerController;
import fr.miage.dedal.core.Party;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alex
 */
public class HandleXMLDao extends HandleDao{
    private XStream xstream = new XStream(new DomDriver());
    private String nameFile="save.xml";

    
    @Override
    public Party Create(Party o){
       String xml = xstream.toXML(o);
       Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, xml, xml);
       File fichier = new File(nameFile);
       FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(fichier);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            xstream.toXML(o, fos);
        } finally {
           try {
               fos.close();
           } catch (IOException ex) {
               Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       return o;
    }
    
    @Override
    public Party Update(Party o) {
        this.Delete(o);
        return this.Create(o);
    }
    
    @Override
    public void Delete(Party o) {
        try{
            File file = new File(nameFile);
            if(file.delete()){
                Logger.getLogger(HandleXMLDao.class.getName()).log(Level.INFO, null,"Supression effectué");
            }else{
                Logger.getLogger(HandleXMLDao.class.getName()).log(Level.WARNING, null,"Erreur de suppresion ");
            }
    	}catch(Exception e){
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, e);
    	}
    }
    
    @Override
    public Party findAll() {
        Party handle = null;
        try {
            FileInputStream fis = new FileInputStream(new File(nameFile));
            try {
                handle = (Party) xstream.fromXML(fis);
            } finally {
                fis.close();
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, "File not found", e);
            handle = new Party();
        } catch (IOException ioe) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return handle;
    }
    
}
