/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.entity;

import fr.miage.dedal.core.Handle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
    public Handle Create(Handle o){
       String xml = xstream.toXML(o);
       PrintWriter out=null;
       java.io.FileWriter fw=null;
        try {
            fw = new java.io.FileWriter(nameFile);
            fw.write(xml);
        } catch (IOException ex) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(out!=null){
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        Handle handle = null;
        try {
            FileInputStream fis = new FileInputStream(new File(nameFile));
            try {
                handle = (Handle) xstream.fromXML(fis);
            } finally {
                fis.close();
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ioe) {
            Logger.getLogger(HandleXMLDao.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return handle;
    }
    
}
