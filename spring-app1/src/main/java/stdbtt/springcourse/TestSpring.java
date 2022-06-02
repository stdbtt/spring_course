package stdbtt.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		MusicPlayer musicPlayer = (MusicPlayer)context.getBean("musicPlayer");
		musicPlayer.playMusic();
		System.out.println(musicPlayer.getName());
		System.out.println(musicPlayer.getVolume());
		
		context.close();
	}
	
}
