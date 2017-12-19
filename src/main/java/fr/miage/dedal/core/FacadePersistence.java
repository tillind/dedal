/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.core;

import fr.miage.dedal.core.parameter.EPersistance;
import fr.miage.dedal.entity.HandleDao;
import fr.miage.dedal.entity.HandleJsonDao;
import fr.miage.dedal.entity.HandleRedisDao;
import fr.miage.dedal.entity.HandleXMLDao;

/**
 *
 * @author alex
 */
public class FacadePersistence {
    
    private HandleDao persist;
    
    public FacadePersistence(EPersistance ep){
        switch(ep){
            case JSON: persist = new  HandleJsonDao();break;
            case XML: persist = new  HandleXMLDao();break;
            case REDIS: persist = new  HandleRedisDao();break;
        }
    }
    
    public void saveHandle(Party h){
        persist.Create(h);
    }
    
    public void clearHandle(Party h){
        persist.Delete(h);
    }
    
    public Party getHandle(){
        return persist.findAll();
    }
    
}
