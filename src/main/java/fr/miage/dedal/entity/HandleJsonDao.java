/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.dedal.core.Handle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class HandleJsonDao extends HandleDao {
    private String nameFile="save.xml";
    @Override
    public Handle Create(Handle o) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            //Object to JSON in file
            mapper.writeValue(new File(nameFile), o);
        } catch (IOException ex) {
            Logger.getLogger(HandleJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
       
    @Override
    public Handle Update(Handle o) {
        this.Delete(o);
        return this.Create(o);
    }
    
    @Override
    public void Delete(Handle o) {
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
    public Handle findAll() {
        ObjectMapper mapper = new ObjectMapper();

        Handle obj = null;
        try {
            obj = mapper.readValue(new File(nameFile), Handle.class);
        } catch (IOException ex) {
            Logger.getLogger(HandleJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return obj;
    }
    
}
