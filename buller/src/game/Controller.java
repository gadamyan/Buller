package game;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Controller {
	private Model model;
	private GameState state;
	
	public Controller() {
		
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Buller");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		this.initializeGL();
		this.initModel();
		
		
		while (!Display.isCloseRequested()) {
			this.initEvents();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			switch (state) {
			case PLAY: model.play(true); break;
			case STOP: model.play(false); break;
			case LOSE: this.lose(); break;
			}
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	private void lose() {
		glColor3f(1.0f, 0, 0);
		glBegin(GL_QUADS);
			glVertex3f(-1.0f, -1.0f, -0.1f);
			glVertex3f(-1.0f, 1.0f, -0.1f);
			glVertex3f(1.0f, 1.0f, -0.1f);
			glVertex3f(1.0f, -1.0f, -0.1f);
		glEnd();
		
	}

	private void initializeGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective((float)60, 640f/ 480f, 0.001f, 100f);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
	}
	
	private void initModel() {
		model = new Model(new Events.MyEventListener() {
			@Override
			public void changed(Events.Event evt) {
				state = GameState.LOSE;
			}
		});
		state = GameState.PLAY;
	}

	private void initEvents() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			model.move(UserKeys.RIGHT);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			model.move(UserKeys.LEFT);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			model.move(UserKeys.UP);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			model.move(UserKeys.DOWN);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			switch (state) {
			case PLAY: state = GameState.STOP; break;
			case STOP: state = GameState.PLAY; break;
			case LOSE: this.initModel(); break;
			}
		}
	}
}
