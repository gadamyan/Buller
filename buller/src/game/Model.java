package game;

public class Model {
	public Player player;
	public Stars stars;
	public Stones stones;
	public Events.MyEventListener listener;
	
	public Model(Events.MyEventListener listener) {
		this.listener = listener;
		stars = new Stars();
		player = new Player();
		stones = new Stones();
	}
	
	public void move(UserKeys key) {
		player.move(key);
	}
	
	public void play(boolean play) {
		stars.draw(play);
		stones.draw(play);
		if (play) {
			player.stabilize();
			if (stones.checkCollisions(player, player.size)) {
				listener.changed(new Events.Event(new Object()));
			}
		}
		player.draw();
	}
}
