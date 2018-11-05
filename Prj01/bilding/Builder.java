package bilding;

import custom_exceptions.IlluminanceTooLowException;
import custom_exceptions.IlluminanceTooMuchException;
import custom_exceptions.SpaceUsageTooMuchException;



public class Builder {

	public static void main(String[] args) {

		Building building = new Building("Building 1");
		building.addRoom(10, "room 1", 0);
		building.addRoom(200, "room 2", 3);
// исходные данные для комнат
		double[] chairs = { 0.5, 1, 0.5, 0.75, 0.45, 0.25 };
		double[] chairs2 = { 0.5, 1, 0.5, 1 };
		double[] tables = { 5, 7.5, 3, 4, 3, 5, 2 };
		double[] tables2 = { 3, 6, 1.5, 5, 7.5, 3, 4, 3, 5, 2 };
		int[] lightbulb = { 2000, 150, 250, 300, 350 }; 
		int[] lightbulb2 = { 50, 150, 250, 300, 350, 400 };
//
// в случае если освещенность превышает максимум из=за большого количества окон
		char[] choiceVariants = { 'y', 'n' };
		char choice = choiceVariants[0];
		int step = -1;


		// fillings 1 rooms
		
		// т.к. из условия задачи должно добавляться произвольное количество лампочек и мебели
		// пока не будут достигнуты установленные пределы
		// методы доваления элементов в комнату выбрасывают исключение по достижении предельных 
		// значений. Для того чтобы отлавливать исключения отдельно по каждому из типов добавляемых
		// элементов оборачиваем в трай кэтч добавления стульев, столов и лампочек
		try {
			for (int i = 0; i < lightbulb.length; i++) {
				step = i;
				building.getRoom(0).addLightbulb(lightbulb[i]);
			}
		} catch (IlluminanceTooMuchException e) {
			System.out.println(" Too high illumination on step " + step + " ("
					+ building.getRoom(0).toString() + " ) ");
			//e.printStackTrace();
		}
		try {
			for (int i = 0; i < chairs.length; i++) {
				step = i;
				building.getRoom(0).addChair(chairs[i]);
			}
		} catch (SpaceUsageTooMuchException e1) {
			System.out.println(" Usage space is exceeded with chairs ( adding " + (step+1)+" ("
					+ building.getRoom(0).toString() + " ) "+ ")");
			//e1.printStackTrace();
		}
		try {
			for (int i = 0; i < tables.length; i++) {
				step = i;
				building.getRoom(0).addTable(tables[i]);
			}
		} catch (SpaceUsageTooMuchException e1) {
			System.out.println(" Usage space is exceeded with tables ( adding " + (step+1)+ " ("
					+ building.getRoom(0).toString() + " ) "+")");
			//e1.printStackTrace();
		}

		// fillings 2 room

		try {
			for (int i = 0; i < lightbulb2.length; i++) {
				step = i;
				building.getRoom(1).addLightbulb(lightbulb2[i]);
			}
		} catch (IlluminanceTooMuchException e) {
			System.out.println(" Too high illumination on step " + step+" ("
					+ building.getRoom(0).toString() + " ) ");
			//e.printStackTrace();
		}
		try {
			for (int i = 0; i < chairs2.length; i++) {
				step = i;
				building.getRoom(1).addChair(chairs2[i]);
			}
		} catch (SpaceUsageTooMuchException e1) {
			System.out.println(" Usage space is exceeded with chairs ( adding " + (step+1)+" ("
					+ building.getRoom(0).toString() + " ) "+ ")");
			//e1.printStackTrace();
		}
		try {
			for (int i = 0; i < tables2.length; i++) {
				step = i;
				building.getRoom(1).addTable(tables2[i]);
			}
		} catch (SpaceUsageTooMuchException e1) {
			System.out.println(" Usage space is exceeded with tables ( adding " + (step+1)+ " ("
					+ building.getRoom(0).toString() + " ) "+")");
			//e1.printStackTrace();
		}

		// validate room 1
		// осуществляем проверку вкладываются ли все параметры коматы в требуемые пределы
		//

		try {
			building.getRoom(0).validate();
		} catch (IlluminanceTooMuchException e) {
			System.out.println(" Too high illumination from windows! " + " ("
					+ building.getRoom(0).toString() + " ) ");
			System.out.println("Fix room? (y / n)");
			System.out.println(choice);
			// уменьшаем количество окон, т.к. ограничение на превышение освещенности
			// исключает превышение освещенности из-за большого количества ламп
			if (choice == 'y') {
				int indexLastWindow = building.getRoom(0).getListOfWinows().size() - 1;
				if (building.getRoom(0).getListOfLightbulbs().isEmpty())// If
																					// there
																					// is
																					// no
																					// Light
																					// bulbs
				{
					while (building.getRoom(0).getIlluminationOfRoom() > Room.getUpLimitOfillumination()) {
						building.getRoom(0).removeWindow(indexLastWindow);
						
						indexLastWindow--;
					}

				} else {
					System.out.println("Something badly wrong whith Illumination and windows");
				}

			}
		} catch (SpaceUsageTooMuchException e) {
			System.out.println(" Too much furniture! \n"
					+ " You should remove some furniture \n ("
					+ building.getRoom(0).toString() + " ) ");
			//e.printStackTrace();
		} catch (IlluminanceTooLowException e) {
			System.out.println(" Too low illumination! \n"
					+ " You should add more windows or light bulbs \n ("
					+ building.getRoom(0).toString() + " ) ");
			//e.printStackTrace();
		}

		
		building.describe();

	}
}
