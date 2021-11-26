import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class OpenExistingFile {
	
	public static String filename;
	
	public void getOpenExistingfile() {
			
		Scanner input = new Scanner(System.in);
		CreateNewFile createnewfile = new CreateNewFile();
		openfileoption openfileoption = new openfileoption();
		
		System.out.println("\n=====================");
		System.out.println("> Open Existing File");
		System.out.println("=====================");
		System.out.print("\nEnter File name + Location: "); filename = input.nextLine();
		//C:\Users\67372\Documents\PB Sem 3\OOP\ASG03\Clothing.txt
			
		File openfile = new File (filename);
			
		Pattern filepattern = Pattern.compile("^<Clothing>.*</Clothing>$");
		StringBuilder Content = new StringBuilder();
		boolean FileIsAvailable = false;
			
		while (!FileIsAvailable){
			if(openfile.exists() && openfile.isFile()) {
				Scanner readopenfile = null;
				try {
					readopenfile = new Scanner (openfile);
					while(readopenfile.hasNextLine()) {
						Content.append(readopenfile.nextLine().trim());
					}
					Matcher fileMatcher = filepattern.matcher(Content);	
					if (fileMatcher.matches()) {
						FileIsAvailable = true;
						System.out.println("\n=======================================================================================");
						System.out.println("\tFile formatting is valid\n");
						System.out.println("\t" + Content);
						System.out.println("=======================================================================================");
					} 
					else {
						System.out.println("\n\tFile formatting is invalid");
						System.out.print("\nRe-input file name + location:");
						filename = input.nextLine();
						openfile = new File(filename);
					}
						
				} catch (FileNotFoundException e) {
					System.out.println("\n\tFile is not available.");
					System.out.println("Re-input file name + location:");
					filename = input.nextLine();
					openfile = new File(filename);
				} finally {
					if(readopenfile != null) {
						readopenfile.close();
					}
				}	
			} else {
				System.out.println("\n\tFile is not exist");
				System.out.println("\t" + openfile.getAbsolutePath());
				if(!openfile.isDirectory()) {
						
					System.out.print("\nCreate new file? (y/n): "); String option = input.nextLine();
						
					if (option.equalsIgnoreCase("y")) {
						createnewfile.creatingfile();
						FileIsAvailable = true;
					} else {
						System.out.print("\nRe-input file name + location:");
						filename = input.nextLine();
						openfile = new File(filename);
					}
				} else {
					System.out.println("It is not a file.");
					System.out.println("\nRe-input file name + location:");
					filename = input.next();
					openfile = new File(filename);
				}
			}
		}
		boolean back = true;
		
		while(back) {
		System.out.println("\n===========================");
		System.out.println("> Open File: " + openfile.getName());
		System.out.println("===========================");
		
		System.out.println("\n[1] Display data");
		System.out.println("[2] Add new data");
		System.out.println("[3] Back");
		System.out.print("\nSelect option: "); int selectoption = input.nextInt();
			
		switch (selectoption) {
			case 1: openfileoption.getdisplaydata();
				break;
			case 2: openfileoption.getaddnewdata();
				break;
			case 3: back = false;
				break;
			}
		}
	}
}
