// This class depends on what data is in our data file
// Subject to change (the data file that we picked earlier has these attributes)
public class Game {
   String m_ID;
   String m_Title;
   String m_highlights, m_optimized;
   String m_SteamUrl;
   String m_Publisher;
   String m_Genre;
   String m_Status;

   String m_PathToPic;

   public Game(){
      m_ID = "1";
      m_Title = "N/A";
      m_highlights = "N/A";
      m_optimized = "N/A";
      m_SteamUrl = "N/A";
      m_Publisher= "N/A";
      m_Genre= "N/A";
      m_Status= "N/A";

      m_PathToPic = "./gamePictures/"; // Will determine layout later
   }
   public Game(String id, String title, String highlights, String optimized, String url, String publisher, String genre, String status, String filePath){
      m_ID = id;
      m_Title = title;
      m_highlights = highlights;
      m_optimized = optimized;
      m_SteamUrl = url;
      m_Publisher= publisher;
      m_Genre= genre;
      m_Status= status;

      m_PathToPic = filePath; // Will determine layout later
   }
   // Easiest way to initiallize will probably pass a parser to gamelist, and let it go through constructor

   public String getGenre(){return m_Genre;}
   public String getTitle(){return m_Title;}
   public String getID(){return m_ID;}
}
