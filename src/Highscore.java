import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tobias on 09.11.2016.
 */
public class Highscore {
    private SQLiteJDBC dataAccess = new SQLiteJDBC();

    public void setHighscores(HashMap<String, Integer> highscores) {
        for (Map.Entry<String, Integer> highscore : highscores.entrySet()) {
            String playerName = highscore.getKey();
            Integer score = highscore.getValue();

            HashMap<String, Integer> player = getPlayer(playerName);

            if (player != null) {
                Map.Entry<String,Integer> entry=player.entrySet().iterator().next();
                Integer oldPlayerScore=entry.getValue();
                if (oldPlayerScore < score) {
                    updateHighscore(playerName, score);
                }
            } else {
                insertHighscore(playerName, score);
            }
        }
    }

    public HashMap<String, Integer> getHighscores() {
        return dataAccess.selectData("SELECT * FROM highscore");
    }

    private HashMap<String, Integer> getPlayer(String playerName) {
        HashMap<String, Integer> result = dataAccess.selectData("SELECT * FROM highscore WHERE name = '" + playerName + "'");
        if (result.size() > 0) {
            return result;
        }
        return null;
    }

    private void updateHighscore(String playerName, int score) {
        dataAccess.updateData(playerName, score);
    }

    private void insertHighscore(String playerName, int score) {
        dataAccess.insertData(playerName, score);
    }

    public static void main(String[] args) {
        Highscore hs = new Highscore();
        SQLiteJDBC sq = new SQLiteJDBC();
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("Tobias", 301);
        hs.setHighscores(hm);

        HashMap<String, Integer> r = sq.selectData("SELECT * FROM highscore");

        for (Map.Entry<String, Integer> entry : r.entrySet()) {
            String key = entry.getKey().toString();
            Integer value = entry.getValue();
            System.out.println("key, " + key + " value " + value);
        }
    }
}
