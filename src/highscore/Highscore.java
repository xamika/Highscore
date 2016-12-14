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
            HashMap<String, Integer> player = getHighscoreByPlayerName(playerName);
            insertOrUpdatePlayer(player, playerName, highscore.getValue());
        }
    }

    public HashMap<String, Integer> getHighscores() {
        return dataAccess.getAllHighscores();
    }
    
    public HashMap<String, Integer> getHighscoreByPlayerName(String playerName) {
        return dataAccess.getHighscoresByPlayerName(playerName);
    }

    private void insertOrUpdatePlayer(HashMap<String, Integer> player, String playerName, Integer score) {
        if (player.size() == 0) {
            dataAccess.insertData(playerName, score);
        } else {
            updateScore(player, playerName, score);
        }
    }

    private void updateScore(HashMap<String, Integer> player, String playerName, Integer score) {
        Map.Entry<String, Integer> entry = player.entrySet().iterator().next();
        Integer oldPlayerScore = entry.getValue();
        score = oldPlayerScore + score;
        dataAccess.updateData(playerName, score);
    }
    
}
