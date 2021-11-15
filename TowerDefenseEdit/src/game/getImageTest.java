package game;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

class getImageTest {

	@Test
	void test() {
		for (int i = 0; i < 1000000; i++) {
			BufferedImage backdrop = ResourceLoader.getLoader().getImage("path_2.jpg");
			BufferedImage car = ResourceLoader.getLoader().getImage("HaasCar.png");
		}
	}

}
