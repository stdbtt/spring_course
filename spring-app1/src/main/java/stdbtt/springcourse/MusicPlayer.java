package stdbtt.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MusicPlayer {


	private List<Music> musicList;

	public MusicPlayer(List<Music> musicList) {
		this.musicList=musicList;
	}
	
	public String playMusic() {
		Random random = new Random();
		String song = musicList.get(random.nextInt(3)).getSong();

		return "Playing: " + song;
	}
}
