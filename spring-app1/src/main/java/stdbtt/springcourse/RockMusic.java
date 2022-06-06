package stdbtt.springcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class RockMusic implements Music {

	@Override
	public String getSong() {
		return "Wind cries Mary";
	}

	@Override
	public List<String> getMusicList(Genre genre) {
		ArrayList<String> musicList = new ArrayList<>();

		musicList.add("Stairway to Heaven");
		musicList.add("Blue Monday");
		musicList.add("We're the Champions");
		return musicList;
	}

}
