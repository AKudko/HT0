package room_content;

public class Chair extends Furniture {
	private String type;
	private String model;

	public Chair(double square) {
		super(square);
	}

	public Chair(double square, String type, String model) {
		super(square);
		this.type = type;
		this.model = model;
	}

	// GETTERS ----------------
	public String getType() {
		return type;
	}

	public String getModel() {
		return model;
	}
	// ---------------------

}
