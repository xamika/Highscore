package highscore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tobias on 09.11.2016.
 */
public class Highscore implements HighscoreServer {
    private DataAccess dataAccess = new DataAccess();

    public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public void setHighscores(HashMap<String, Integer> highscores) {
        for (Map.Entry<String, Integer> highscore : highscores.entrySet()) {
            String playerName = highscore.getKey();
            HashMap<String, Integer> player = searchHighscoreByPlayerName(playerName);
            insertIfPlayerPresent(player, playerName, highscore.getValue());
        }
    }

    public HashMap<String, Integer> getHighscores() {
        return dataAccess.getAllHighscores();
    }
    
    public HashMap<String, Integer> searchHighscoreByPlayerName(String playerName) {
        return dataAccess.getHighscoresByPlayerName(playerName);
    }

    private void insertIfPlayerPresent(HashMap<String, Integer> player, String playerName, Integer score) {
        if (player.size() == 0) {
            insertHighscore(playerName, score);
        } else {
            updateIfHigherScore(player, playerName, score);
        }
    }

    private void updateIfHigherScore(HashMap<String, Integer> player, String playerName, Integer score) {
        Map.Entry<String, Integer> entry = player.entrySet().iterator().next();
        Integer oldPlayerScore = entry.getValue();
        if (oldPlayerScore < score) {
            updateHighscore(playerName, score);
        }
    }


    private void updateHighscore(String playerName, int score) {
        dataAccess.updateData(playerName, score);
    }

    private void insertHighscore(String playerName, int score) {
        dataAccess.insertData(playerName, score);
    }

    public static void main(String[] args) {
    	Highscore hs = new Highscore();
		
        HashMap<String, Integer> r = hs.searchHighscoreByPlayerName("Tobias");

        for (Map.Entry<String, Integer> entry : r.entrySet()) {
            String key = entry.getKey().toString();
            Integer value = entry.getValue();
            System.out.println("key, " + key + " value " + value);
        }
  
    }
    
}
