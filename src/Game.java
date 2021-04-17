/**
 * class: Game
 *        Datafile that holds is used to store instances of Games. Modelled after the gameFile
 */
public class Game {
   String m_ID;
   String m_Title;
   String m_highlights, m_optimized;
   String m_SteamUrl;
   String m_Publisher;
   String m_Genre;
   String m_Status;
   String m_rating;
   String m_PathToPic;

   /**
    * Base Constructor that inits all variables
    */
   public Game(){
      m_ID = "1";
      m_Title = "N/A";
      m_highlights = "N/A";
      m_optimized = "N/A";
      m_SteamUrl = "N/A";
      m_Publisher= "N/A";
      m_Genre= "N/A";
      m_Status= "N/A";
      m_rating= "N/A";

      m_PathToPic = "./gamePictures/"; // Will determine layout later
   }

   /**
    * Overloaded constuctor that takes in all values and sets the vars
    * params are there non m_ version
    * @param id
    * @param title
    * @param highlights
    * @param optimized
    * @param url
    * @param publisher
    * @param genre
    * @param status
    * @param rating
    */
   public Game(String id, String title, String highlights, String optimized, String url, String publisher, String genre, String status, String rating){
      m_ID = id;
      m_Title = title;
      m_highlights = highlights;
      m_optimized = optimized;
      m_SteamUrl = url;
      m_Publisher= publisher;
      m_Genre= genre;
      m_Status= status;
      m_rating= rating;
   }

   /**
    * Basic Set
    * @param id
    */
   public void setId(String id){m_ID=id;}

   /**
    * Basic Set
    * @param title
    */
   public void setTitle(String title){m_Title=title;}
   /**
    * Basic Set
    * @param highlights
    */
   public void setHighlights(String highlights){m_highlights=highlights;}

   /**
    * Basic Set
    * @param optimized
    */
   public void setOptimized(String optimized){m_optimized=optimized;}

   /**
    * Basic Set
    * @param url
    */
   public void setURL(String url){m_SteamUrl=url;}

   /**
    * Basic Set
    * @param publisher
    */
   public void setPublisher(String publisher){m_Publisher=publisher;}

   /**
    * Basic Set
    * @param genre
    */
   public void setGenre(String genre){m_Genre=genre;}

   /**
    * Basic Set
    * @param status
    */
   public void setStatus(String status){m_Status=status;}

   /**
    * Basic Set
    * @param rating
    */
   public void setRating(String rating){m_rating=rating;}

   /**
    * Basic Get
    * @return ID
    */
   public String getID() {
      return m_ID;
   }

   /**
    * Basic Get
    * @return Title
    */
   public String getTitle() {
      return m_Title;
   }

   /**
    * Basic Get
    * @return Hgihlights
    */
   public String getHighlights() {
      return m_highlights;
   }

   /**
    * Basic Get
    * @return Optimized
    */
   public String getOptimized() {
      return m_optimized;
   }

   /**
    * Basic Get
    * @return Steam Url
    */
   public String getSteamUrl() {
      return m_SteamUrl;
   }

   /**
    * Basic Get
    * @return Publisher
    */
   public String getPublisher() {
      return m_Publisher;
   }

   /**
    * Basic Get
    * @return genre
    */
   public String getGenre() {
      return m_Genre;
   }

   /**
    * Basic Get
    * @return Status
    */
   public String getStatus() {
      return m_Status;
   }

   /**
    * Basic Get
    * @return Rating
    */
   public String getRating() {return m_rating;}

   /**
    * method: compareName
    *          Compares titles of the two games. To handle spaces, every word split by space
 *          is search individually with recursion. If every word exists, return true.
    * @param game Game holding the title from the user's search
    * @return
    */
   public boolean compareNames(Game game){// dumb code, but it could have looked a lot messier.
       // this is handling spaces through recurrsion: it's a mess, don't ask
      if(game.getTitle().indexOf(" ") > -1){
         int m_tmpInt0 = game.getTitle().indexOf(" ");

         Game game2 = new Game();
         Game game3 = new Game();
         game2.setTitle(game.getTitle().substring(0,m_tmpInt0));
         game3.setTitle(game.getTitle().substring(m_tmpInt0+1));

         boolean m_tmpRetVal1 = compareNames(game2);
         boolean m_tmpRetVal2 = compareNames(game3);

         return (m_tmpRetVal1 && m_tmpRetVal2);
      }
      else{
         return (this.getTitle().toLowerCase().indexOf(game.getTitle().toLowerCase()) > -1);
      }
   }

   /**
    * method: Comparing Genre
    *          See comparing title. Copy paste of the code with title replaced with genre
    * @param game game holding the genre from user entry
    * @return
    */
   public boolean compareGenre(Game game){// dumb code, but it could have looked a lot messier.
      // this is handling spaces through recurrsion: it's a mess, don't ask
      if(game.getTitle().indexOf(" ") > -1){
         int m_tmpInt0 = game.getGenre().indexOf(" ");

         Game game2 = new Game();
         Game game3 = new Game();
         game2.setTitle(game.getGenre().substring(0,m_tmpInt0));
         game3.setTitle(game.getGenre().substring(m_tmpInt0+1));

         boolean m_tmpRetVal1 = compareGenre(game2);
         boolean m_tmpRetVal2 = compareGenre(game3);

         return (m_tmpRetVal1 && m_tmpRetVal2);
      }
      else{
         return (this.getGenre().toLowerCase().indexOf(game.getGenre().toLowerCase()) > -1);
      }
   }

   /**
    * method ToString() implementation
    *        Override toString() just prints everything
    * @return
    */
   @Override
   public String toString() {
      return "Game{" +
              "m_ID='" + m_ID + '\'' +
              ", m_Title='" + m_Title + '\'' +
              ", m_highlights='" + m_highlights + '\'' +
              ", m_optimized='" + m_optimized + '\'' +
              ", m_SteamUrl='" + m_SteamUrl + '\'' +
              ", m_Publisher='" + m_Publisher + '\'' +
              ", m_Genre='" + m_Genre + '\'' +
              ", m_Status='" + m_Status + '\'' +
              ", m_rating='" + m_rating + '\'' +
              ", m_PathToPic='" + m_PathToPic + '\'' +
              '}';
   }
}
