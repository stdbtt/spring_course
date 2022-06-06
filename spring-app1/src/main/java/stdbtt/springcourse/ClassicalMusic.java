package stdbtt.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


public class ClassicalMusic implements Music {

	@Override
	public String getSong() {
		return "Hungarian Rhapsody";
	}


	@Override
	public List<String> getMusicList(Genre genre) {
		ArrayList<String> musicList = new ArrayList<>();

			musicList.add("Spring");
			musicList.add("Second Opera");
			musicList.add("Vodevile");
			return musicList;
	}
}
