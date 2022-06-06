package stdbtt.springcourse;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;

import java.util.List;

public interface Music {
	String getSong();

	List<String> getMusicList(Genre genre);
}