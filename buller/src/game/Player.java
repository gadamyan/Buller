package game;
import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Player extends Point3D {
	private Texture tex;
	private int dx, dy;
	public final float size = 0.15f;
	public Player() {
		try {
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("game/res/player.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		z = -2.0f;
		dx = 0;
		dy = 0;
	}
	
	public void draw() {
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor3f(1.0f, 1.0f, 1.0f);
		tex.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 1); glVertex3f(x - size, y - size, z);
			glTexCoord2f(1, 1); glVertex3f(x + size, y - size, z);
			glTexCoord2f(1, 0); glVertex3f(x + size, y + size, z);
			glTexCoord2f(0, 0); glVertex3f(x - size, y + size, z);
		glEnd();
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}
	
	public void move(UserKeys key) {
		switch (key) {
		case RIGHT: dx += 4; break;
		case LEFT: dx -= 4; break;
		case UP: dy += 4; break;
		case DOWN: dy -= 4; break;
		}
	}

	public void stabilize() {
		if (dx != 0) {
			if (dx > 0) {
				dx -= 1;
			} else {
				dx += 1;
			}
		}
		if (dy != 0) {
			if (dy > 0) {
				dy -= 1;
			} else {
				dy += 1;
			}
		}
		x += dx * 0.001;
		y += dy * 0.001;
		if (x < -1.3) {
			x = -1.3f;
			dx = 0;
		}
		if (x > 1.3) {
			x = 1.3f;
			dx = 0;
		}
		if (y < -1) {
			y = -1;
			dy = 0;
		}
		if (y > 1) {
			y = 1;
			dy = 0;
		}
	}
}