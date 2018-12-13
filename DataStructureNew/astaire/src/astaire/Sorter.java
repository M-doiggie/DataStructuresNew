/**
 * The Sorter Class implements the Controller interface so that it can implement the abstract methods of Controller.
 * In this class, the methods provide information to the user.
 *
 * @author Michael Doig, Alainya Wallace
 * @version 1.0
 * @since 4/12/2018
 */


package astaire;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Sorter implements Controller {
	private HashMap<String, Dance> dances;
	private ArrayList<String> runningOrder;
	private Tokeniser csvReader;

	public Sorter(HashMap<String, Dance> dance) {
		dances = dance;
		csvReader = new Tokeniser();
	}

	/**
	 * This method is used to complete Requirement number 2 of the specification, It takes the dances from the HashMap and lists them alphabetically.
	 * @return String result, is used to be output to the console whilst the application is running.
	 */

	@Override
	public String listAllDancesAndPerformers() {
		// TODO Auto-generated method stub
		String result = "";
		Object[] keys = dances.keySet().toArray();
		Arrays.sort(keys);
		System.out.println("These are the Dances listed Alphabetically: ");
		int count = 0;
		for (Object key : keys) {
			Dance title = dances.get(key);
			result = result + "\n" + count +": " + title.getName() + " Dancers: " +title.getUnparsedDancers() ;
			count ++;
		}
		return result;
	}


	/**
	 *
	 * @return String result, is used to display all of the dancers involved in a specific dance
	 */
	@Override
	public String listAllDancersIn(String dance) {
		String result = "";
		if (dances.containsKey(dance)) {
			result = "These are the dancers in " + dance + " : " + dances.get(dance).getUnparsedDancers();
		} else
			result = "No Key Found";
		return result;
	}

	/*
	 * @Override public String checkFeasibilityOfRunningOrder(String filename,
	 * int gaps) { // TODO Auto-generated method stub //ArrayList, if value of
	 * the list contains Pattern, if pattern occurs within the next x gaps,
	 * return false or something. //Still broken, just need doesnt hit
	 * correctly, no match. System out put doesnt make much sense, more testing
	 * needed but almost there String checkingString = ""; int count = 0;
	 * boolean hit = false; for (int i = 0; i < runningOrder.size(); i++){
	 * HashSet set = new
	 * HashSet(parser.groupSeperater((dances.get(runningOrder.get(i)).getDancers
	 * ()))); count = 0; for (int j = i; j < runningOrder.size(); j++){ HashSet
	 * comparing = new
	 * HashSet(parser.groupSeperater((dances.get(runningOrder.get(j)).getDancers
	 * ()))); System.out.println(count + "   " + hit); if (count > gaps || count
	 * == 0){ hit = false; } else hit = true; if (set.equals(comparing) && hit
	 * == true ){ System.out.println(comparing +"      " + set); return
	 * "Failed"; } count ++; } }
	 *
	 * return "Fine"; }
	 */
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {

		if (gaps == 0) {
			return "runningOrder good";
		}

		for (int i = 0; i < runningOrder.size(); i++) {
			Set<String> searchSet = new HashSet<String>(dances.get(runningOrder.get(i)).getDancers());
			for (int j = 1; j <= gaps; j++) {
				int counter = i + j;
				if (counter >= runningOrder.size()) {
					break;
				}
				Set<String> compareSet = new HashSet<String>(dances.get(runningOrder.get(counter)).getDancers());
				Set<String> intersection = new HashSet<String>(searchSet);
				intersection.retainAll(compareSet);
				if (intersection.isEmpty() == false) {
					return "RunningOrder is broken";
				}
			}
		}
		System.out.println("All good chief");
		return "runningOrder good";
	}

	@Override
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		LinkedList tempRunningOrder = new LinkedList<Dance>();
		Boolean isValid = false;
		Random generator = new Random();
		Set<String> keySet = dances.keySet();
		ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
		while (isValid == false) {
			int ALSize = listOfKeys.size();
			System.out.println(ALSize);
			int randInt = 0;
			if (ALSize != 0) {
				randInt = generator.nextInt(ALSize);
				String pickedObject = listOfKeys.get(randInt);
				listOfKeys.remove(randInt);
				try {
					csvReader.writeToCSV(dances.get(pickedObject));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ALSize == 0) {
				getRunningOrder();
				if (checkFeasibilityOfRunningOrder("test.csv", gaps) == "runningOrder good") {
					isValid = true;
				}
			}
		}

		String result = "File Created";
		return result;
	}

	private void getRunningOrder() {
		ArrayList<String[]> tempList = csvReader.parse("test.csv");
		for (int i = 1; i < tempList.size(); i++) {
			ArrayList<String> stringArray = csvReader.groupSeperater(tempList.get(i)[1]);
			runningOrder.add(tempList.get(i)[0]);
		}
	}
}
