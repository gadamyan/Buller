package game;
import java.util.EventListener;
import java.util.EventObject;


public class Events {
	public static class Event extends EventObject {
		private static final long serialVersionUID = 1L;

		public Event(Object source) {
			super(source);
		}
	}

	public static interface MyEventListener extends EventListener {
		public void changed(Event evt);
	}
}
