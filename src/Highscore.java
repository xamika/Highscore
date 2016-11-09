import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tobias on 09.11.2016.
 */
public class Highscore {
    public void setHighscores(HashMap<String, Integer> highscores) {
        for(Map.Entry<String, Integer> highscore : highscores.entrySet()) {
            String playerName = highscore.getKey();
            Integer score = highscore.getValue();
            if (checkPlayerNamePResent(playerName)) {
                updateHighscore(playerName, score);
            } else {
                insertHighscore(playerName, score);
            }
        }
    }

    public HashMap<String, Integer> getHighscores() {
        return new HashMap<String, Integer>();
    }

    private boolean checkPlayerNamePResent (String playerName) {
        return true;
    }

    private void updateHighscore(String playerName, int Score) {

    }

    private void insertHighscore(String playerName, int Score) {

    }
}
