/**
 * The Tokeniser Class is used to parse the csv files related to the system
 *
 * @author Michael Doig, Alainya Wallace
 * @version 1.0
 * @since 4/12/2018
 */


package astaire;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class Tokeniser {

	private String csvFile;
	public Tokeniser(){

		}

	/**
	 * This method is the part which takes the file, reads it line by line and breaks it up by when there is a tab present. It then places all the data into an ArrayList of String arrays.
	 * @param String fileName
	 * @return an ArrayList<String[]> which is used to construct the dance class in the simulator
	 */
	public ArrayList<String[]> parse(String fileName){
		csvFile = fileName;
		String line = "";
		String cvsSplitBy = "\t";
		BufferedReader br = null;
		ArrayList<String[]> result = new ArrayList<String[]>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null){
				result.add(line.split(cvsSplitBy));

			}
		return result;
		} catch (FileNotFoundException e2){
			e2.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally {
			if (br != null){
				try{
					br.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	/**
	 * This method is where the String of groups which is taken from the CSV is separated where there is a comma present.
	 * @param String groups
	 * @return ArrayList<String> , this contains all of the groups separated out into indexes.
	 */
	public ArrayList<String> groupSeperater(String groups){
		String list = groups;
		//list.split(",");
		ArrayList<String> temp = new ArrayList<>(Arrays.asList(list.split(",")));
		return temp;
	}

	/**
	 * This method is where the runningOrder is written to a new file called "RunningOrder.csv"
	 * @param Dance dance
	 * @throws IOException
	 *
	 */
	public void writeToCSV (Dance dance) throws IOException{
		File file = new File ("test.csv");
		FileWriter writer = new FileWriter("RunningOrder.csv",true);
		BufferedWriter br = new BufferedWriter(writer);
		br.write("\n");
		br.write(dance.getName());
		br.write("\t");
		br.write(dance.getUnparsedDancers());
		br.close();
		writer.close();
	}

}
