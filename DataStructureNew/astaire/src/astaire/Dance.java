/**
 * The Dance class allows the storage of Dance information in one location.
 *
 * @author Michael Doig, Alainya Wallace
 * @version 1.0
 * @since 4/12/2018
 */

package astaire;

import java.util.ArrayList;

public class Dance {

	private String name;
	private ArrayList<String> dancers;
	private String unparsedDancers;

	public Dance(String name, ArrayList<String> dancers, String unparsedDancers) {
		this.name = name;
		this.dancers = dancers;
		this.unparsedDancers = unparsedDancers;
	}

	/**
	 * Getter methods for the name variable
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for dancers variable
	 *
	 * @return dancers
	 */
	public ArrayList<String> getDancers() {
		return dancers;
	}

	/**
	 * Getter method for unparsedDancers variable
	 *
	 * @return unparsedDancers
	 */
	public String getUnparsedDancers() {
		return unparsedDancers;
	}
}
