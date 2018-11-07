package room_content;

public class Table extends Furniture {
	private String type;
	private String model;

// CONSTRUCTORS----------------
	public Table(double square) {
		super(square);
	}

	public Table(double square, String model) {
		super(square);
		this.model = model;
	}

	public Table(double square, String type, String model) {
		super(square);
		this.type = type;
		this.model = model;
	}
	// ----------------

// GETTERS---------
	public String getModel() {
		return model;
	}

	public String getType() {
		return type;
	}
	// ------------------

}
