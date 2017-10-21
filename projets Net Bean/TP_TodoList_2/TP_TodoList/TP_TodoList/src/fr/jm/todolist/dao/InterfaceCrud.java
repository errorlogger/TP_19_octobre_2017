/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.jm.todolist.dao;

import fr.jm.todolist.dao.TdListDAO;
import fr.jm.todolist.dto.TdEvent;
import java.util.List;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author jean marc
 * @param <O>
 * @param <DAO>
 */
public interface InterfaceCrud <O , DAO >{
    
    
    
    public void deleteTdEvent(O obj)throws SQLException;
    public void modifyTdEvent(O obj)throws SQLException;
    public void addTdEvent(O obj)throws SQLException;
    public DAO getTdEventAsDao(int id)throws SQLException;
    public DAO getAllTdEventAsDao()throws SQLException;
    public O getTdEvent()throws SQLException;
    public Map<String,String> getTdEventAsMap()throws SQLException;
    public List<Map<String,String>> getAllTdEventAsMap()throws SQLException;
    public List<O> getAllTdEvent() throws SQLException;
    public Map<String,Integer> getFkMap()throws SQLException;
}
