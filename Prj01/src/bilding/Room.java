package bilding;

import java.util.ArrayList;
import java.util.List;


import custom_exceptions.IlluminanceTooLowException;
import custom_exceptions.IlluminanceTooMuchException;
import custom_exceptions.SpaceUsageTooMuchException;
import interfaces.BuiltUtils;
import room_content.Chair;
import room_content.Door;
import room_content.Furniture;
import room_content.LightBulb;
import room_content.Table;
import room_content.Window;

public class Room implements BuiltUtils {
	private double square;
	private double totalSquareOfThings = 0.0;
	private String name;
	private int illuminationOfRoom = 0;

	private int[] amountFurniture = { 0, 0 }; // 0- amount tables 1- amount chairs

	private List<Window> listOfWinows = new ArrayList<Window>();
	private List<Door> amountOfDoors = new ArrayList<Door>();
	private List<LightBulb> listOfLightbulbs = new ArrayList<LightBulb>();
	private List<Furniture> furniture = new ArrayList<Furniture>();

	private static int upLimitOfillumination = 4000;
	private static int downLimitOfillumination = 300;
	private static double upPerCentSquareOfThings = 0.7;

	public Room(double square, String name, int amountOfWindows) {
		super();
		this.square = square;
		this.name = name;
		for (int i = 0; i < amountOfWindows; i++) {
			this.addWindow();
		}
	}

	// METHODS-----------

	@Override
	public void validate() throws IlluminanceTooLowException, IlluminanceTooMuchException, SpaceUsageTooMuchException {

		if (illuminationOfRoom > upLimitOfillumination) {
			throw new IlluminanceTooMuchException(" too much windows! ");
		}
		if (illuminationOfRoom < downLimitOfillumination) {
			throw new IlluminanceTooLowException(" too low illumination! \n "
					+ "too little much light bulbs or windows! ");
		}
		if (totalSquareOfThings > (upPerCentSquareOfThings * square)) {
			throw new SpaceUsageTooMuchException();
		}
	}

	@Override
	public void describe() {
		int amountOfBulbs = this.getListOfLightbulbs().size();

		System.out.println(this.name);
		System.out.print("  - illumination = " + this.illuminationOfRoom + " ( " + this.getListOfWinows().size()
				+ " window(s) for 700 lk, " + this.getListOfLightbulbs().size() + " light bulb(s) (");
		if (this.getListOfLightbulbs().size() > 1) {
			for (int i = 0; i < amountOfBulbs - 1; i++) {
				System.out.print(this.getListOfLightbulbs().get(i).getIllumination() + " lk, ");
			}
			System.out.println(this.getListOfLightbulbs().get(amountOfBulbs - 1).getIllumination() + " lk. ) )");
		} else if (this.getListOfLightbulbs().size() == 1) {
			System.out.print(this.getListOfLightbulbs().get(0).getIllumination() + " lk. ) \n");
		} else {
			System.out.print("0 lk. ) \n");
		}

		System.out.print("  - square = " + this.square + " m^2 ( use " + this.totalSquareOfThings + ", free "
				+ (this.square - this.totalSquareOfThings) + " m^2 ) \n");

		System.out.println("  - furniture: ");
		for (int i = 0; i < furniture.size(); i++) {
			System.out.println("   -- "
					+ (this.furniture.get(i).getClass().getName().equalsIgnoreCase("room_content.Table") ? "table "
							: "chair ")
					+ "(square " + this.furniture.get(i).getSquare() + " m^2 )");
		}

	}

	@Override
	public void showRestrictions() {
		System.out.println("Restrictions \n " + "upLimitOfillumination = 300; \n "
				+ "downLimitOfillumination = 4000; \n" + "upPerCentSquareOfThings = 70;");

	}

	public void checkIlluminaitionlimit(int Illuminaition) throws IlluminanceTooMuchException {
		if (Illuminaition > upLimitOfillumination) {
			throw new IlluminanceTooMuchException();
		}
	}

	public void checkSpaceUsage(double squareOfThings) throws SpaceUsageTooMuchException {
		if (squareOfThings > (upPerCentSquareOfThings * square)) {
			throw new SpaceUsageTooMuchException();
		}
	}

	public void addWindow() /* throws IlluminanceTooMuchException */ {
//		int chekingValueIllumination = 700 + illuminationOfRoom;
//		checkIlluminaitionlimit(chekingValueIllumination);
		Window window = new Window();
		listOfWinows.add(window);
		illuminationOfRoom += window.getIlluminaition();

	}

	public void addWindow(int height, int weight) {
		Window window = new Window(height, weight);
		listOfWinows.add(window);
		illuminationOfRoom += window.getIlluminaition();

	}

	public void addDoor(int height, int weight) {
		amountOfDoors.add(new Door(height, weight));
	}

	public void addLightbulb(int illuminaition) throws IlluminanceTooMuchException {
		int chekingValueIllumination = illuminaition + illuminationOfRoom;
		checkIlluminaitionlimit(chekingValueIllumination);
		LightBulb lightBulb = new LightBulb(illuminaition);
		listOfLightbulbs.add(lightBulb);
		illuminationOfRoom += lightBulb.getIllumination();
	}

	public void addTable(double square) throws SpaceUsageTooMuchException {
		double chekingValueSpace = totalSquareOfThings + square;
		checkSpaceUsage(chekingValueSpace);
		Table table = new Table(square);
		furniture.add(table);
		totalSquareOfThings += table.getSquare();
		amountFurniture[0]++;
	}

	public void addTable(double square, String model) throws SpaceUsageTooMuchException {
		double chekingValueSpace = totalSquareOfThings + square;
		checkSpaceUsage(chekingValueSpace);
		Table table = new Table(square, model);
		furniture.add(table);
		totalSquareOfThings += table.getSquare();
		amountFurniture[0]++;
	}

	public void addTable(double square, String type, String model) throws SpaceUsageTooMuchException {
		double chekingValueSpace = totalSquareOfThings + square;
		checkSpaceUsage(chekingValueSpace);
		Table table = new Table(square, type, model);
		furniture.add(table);
		totalSquareOfThings += table.getSquare();
		amountFurniture[0]++;
	}

	public void addChair(double square) throws SpaceUsageTooMuchException {
		double chekingValueSpace = totalSquareOfThings + square;
		checkSpaceUsage(chekingValueSpace);
		Chair chair = new Chair(square);
		furniture.add(chair);
		totalSquareOfThings += chair.getSquare();
		amountFurniture[1]++;

	}

	public void addChair(double square, String type, String model) throws SpaceUsageTooMuchException {
		double chekingValueSpace = totalSquareOfThings + square;
		checkSpaceUsage(chekingValueSpace);
		Chair chair = new Chair(square, type, model);
		furniture.add(chair);
		totalSquareOfThings += chair.getSquare();
		amountFurniture[1]++;
	}

	public Furniture removeSomeFurniture(int index) {

		return furniture.remove(index);
	}

	public Window removeWindow(int index) {
		illuminationOfRoom -= 700;
		return listOfWinows.remove(index);

	}

	public Door removeDoor(int index) {
		return amountOfDoors.remove(index);

	}

	public LightBulb removeLightbulb(int index) {
		illuminationOfRoom -= listOfLightbulbs.get(index).getIllumination();
		return listOfLightbulbs.remove(index);

	}

	public Furniture removeTable(int index) {
		if (!(furniture.get(index) instanceof Table)) {
			throw new UnsupportedOperationException();
		}
		totalSquareOfThings -= furniture.get(index).getSquare();
		return removeSomeFurniture(index);
	}

	public Furniture removeChair(int index) {

		if (!(furniture.get(index) instanceof Chair)) {
			throw new UnsupportedOperationException();
		}
		totalSquareOfThings -= furniture.get(index).getSquare();
		return removeSomeFurniture(index);
	}

	// -------------------

	// GETTERS ----------------

	public double getSquare() {
		return square;
	}

	public double getTotalSquareOfThings() {
		return totalSquareOfThings;
	}

	public String getName() {
		return name;
	}

	public int getIlluminationOfRoom() {
		return illuminationOfRoom;
	}

	public List<Window> getListOfWinows() {
		return listOfWinows;
	}

	public List<Door> getAmountOfDoors() {
		return amountOfDoors;
	}

	public List<LightBulb> getListOfLightbulbs() {
		return listOfLightbulbs;
	}

	public List<Furniture> getFurniture() {
		return furniture;
	}

	public static int getUpLimitOfillumination() {
		return upLimitOfillumination;
	}

	public static int getDownLimitOfillumination() {
		return downLimitOfillumination;
	}

	public static double getUpPerCentSquareOfThings() {
		return upPerCentSquareOfThings;
	}

	
//------------------
	
	// TO STRING
	
	@Override
	public String toString() {
		return "Room [square=" + square + ", name=" + name + "]";
	}

}
