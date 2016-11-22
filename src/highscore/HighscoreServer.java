package highscore;

import java.util.HashMap;

/**
 * Created by Tobias on 21.11.2016.
 */
public interface HighscoreServer {
    void setHighscores(HashMap<String, Integer> highscores);
    HashMap<String, Integer> getHighscores();
    HashMap<String, Integer> searchHighscoreByPlayerName(String name);
}
