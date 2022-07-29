package virtualKey;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class VirtualKey {
static String Location;
File name;

   public VirtualKey() {
       Location = System.getProperty("user.dir");
       name = new File(Location+"/files");
       if (!name.exists())
           name.mkdirs();
          }

 static String Intro ="********************\n"
 			+ "********************\n"
 			+" LockedMe.com   \n"	
 			+"********************\n"
 			+"********************\n";



   void showPrimaryMenu() {
	 
	
       System.out.println("\nMain Menu\n"
       		+ "Choose any of the options: \n"+
               "1) Display the current file names\n"+
               "2) Add, Delete or Search the file\n"+
               "3) Close the application");
       try{
           Scanner scanner = new Scanner(System.in);
           int choice = scanner.nextInt();
           switch (choice){
               case 1 : {
                   showFiles();
                   showPrimaryMenu();
               }
               case 2 : {
                   showSecondaryMenu();
               }
               case 3 : {
                   System.out.println("Thank You for using the application");
                   System.exit(0);
               }
               default: showPrimaryMenu();
           }
       }
       catch (Exception e){
           System.out.println("Please enter 1, 2 or 3");
           showPrimaryMenu();
       }
   }

   void showSecondaryMenu() {
       System.out.println("   \nChoose any of the following: \n"+
               "a) Add a file\n"+
               "b) Delete a file\n"+
               "c) Search a file\n"+
               "d) GoBack");
       try{
           Scanner scanner = new Scanner(System.in);
           char[] input = scanner.nextLine().toLowerCase().toCharArray() ;
           char option = input[0];

           switch (option){
               case 'a' : {
                   System.out.print("Enter the File Name to be added: ");
                   String filename = scanner.next().toLowerCase();
                   addFile(filename);
                   break;
               }
               case 'b' : {
                   System.out.print("Enter the File Name to be deleted : ");
                   String filename = scanner.next();
                   deleteFile(filename);
                   break;
               }
               case 'c' : {
                   System.out.print("Enter the File Name to be searched: ");
                   String filename = scanner.next();
                   searchFile(filename);
                   break;
               }
               case 'd' : {
                   System.out.println("Going Back to MAIN menu");
                   showPrimaryMenu();
                   break;
               }
               default : System.out.println("Please enter a, b, c or d");
           }
           showSecondaryMenu();
       }
       catch (Exception e){
           System.out.println("Please enter a, b, c or d");
           showSecondaryMenu();
       }
   }

   void showFiles() {
       if (name.list().length==0)
           System.out.println("There is no file present in this folder");
       else {
           String[] list = name.list();
           System.out.println("The files in the"+ name +" are :");
           Arrays.sort(list);
           for (String str:list) {
               System.out.println(str);
           }
       }
   }

   void addFile(String filename) throws IOException {
       File path = new File(name +"/"+filename);
       String[] list = name.list();
       for (String file: list) {
           if (filename.equalsIgnoreCase(file)) {
               System.out.println("File " + filename + " already exists at " + name);
               return;
           }
       }
       path.createNewFile();
       System.out.println("File "+filename+" added to "+ name);
   }

   void deleteFile(String filename) {
       File filepath = new File(name +"/"+filename);
       String[] list = name.list();
       for (String file: list) {
           if (filename.equals(file) && filepath.delete()) {
               System.out.println("File " + filename + " deleted from " + name);
               return;
           }
       }
       System.out.println("FILE NOT FOUND");
   }

   void searchFile(String filename) {
       String[] list = name.list();
       for (String file: list) {
           if (filename.equals(file)) {
               System.out.println("FOUND : File " + filename + " exists at " + name);
               return;
           }
       }
       System.out.println("File NOT found (FNF)");
   }

public static void main(String[] args) {
		System.out.println(Intro);
       VirtualKey menu = new VirtualKey();
       menu.showPrimaryMenu();

}

}
