package game;
public class Point3D {
	protected float x, y, z;
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.z = y;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getZ() {
		return this.z;
	}
}
