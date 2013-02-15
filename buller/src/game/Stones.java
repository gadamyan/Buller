package game;
import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;


public class Stones {
	private final float speed = 0.4f;
	private final float size = 0.5f;
	private final int count = 50;
	private Point3D[] stones = new Point3D[count];
	public Stones() {
		Random rand = new Random();
		for (int i = 0; i < stones.length; ++i) {
			stones[i] = new Point3D((rand.nextFloat() - 0.5f) * 30f,
					            (rand.nextFloat() - 0.5f) * 30f,
					            rand.nextInt(400) - 400f);
		}
	}
	
	public boolean checkCollisions(Point3D obj, float size) {
		float col_size = size + this.size;
		for (Point3D p : this.stones) {
			double len = Math.sqrt(Math.pow(obj.getX() - p.getX(), 2) + Math.pow(obj.getY() - p.getY(), 2) + Math.pow(obj.getZ() - p.getZ(), 2));
			if (col_size > len) {
				return true;
			}
		}
		return false;
	}
	
	public void draw(boolean play) {
		glColor3f(0.7f, 0.7f, 0.7f);
		for (Point3D p : this.stones) {
			glPushMatrix();
			glTranslatef(p.getX(), p.getY(), p.getZ());
			Sphere s = new Sphere();
			s.setDrawStyle(GLU.GLU_FILL);
			s.setNormals(GLU.GLU_SMOOTH);
	        s.draw(size, 30, 2);
			glPopMatrix();
			
			if (play) {
				p.setZ(p.getZ() + speed);
				if (p.getZ() > 0) {
					p.setZ(-400f);
					Random rand = new Random();
					p.setX((rand.nextFloat() - 0.5f) * 30f);
					p.setY((rand.nextFloat() - 0.5f) * 30f);
				}
			}
		}
	}
}
