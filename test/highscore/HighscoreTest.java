package highscore;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HighscoreTest {
	Highscore highscore;
	DataAccess dataAccess;

	@Before
	public void setUp() throws Exception {
		dataAccess = mock(DataAccess.class);
		highscore = new Highscore();
		highscore.setDataAccess(dataAccess);
	}
	
	@Test
	public void testSetHighscoresNewPlayer() {
		HashMap<String, Integer> highscores = new HashMap<>();
		highscores.put("test", 22);
		highscores.put("test3", 22);
		HashMap<String, Integer> oldHighscore = new HashMap<>();
		when(dataAccess.getHighscoresByPlayerName(any())).thenReturn(oldHighscore);
		doNothing().when(dataAccess).insertData(any(), any());
		
		highscore.setHighscores(highscores);
		verify(dataAccess, times(2)).insertData(any(), any());
	}
	
	@Test
	public void testSetHighscoresUpdatePlayer() {
		HashMap<String, Integer> highscores = new HashMap<>();
		highscores.put("test", 22);
		highscores.put("test2", 22);
		HashMap<String, Integer> oldHighscore = new HashMap<>();
		oldHighscore.put("test", 10);
		when(dataAccess.getHighscoresByPlayerName(any())).thenReturn(oldHighscore);
		doNothing().when(dataAccess).updateData(any(), any());

		highscore.setHighscores(highscores);
		verify(dataAccess, times(2)).updateData(any(), any());
	}
	
	@Test
	public void testSetHighscoresOldScoreHigher() {
		HashMap<String, Integer> highscores = new HashMap<>();
		highscores.put("test", 22);
		highscores.put("test2", 22);
		HashMap<String, Integer> oldHighscore = new HashMap<>();
		oldHighscore.put("test", 300);
		when(dataAccess.getHighscoresByPlayerName(any())).thenReturn(oldHighscore);
		
		highscore.setHighscores(highscores);
		verify(dataAccess, times(2)).getHighscoresByPlayerName(any());
	}

	@Test
	public void testGetHighscores() {
		HashMap<String, Integer> highscores = new HashMap<>();
		highscores.put("test", 22);
		when(dataAccess.getAllHighscores()).thenReturn(highscores);
		
		assertEquals(highscore.getHighscores(), highscores);
	}

	@Test
	public void testSearchHighscoreByPlayerName() {
		HashMap<String, Integer> highscores = new HashMap<>();
		highscores.put("test", 22);
		when(dataAccess.getHighscoresByPlayerName("test")).thenReturn(highscores);
		
		assertEquals(highscore.searchHighscoreByPlayerName("test"), highscores);
	}

}
