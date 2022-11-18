import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
/**
 * <h1>Tools</h1>
 * <p>
 * Various tools for ease of use
 * </p>
 *
 * @author Eli Wood, Zachary Sousa, Dave Slemon
 * @version v108
 */
public class Tools
{
    /**
     * <h1>Save</h1>
     * <p>
     * saves your current array
     * </p>
     * 
     * @author Eli Wood
     * @version v100
     * 
     * @param array is the array you want to save
     */
    public static void save(int[][]array) {
        array = reduce(array);
        
        try {
            FileWriter out = new FileWriter("saved.txt");
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    out.write(array[i][j] + " ");
                }
                out.write("\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println("An error has occured");
            System.out.println(e);
        }
    }
    /**
     * <h1>Save</h1>
     * <p>
     * saves your current array
     * </p>
     * 
     * @author Eli Wood
     * @version v100
     * 
     * @param array the array you want to save
     * @param fileName filename is the name of the file you want to save
     */
    public static void save(int[][]array, String fileName) {
        array = reduce(array);
        
        try {
            FileWriter out = new FileWriter(fileName);
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    out.write(array[i][j] + " ");
                }
                out.write("\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println("An error has occured");
            System.out.println(e);
        }
    }
    /**
     * <h1>Load</h1>
     * <p>
     * loads your previously saved 2d array
     * </p>
     * 
     * @author Eli Wood
     * @version v100
     * 
     * @return array the loaded array
     */
    public static int[][] load() {
        ArrayList<String> numbers = new ArrayList();
        
        try {
            File saved = new File("saved.txt");
            
            Scanner in = new Scanner(saved);
            
            while(in.hasNextLine()) {
                numbers.add(in.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            System.out.println(e);
        }
        
        int[][] array = new int[numbers.size()][3];
        
        try {
            for (int i = 0; i < array.length; i++) {
                /*
                int firstSpace = numbers.get(i).indexOf(' ');
                int secondSpace = numbers.get(i).substring(firstSpace + 1, numbers.get(i).length()).indexOf(' ') + firstSpace + 1;
                int lastSpace = numbers.get(i).lastIndexOf(' ');
                
                System.out.println(numbers.get(i));
                System.out.println(firstSpace);
                System.out.println(secondSpace);
                System.out.println(lastSpace);
                
                array[i][0] = Integer.parseInt(numbers.get(i).substring(0, firstSpace));
                array[i][1] = Integer.parseInt(numbers.get(i).substring(firstSpace+1, secondSpace));
                array[i][2] = Integer.parseInt(numbers.get(i).substring(secondSpace+1, lastSpace));
                
                System.out.println(array[i][0]);
                System.out.println(array[i][1]);
                System.out.println(array[i][2]);
                */
               
                String [] temp = new String [3];
                
                temp = numbers.get(i).split(" ");
                
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = Integer.parseInt(temp[j]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            System.out.println(e);
        }
        
        array = expand(array);
        
        return array;
    }
    /**
     * <h1>Load</h1>
     * <p>
     * loads your previously saved 2d array
     * </p>
     * 
     * @author Eli Wood
     * @version v100
     * 
     * @param fileName then name of the file you want to load
     * @return array the loaded array
     */
    public static int[][] load(String fileName) {
        ArrayList<String> numbers = new ArrayList();
        
        try {
            File saved = new File(fileName);
            
            Scanner in = new Scanner(saved);
            
            while(in.hasNextLine()) {
                numbers.add(in.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            System.out.println(e);
        }
        
        int[][] array = new int[numbers.size()][3];
        
        try {
            for (int i = 0; i < array.length; i++) {
                /*
                int firstSpace = numbers.get(i).indexOf(' ');
                int secondSpace = numbers.get(i).substring(firstSpace + 1, numbers.get(i).length()).indexOf(' ') + firstSpace + 1;
                int lastSpace = numbers.get(i).lastIndexOf(' ');
                
                System.out.println(numbers.get(i));
                System.out.println(firstSpace);
                System.out.println(secondSpace);
                System.out.println(lastSpace);
                
                array[i][0] = Integer.parseInt(numbers.get(i).substring(0, firstSpace));
                array[i][1] = Integer.parseInt(numbers.get(i).substring(firstSpace+1, secondSpace));
                array[i][2] = Integer.parseInt(numbers.get(i).substring(secondSpace+1, lastSpace));
                
                System.out.println(array[i][0]);
                System.out.println(array[i][1]);
                System.out.println(array[i][2]);
                */
               
                String [] temp = new String [3];
                
                temp = numbers.get(i).split(" ");
                
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = Integer.parseInt(temp[j]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            System.out.println(e);
        }
        
        array = expand(array);
        
        return array;
    }
    /**
     * <h1>Reduce</h1>
     * <p>
     * serializes sparse arrays using the triplet algorithm
     * </p>
     * @author Eli Wood
     * @version v100
     * 
     * @param matrix the array you want to reduce
     * @return reduced is the serialized sparse array
     */
    public static int[][] reduce(int[][]matrix) {
        int num = 0;
        int currentRow = 1;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    num ++;
                }
            }
        }
        
        int [] [] reduced = new int [num + 1] [3];
        
        reduced[0] [0] = matrix.length;
        reduced[0] [1] = matrix[0].length;
        reduced[0] [2] = num;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    reduced[currentRow][0] = i;
                    reduced[currentRow][1] = j;
                    reduced[currentRow][2] = matrix[i][j];
                    currentRow += 1;
                }
            }
        }
        
        return reduced;
    }
    /**
     * <h1>expand</h1>
     * <p>
     * deseriaizes a sparse array from the triplet algorithm
     * </p>
     * @author Eli Wood
     * @version v100
     * 
     * @param reduced the serialized array
     * @return full the deserialized array
     */
    public static int[][] expand(int[][]reduced) {
        int[][]full = new int[reduced[0][0]][reduced[0][1]];
        
        int currentRow = 1;
        
        for (int i = 0; i < full.length; i++) {
            for (int j = 0; j < full[i].length; j++) {
                if (currentRow < reduced.length) {
                    if (reduced[currentRow][0] == i && reduced[currentRow][1] == j) {
                        full[i][j] = reduced[currentRow][2];
                        currentRow++;
                    } else {
                        full[i][j] = 0;
                    }
                } else {
                    full[i][j] = 0;
                }
                //System.out.println(full[i][j]);
            }
        }
        
        return full;
    }
    /**
     * <h1>Ask for int</h1>
     * <p>
     * This tool converts strings to integers
     * </p>
     * 
     * @param question is a String for what you want to ask
     * @return x is an int for your converted number
     * 
     * @author Eli Wood
     * @version v102
     */
    public static int askForInt(String question)
    {
        boolean isInt = false;
        
        String temp;
        int x = 0;
        
        Scanner keybd = new Scanner(System.in);
        
        while (isInt == false) {
            System.out.print(question + " ");
            temp = keybd.nextLine();
            isInt = isInt(temp);
            if (isInt == false) {
                System.out.printf("Please enter a proper whole number\n");
            } else {
                x = Integer.parseInt(temp);
            }
        }
        
        return x;
    }
    
    /**
     * <h1>Ask for double</h1>
     * <p>
     * This tool converts strings to doubles
     * </p>
     * 
     * @param temp is a String that you want to convert
     * @param question is a String for what you want to ask
     * @return x is a double for your converted number
     * 
     * @author Eli Wood
     * @version v102
     */
    public static double askForDouble(String question)
    {
        boolean isDouble = false;
        String temp;
        
        double x = 0.0;
        
        Scanner keybd = new Scanner(System.in);
        
        while (isDouble == false) {
            System.out.print(question + " ");
            temp = keybd.nextLine();
            isDouble = isDouble(temp);
            if (isDouble == false) {
                System.out.printf("Please enter a proper decimal number\n");
            } else {
                x = Double.parseDouble(temp);
            }
        }
        
        return x;
    }
    
    /**
     * <h1>Ask for String</h1>
     * <p>
     * This tool takes a question and asks for a double
     * </p>
     * 
     * @param question is a String for what you want to ask
     * @return x is a String for your converted number
     * 
     * @author Eli Wood
     * @version v101
     */
    public static String askForString(String question)
    {
        Scanner keybd = new Scanner(System.in);
        
        String x;
        
        System.out.print(question + " ");
        x = keybd.nextLine();
        if (x == null) {
            return "Please enter a valid phrase\n";
        } else {
            return x;
        }
    }
    
    /**
     * <h1>triangle area</h1>
     * <p>
     * This tool finds the area of a triangle from 3 sides
     * </p>
     * 
     * @param a is a double for side 1
     * @param b is a double for side 2
     * @param c is a double for side 3
     * 
     * @return area is the area of the triangle
     * 
     * @author Eli Wood
     * @version v100
     */
    public static double triArea(double a, double b, double c)
    {
        double area, p;
        
        p = (a + b + c) / 2;
        
        area = Math.sqrt(p * (p-a) * (p-b) * (p-c));
        
        return area;
    }
    
    /**
     * <h1>First space</h1>
     * <p>
     * This tool locates the first space in a String
     * </p>
     * @author Eli Wood
     * @version v100
     */
    public static int SpaceLocation(String str) {
        int space = str.indexOf(" ");
        return space;
    }
    
    /**
     * <h1>First space</h1>
     * <p>
     * This tool locates the first space in a String
     * </p>
     * @author Eli Wood
     * @version v100
     */
    public static int LastSpaceLocation(String str) {
        int space = str.lastIndexOf(" ");
        return space;
    }
    
    /**
     * <h1>reverseString</h1>
     * 
     * <ul>
     *  <li>This function takes a String of any size and rewrites it in backwards order</li>
     * </ul>
     * 
     * @author Zachary Sousa
     * @version v100
     * @param phrase    a string of any length, inputted by you or the user
     * @return          bwPhrase (reveresed version of inputted String)
     * 
     * 
     */
    public static String reverseString (String phrase) {
        
        String bwPhrase = "";
        
            for(int i = phrase.length() - 1; i > -1; i --)
            {
                bwPhrase = bwPhrase + Character.toString(phrase.charAt(i)); //Creates a new string by indexing all characters of phrase in reverse order.
            }
            
        return bwPhrase;
        
    }
    
    /**
     * <h1>Is int</h1>
     * <p>
     * This method returns true is the given str is an integer,
     * otherwise false.
     *
     * @author: Dave Slemon, Eli Wood
     * @version v101
     * 
     * @param str is a string 
     * @return true if the str is an integer other false
        */
     public static boolean isInt(String str) 
     {
        if (str == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
    This method returns true is the given str is a double,
    otherwise false.

    @author: Dave Slemon, Eli Wood
    @version v101
    @param str is a string 
    @return true if the str is a double other false
    */
     public static boolean isDouble(String str) 
     {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
    This method returns true is the given str is a number,
    otherwise false.

    @author: Dave Slemon
    @version 1.00
    @param str is a string 
    @return true if the str is a number other false
    */
     public static boolean isNumeric(String str) 
     {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     * <h1>highest int</h1>
     * <p>
     * This tool finds the highes number in an int array
     * </p>
     * 
     * @author Eli Wood
     * @version v100
     * @param array is an int array
     * @return the highest int
     */
    public static int highestInt(int[] array) 
    {
        int highest = array[0];
        
        
        for (int i = 0; i < array.length; i++) {
            highest = Math.max(highest, array[i]);
        }
        
        return highest;
    }
}
