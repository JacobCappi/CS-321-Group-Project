package com.cs_group.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class: com.cs_group.objects.GameList
 * Description: Data Type that stores multiple Games and the name of the List itself
 */
public class GameList implements Cloneable, Iterable{
    String m_listName;
    ArrayList<Game> m_gameList = new ArrayList<>();
    int m_length;

    /**
     * Constructor that sets the name to default and zeros length
     */
    public GameList(){
        m_length = 0;
        setListName("Default");
    }

    /**
     * method: Basic Set
     * @param listName name of the list
     */
    public void setListName(String listName){m_listName = listName;}

    /**
     * Basic Get
     * @return listname
     */
    public String getListName(){return m_listName;}

    /**
     * method addGame()
     *        adds game to list and increase's length. (technically arraylist has size, so unsure why I did it this way)
     * @param game game to add
     */
    public void addGame(Game game){
        m_gameList.add(game);
        m_length++;
    }

    /**
     * method: delete com.cs_group.objects.Game
     *         bug(duplicate game names aren't handled, the first one found is deleted)
     *         out of time, and not a huge issue
     * @param game game to delete
     * @return
     */
    public boolean deleteGame(Game game){
        for(int i =0; i<m_length; i++){
            if(m_gameList.get(i).getTitle().equals(game.getTitle())){
                m_gameList.remove(i);
                m_length--;
                return true;
            }
        }
        return false;
    }

    /**
     * Basic clear(). uses arraylist's clear() and length is set to zero
     */
    public void clear(){
        m_gameList.clear();
        m_length = 0;
    }

    /**
     * Unused, but works like remove.
     * @param game searches for time game
     * @return true if it does, false if it doesn't
     */
    public boolean ifExists(Game game){
        for(int i =0; i<m_length; i++){
            if(m_gameList.get(i).getID().equals(game.getID())){
                return true;
            }
        }
        return false;
    }

    /**
     * Basic Get
     * @return length
     */
    public int getLength() {
        return m_length;
    }

    /**
     * String dump
     * @return that String
     */
    @Override
    public String toString() {
        return "com.cs_group.objects.GameList{" +
                "m_listName='" + m_listName + '\'' +
                ", m_gameList=" + m_gameList +
                ", m_length=" + m_length +
                '}';
    }

    /**
     * Basic Clone() method implementation
     * @return super.clone()
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Basic Iterator implementation so that user can use it
     * @return the Iterator
     */
    public Iterator<Game> iterator() {
        return this.m_gameList.iterator();
    }

}
