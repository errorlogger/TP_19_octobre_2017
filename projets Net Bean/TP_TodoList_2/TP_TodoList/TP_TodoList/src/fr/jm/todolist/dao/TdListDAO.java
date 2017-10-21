/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.jm.todolist.dao;


import fr.jm.todolist.dto.TdEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 *
 * @author jean marc
 */
public class TdListDAO implements InterfaceCrud <TdEvent , TdListDAO >{
    
    private Connection connect;
    private PreparedStatement state;
    private ResultSet rst;
    
    public TdListDAO(Connection connect)throws SQLException {
        this.connect = connect;
    }

    public ResultSet getRst(){
        return this.rst;
    }
    
   
    @Override
    public void addTdEvent(TdEvent obj)throws SQLException {
        String sql ="INSERT INTO liste (tache,id_categorie,urgent,fait) VALUES(?,?,?,?)";
        state = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//pour recuperer l'id du dernier insert
        
        state.setString(1,obj.getTdTache());
        state.setInt(2,obj.getTdCategorie());
        state.setBoolean(3,obj.getTdUrgent());
        state.setBoolean(4,obj.getTdFait());
        
        /*ResultSet key = state.getGeneratedKeys();
        if(key.next()){
            obj.setTdId(key.getInt("id"));
        }*/
        
        state.executeUpdate();
    }

    @Override
    public void deleteTdEvent(TdEvent obj) throws SQLException {
        
            String sql = "DELETE FROM liste WHERE id_tache =?";
            System.out.println(obj.getTdId());
            state = connect.prepareStatement(sql);
            state.setInt(1,(int)obj.getTdId());
            
            state.executeUpdate();
        
        
    }

    @Override
    public void modifyTdEvent(TdEvent obj) throws SQLException {
        String sql ="UPDATE liste SET tache = ? , id_categorie = ? , urgent = ? , fait = ? WHERE id_tache = ?";
        state = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//pour recuperer l'id du dernier insert
        
        state.setString(1,obj.getTdTache());
        state.setInt(2,obj.getTdCategorie());
        state.setBoolean(3,obj.getTdUrgent());
        state.setBoolean(4,obj.getTdFait());
        state.setInt(5,obj.getTdId());
        
        ResultSet key = state.getGeneratedKeys();
        if(key.next()){
            obj.setTdId(key.getInt("id"));
        }
        
        state.executeUpdate(); 
    }

   
    public void deleteAllTdEvent() throws SQLException {
        String sql = "DELETE FROM liste ";
            
            state = connect.prepareStatement(sql);
                       
            state.executeUpdate(); 
    }

    @Override
    public TdListDAO getTdEventAsDao(int id) throws SQLException {
        String sql = "SELECT * FROM liste WHERE id = ?";
        state = connect.prepareStatement(sql);
        state.setInt(1,id);
            
        rst = state.executeQuery();
        return this;
    }

    @Override
    public TdListDAO getAllTdEventAsDao() throws SQLException {
        String sql = "SELECT id_tache,tache,categorie,urgent,fait FROM `liste` INNER JOIN categories ON liste.id_categorie = categories.id";
        state = connect.prepareStatement(sql);
        rst = state.executeQuery();
        return this;
    }

    @Override
    public TdEvent getTdEvent()throws SQLException {
        TdEvent evt = new TdEvent();
        
        if (rst.next()){
            evt.setTdTache(rst.getString("tache"));
            evt.setTdCategorie(rst.getInt("categorie"));
            evt.setTdUrgent(rst.getBoolean("urgent"));
            evt.setTdFait(rst.getBoolean("fait"));
        }
        
        return evt; 
    }

    @Override
    public Map<String, String> getTdEventAsMap() throws SQLException {
        Map<String,String> mapDonnees = new HashMap<>();
        
        if (rst.next()){
            mapDonnees.put("tache",rst.getString("tache"));
            mapDonnees.put("categorie",String.valueOf(rst.getInt("id_categorie")));
            mapDonnees.put("urgent",String.valueOf(rst.getBoolean("urgent")));
            mapDonnees.put("fait",String.valueOf(rst.getBoolean("fait")));
        }
        
        return mapDonnees;
    }

    @Override
    public List<Map<String, String>> getAllTdEventAsMap() throws SQLException {
        List<Map<String,String>> lsMap = new ArrayList<>();
        
        if (rst.isBeforeFirst()){
            while(!rst.isLast()){
            lsMap.add(this.getTdEventAsMap());
            }
        }
        return lsMap;
    }

    @Override
    public List<TdEvent> getAllTdEvent() throws SQLException {
        List<TdEvent> ls = new ArrayList<>();
        
        if (rst.isBeforeFirst()){
            while(!rst.isLast()){
                ls.add(this.getTdEvent());
            }
        }
        return ls;
    }
    
    @Override
    public Map<String,Integer> getFkMap()throws SQLException{
        String sql = "SELECT * FROM categories ORDER BY categorie";
        state = connect.prepareStatement(sql);
        rst = state.executeQuery();
        
        Map<String,Integer> map = new HashMap<>();
        
        while(rst.next()){
            map.put(rst.getString(2),rst.getInt(1));
          
        }
        return map;
    }
}

    