/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.dedal.core.Handle;
import fr.miage.dedal.core.Party;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class HandleJsonDao extends HandleDao {
    private String nameFile="save.json";
    @Override
    public Party Create(Party o) {
        ObjectMapper mapper = new ObjectMapper();
        File fichier = new File(nameFile);
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(fichier);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Object to JSON in file
            mapper.writeValue(fichier, o);
        } catch (IOException ex) {
            Logger.getLogger(HandleJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileInputStream fis = new FileInputStream(new File(nameFile));
            try {
                handle = mapper.readValue(fis, Party.class);
            } catch (IOException ex) {
                Logger.getLogger(HandleJsonDao.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HandleJsonDao.class.getName()).log(Level.SEVERE, null, ex);
            handle = new Party();
        }
       
        return handle;
    }
    
}
