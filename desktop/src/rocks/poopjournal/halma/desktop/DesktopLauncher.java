package rocks.poopjournal.halma.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import rocks.poopjournal.halma.Halma;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.out.println("bin am Desktop");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Halma(), config);
	}
}
