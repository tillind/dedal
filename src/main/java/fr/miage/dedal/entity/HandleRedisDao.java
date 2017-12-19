/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.entity;

import fr.miage.dedal.core.Handle;
import fr.miage.dedal.core.Party;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 *
 * @author alex
 */
public class HandleRedisDao extends HandleDao {
    private static final String host =  "192.168.99.100";
    private static final int port =  32768;
    private static final int redisDB = 0;
    
    private Jedis jedis;
    
    private final String PATH= "Player" ;
    
    public HandleRedisDao(){
        jedis = new Jedis(host, port);
        jedis.select(redisDB);
    }
    
    @Override
    public Party Create(Party o) {
       ArrayList<Handle> handles = o.getHandles();
       Map<String,String> hash = new HashMap<>();
        for (Handle handle : handles) {
            hash.put(String.valueOf(handle.getParty()), String.valueOf(handle.getHighScore()));
        }
        this.jedis.hmset(PATH, hash);
       return o;
    }
    @Override
    public void Delete(Party o){
        jedis.del(PATH);
    }
    
    @Override
    public Party findAll() {
        Party p = new Party();
        ArrayList<Handle> handles = new ArrayList<>();
        Map<String, String> properties = jedis.hgetAll(PATH);
        
        if(properties.isEmpty()){
            return new Party();
        }
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            Handle tmp = new Handle();
            tmp.setParty(Integer.parseInt(entry.getKey()));
            tmp.setHighScore(Integer.parseInt(entry.getValue()));
            handles.add(tmp);
        }
        p.setHandles(handles);
        return p;
    }
}
