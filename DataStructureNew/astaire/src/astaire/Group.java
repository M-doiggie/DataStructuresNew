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

public class Group {
	private String name;
	private ArrayList<String> dancers;

	public Group(String groupName, ArrayList<String> temp){
		name = groupName;
		dancers = temp;

	}

	public String getNamesOfDancers(){
		String result = "";
		for (int i = 0; i < dancers.size(); i++){
			if (i == 0){
				result = dancers.get(i);
			}
			else{
				result = result +" , " + dancers.get(i);
			}
		}

		return result;

	}
}
