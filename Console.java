import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Console {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		CreateNewFile newfile = new CreateNewFile();
		OpenExistingFile openfile = new OpenExistingFile();
		deletingfile delete = new deletingfile();
		
		System.out.println("==================================");
		System.out.println("    Clothing Management System");
		System.out.println("==================================");
		
		while(true) {
		System.out.println("\n[1] Create new File");
		System.out.println("[2] Open Existing File");
		System.out.println("[3] Delete File");
		System.out.println("[4] Close Application");
		
		System.out.print("\nSelect Option: "); int option = input.nextInt();
		
		switch (option) {
		case 1: newfile.creatingfile();
			break;
		case 2: openfile.getOpenExistingfile();
			break;
		case 3: delete.getdeletefile();
			break;
		case 4: 
			System.out.println("\n===================");
			System.out.println(" System terminate.");
			System.out.println("===================");
			System.exit(0);
			break;
		}
		
		}
	}

}
