package game;
import static org.lwjgl.opengl.GL11.*;
import java.util.Random;


public class Stars {
	private Point3D[] stars = new Point3D[10000];
	private final float speed = 1.0f;
	public Stars() {
		Random rand = new Random();
		for (int i = 0; i < stars.length; ++i) {
			stars[i] = new Point3D((rand.nextFloat() - 0.5f) * 100f,
					            (rand.nextFloat() - 0.5f) * 100f,
					            rand.nextInt(200) - 200f);
		}
	}
	
	public void draw(boolean play) {
		glColor3f(0.7f, 0.7f, 0.7f);
		glBegin(GL_POINTS);
			for (Point3D p : this.stars) {
				if (play) {
					p.setZ(p.getZ() + this.speed);
					if (p.getZ() > 0) {
						p.setZ(-200.0f);
					}
				}
				glVertex3f(p.getX(), p.getY(), p.getZ());
			}
		glEnd();
	}
}