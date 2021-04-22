package com.cs_group.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class com.cs_group.objects.User
 * Description: The main data struct that is passed around through the panels
 *       Contains every information the com.cs_group.objects.User needs to use the software
 */
public class User implements Iterable, Cloneable{
    ArrayList<GameList> m_GameLists = new ArrayList<>(); // com.cs_group.objects.Game list placeholder
    GameList m_activeGameList;
    String m_Name;
    String m_Password;

    /**
     * Constructor that inits vars.
     */
    public User(){
        m_Name = "";
        m_Password = "";
        m_activeGameList = new GameList();
        m_GameLists.add(m_activeGameList);
    }

    /**
     * Overloaded contructor with name and password set
     * @param name name for com.cs_group.objects.User
     * @param password password for com.cs_group.objects.User
     */
    public User(String name, String password){
        m_Name = name;
        m_Password = password;
        m_activeGameList = new GameList();
        m_GameLists.add(m_activeGameList);
    }

    /**
     * Bad design but, didn't know how else. Just returns the arraylists of GameLists
     * @return
     */
    public ArrayList<GameList> getGameLists() {
        return m_GameLists;
    }

    /**
     * Basic Set. Passwords don't have any protection
     * @param password password
     */
    public void setPassword(String password){m_Password = password;}

    /**
     * Basic set
     * @param name Name
     */
    public void setName(String name){m_Name = name;}

    /**
     * Conviences. Basic set for Name & Password
     * @param name name
     * @param password passowrd
     * @return boolean (unsure why i made it a bool)
     */
    public boolean setInfo(String name, String password){
        m_Name = name;
        m_Password = password;
        return true;
    }

    /**
     * Basic get
     * @return name
     */
    public String getName(){return m_Name;} //Changed the gets for right now so that it does not require a String for a parameter for login (3/28/21)

    /**
     * Basic get. Password has no protections
     * @return passowrd
     */
    public String getPassword(){return m_Password;} //Changedthe gets for right now so that it does not require the String for a parameter for login (3/28/21)

    /**
     * basic toString
     * @return string with all information
     */
    @Override
    public String toString() {
        return "com.cs_group.objects.User{" +
                "m_Name='" + m_Name + '\'' +
                ", m_Password='" + m_Password + '\'' +
                '}';
    }


    /**
     * to JSON. to string, but repurposed for the JSON
     * @return string for that JSON
     */
    public String toJSON(){
        return "{\"ID\":\""+ "com.cs_group.objects.User{" +
                "m_Name='" + m_Name + '\'' +
                ", m_Password='" + m_Password + '\'' +
                "}\"}";
    }

    /**
     * Method: createGameList
     * Descripton: Creates a new game list for the user.
     * @param gameList: Passes in a gameList object so that it can be added to the users list
     */
    public void createGameList(GameList gameList){
        m_GameLists.add(gameList);
    }



    /**
     * Method: iterator.
     * @return Returns a GameList iterator.
     */
    public Iterator<GameList> iterator() {
        return this.m_GameLists.iterator();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

