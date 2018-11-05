package room_content;

public class Window {
	private int height;
	private int weight;
	private int illuminaition;
	
	public Window(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
		this.illuminaition = 700;
	}

	public Window() {
		super();
		this.illuminaition = 700;
	}
	
//  SETTERS------------------
	
	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public int getIlluminaition() {
		return illuminaition;
	}
//----------------------------	
	
	
	

}
