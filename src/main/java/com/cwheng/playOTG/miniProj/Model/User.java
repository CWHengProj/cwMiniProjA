package com.cwheng.playOTG.miniProj.Model;

import java.util.List;
import java.util.UUID;

public class User {
    //user properties
    //TODO validation
    private String userName;
    private String uuID; //TODO how to make uuid? how to incorporate into user login
    private Integer stamina;
    private Integer skill;
    private Integer luck;
    private Integer gold;
    private Integer provisions;
    private Integer changeScore;
    private List<String> equipment;
    private List<String> codewords;
    private List<String> notes;
    private Integer currentParagraph;
    private List<Integer> endingsReached; // List of ending pages that the user has explored

    
    public User() {
        uuID= UUID.randomUUID().toString();
        //TODO user creation randomization
        skill = 7; //1 dice /2
        stamina = 10; // 2 dice
        luck = 6; //1 dice
        gold = 6; //2 dice
        


     
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUuID() {
        return uuID;
    }


    public void setUuID(String uuID) {
        this.uuID = uuID;
    }


    public Integer getStamina() {
        return stamina;
    }


    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }


    public Integer getSkill() {
        return skill;
    }


    public void setSkill(Integer skill) {
        this.skill = skill;
    }


    public Integer getLuck() {
        return luck;
    }


    public void setLuck(Integer luck) {
        this.luck = luck;
    }


    public Integer getGold() {
        return gold;
    }


    public void setGold(Integer gold) {
        this.gold = gold;
    }


    public Integer getProvisions() {
        return provisions;
    }


    public void setProvisions(Integer provisions) {
        this.provisions = provisions;
    }


    public Integer getChangeScore() {
        return changeScore;
    }


    public void setChangeScore(Integer changeScore) {
        this.changeScore = changeScore;
    }


    public List<String> getEquipment() {
        return equipment;
    }


    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }


    public List<String> getCodewords() {
        return codewords;
    }


    public void setCodewords(List<String> codewords) {
        this.codewords = codewords;
    }


    public List<String> getNotes() {
        return notes;
    }


    public void setNotes(List<String> notes) {
        this.notes = notes;
    }


    public Integer getCurrentParagraph() {
        return currentParagraph;
    }


    public void setCurrentParagraph(Integer currentParagraph) {
        this.currentParagraph = currentParagraph;
    }


    public List<Integer> getEndingsReached() {
        return endingsReached;
    }


    public void setEndingsReached(List<Integer> endingsReached) {
        this.endingsReached = endingsReached;
    }
    
    



}
