package bilding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import custom_exceptions.IlluminanceException;
import custom_exceptions.SquareException;
import interfaces.BuiltUtils;




public class Building implements BuiltUtils{
	private List<Room> rooms = new ArrayList<Room>();
	private String name;

	public Building(String name) {
		super();
		this.name = name;
	}

	// METHODS-----
	public void addRoom(double square, String name, int amountOfWindows){
		rooms.add(new Room(square, name, amountOfWindows));
	}

	public Room getRoom(int index) {
		return rooms.get(index);
	}

	public Room getRoom(String name) {
		Room returnRoom = null;
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.contains(getRoom(i).getName())) {
				returnRoom = rooms.get(i);
			}
		}
		return returnRoom;

	}
	@Override
	public void describe() {
		System.out.println(this.name.toUpperCase());
		
		for(int i = 0; i < this.getRooms().size(); i++){
			this.getRoom(i).describe();
		}

	}
	
	@Override
	public void validate() throws IlluminanceException, SquareException {
		Iterator<Room> iterator = rooms.iterator();
		while (iterator.hasNext()) {
			iterator.next().validate();
		}
		
	}

	@Override
	public void showRestrictions() {
		System.out.println("Bilding restrictions matche with room's restrictions");
		System.out.println(Room.getDownLimitOfillumination() +"\n" 
		+ Room.getUpLimitOfillumination() +"\n"+
		Room.getUpPerCentSquareOfThings());
	}

	// -------------

	// GETTERS-------
	public List<Room> getRooms() {
		return rooms;
	}

	public String getName() {
		return name;
	}

	

}
