/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.jm.todolist.dto;

import java.util.Map;

/**
 *
 * @author jean marc
 */
public class TdEvent {
    
    private Integer tdId;
    private String tdTache;
    private Integer tdCategorie;
    private boolean tdUrgent = false;
    private boolean tdFait = false;
    
    

    //constructeur par défaut
    public TdEvent() {
    }

    //constructeur 2 argument TEXTE CATEGORIE
    public TdEvent(String tdText, Integer tdCategory) {
        this.tdTache = tdText;
        this.tdCategorie = tdCategory;
    }

    //constructeur 3 arguments TEXTE CATEGORIE URGENCE
    public TdEvent(String tdText, Integer tdCategory, boolean tdUrgent) {
        this.tdTache = tdText;
        this.tdCategorie = tdCategory;
        this.tdUrgent = tdUrgent;
    }

    //constructeur 3 arguments TEXTE CATEGORIE URGENCE FAIT
    public TdEvent(String tdText, Integer tdCategory, boolean tdUrgent, boolean tdFait) {
        this.tdTache = tdText;
        this.tdCategorie = tdCategory;
        this.tdUrgent = tdUrgent;
        this.tdFait = tdFait;
    }

    //constructeur 3 arguments ID TEXTE CATEGORIE URGENCE FAIT
    public TdEvent(Integer tdId, String tdText, Integer tdCategory, boolean tdUrgent, boolean tdFait) {
        this.tdId = tdId;
        this.tdTache = tdText;
        this.tdCategorie = tdCategory;
        this.tdUrgent = tdUrgent;
        this.tdFait = tdFait;
    }

    public Integer getTdId() {
        return tdId;
    }

    public void setTdId(int tdId) {
        this.tdId = tdId;
    }

    public String getTdTache() {
        return tdTache;
    }

    public void setTdTache(String tdText) {
        this.tdTache = tdText;
    }

    public Integer getTdCategorie() {
        return tdCategorie;
    }

    public void setTdCategorie(Integer tdCategory) {
        this.tdCategorie = tdCategory;
    }

    public boolean getTdUrgent() {
        return tdUrgent;
    }

    public void setTdUrgent(boolean tdUrgent) {
        this.tdUrgent = tdUrgent;
    }

    public boolean getTdFait() {
        return tdFait;
    }

    public void setTdFait(boolean tdFait) {
        this.tdFait = tdFait;
    }
    
    public Object[] getTdData() {
        Object[] data = {tdTache,tdCategorie,tdUrgent,tdFait};
        return data;
    }
    
    
    public String toString(){
        return this.tdId+" "+this.tdTache+" "+this.tdCategorie+" "+this.tdUrgent+" "+this.tdFait;
    }
    

}
