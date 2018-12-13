/**
 * The Simulation class is the main class which controls the running of the program
 * and separation of specific strings.
 *
 * @author Michael Doig, Alainya Wallace
 * @version 1.0
 * @since 6/12/2018
 */

package astaire;

import java.util.ArrayList;
import java.util.HashMap;

public class Simulation {

	private Tokeniser csvReader;
	private ArrayList<String[]> listOne;
	private HashMap<String, Dance> dances;
	private ArrayList<String> runningOrder;
	private TUI ui;
	private Sorter sorter;

	/**
	 * The main method allows the program to run.
	 */
	public static void main(String[] args) {
		Simulation running = new Simulation();

	}


	public Simulation() {
		csvReader = new Tokeniser();
		dances = new HashMap<String, Dance>();
		runningOrder = new ArrayList<String>();
		splitGroups();
		splitDances();

		sorter = new Sorter(dances);
		ui = new TUI(sorter);

	}

	/**
	 * The splitGroup method is used to parse the danceGroups csv file, using the parse methods from the Tokeniser class and placing the data into Groups objects.
	 */
	private void splitGroups() {

		listOne = csvReader.parse("C:/Development/DataStructuresCW/astaire/src/astaire/danceShowData_danceGroups.csv");
		ArrayList<String> temp = csvReader.groupSeperater(listOne.get(0)[0]);
		Group test = new Group(listOne.get(1)[0], temp);
	}

	/**
	 * The splitDances method is used to parse the dances from the people who are involved in them and place all of the information into the dances HashMap.
	 */
	private void splitDances() {
		ArrayList<String[]> tempList = csvReader
				.parse("C:/Development/DataStructuresCW/astaire/src/astaire/danceShowData_dances.csv");
		for (int i = 0; i < tempList.size(); i++) {
			ArrayList<String> output = csvReader.groupSeperater(tempList.get(i)[1]);
			Dance tempDance = new Dance(tempList.get(i)[0], output, tempList.get(i)[1]);
			dances.put(tempList.get(i)[0], tempDance);
		}
	}

}
