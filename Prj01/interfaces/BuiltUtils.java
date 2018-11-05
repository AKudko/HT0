package interfaces;

import custom_exceptions.IlluminanceException;
import custom_exceptions.SquareException;

public interface BuiltUtils {
	
	public void validate() throws IlluminanceException, SquareException;

	public void describe();
	
	public void showRestrictions();

}
