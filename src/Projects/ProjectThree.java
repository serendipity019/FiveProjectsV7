package Projects;

/**
 * Αυτό το προγραμματάκι δέχεται ώς είσοδο ένα αρχείο κειμένου και βγάζει στατιστικά
 * για το πόσες φορές εμφανίζεται ο κάθε χαρακτήρας σε έναν ταξινομημένο πίνακα
 * βάσει των χαρακτήρων και του αριθμού εμφάνισης αυτών, καθώς και το αντίστροφο.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProjectThree {
    public static void main(String[] args) {
        String homeDir = System.getProperty("user.home");
        String inFilePath =homeDir + "/coding-factory/java exercises/FiveProjectsV7/files/project3TestText2.txt";
        File inputFile = new File(inFilePath);
        String[][] charInventor = new String[128][2];

        if (!inputFile.exists()){
            System.err.println("Το αρχείο εισόδου δεν υπάρχει: " + inputFile.getAbsolutePath());
            return;
        }
        try(Scanner sc = new Scanner(inputFile)){

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line == null || line.isEmpty()) {
                    break; // Break if an empty or null line is appear
                }
                //System.out.println(line); // Print what we read

                // καλούμε αυτήν την μέθοδο για να δημιουργήσουμε τον πίνακα με τους χαρακτήρες.
                fillCharInventor(line, charInventor);
            }

            //Κάνουμε ταξινόμηση βάση χαρακτήρα( στήλη 1η (0))
            sortByColumn(charInventor,0,2, false);
            System.out.println("Στατιστικά βάση χαρακτήρα και συχνότητα εμφάνισης.");
            System.out.println("Χαρακτήρας " + " Συχνότητα εμφάνισης");
            printCharInventor(charInventor);
            //Κάνουμε ταξινόμηση βάση συχνότητας εμφάνισης.(στήλη 2η (1))
            sortByColumn(charInventor, 1 ,2, true );
            System.out.println("Στατιστικά βάση συχνότητας εμφάνισης και χαρακτήρα.");
            System.out.println("Συχνότητα εμφάνισης " + " Χαρακτήρας" );
            printInvCharInventor(charInventor);

        } catch (IOException e){
            System.out.println("Error reading the file: " + e.getMessage());
        }

    }

    private static void fillCharInventor(String line, String[][] charInventor ) {
        for (char c : line.toCharArray()) {
            if (Character.isWhitespace(c)) continue;

            int index = findTheChar(c , charInventor);

            if(index == -1) {
                addChar(c, charInventor);
            } else {
                countPlus(index, charInventor);
            }


        }
    }

    private static int findTheChar(char c, String[][] charInventor){
        for (int i = 0; i < charInventor.length; i++ ){
            if (charInventor[i][0] != null && charInventor[i][0].equals(String.valueOf(c))){
                return i;
            }
        }
        return -1;
    }

    /**
     * Αυτήν η μέθοδος προσθέτει έναν χαρακτήρα αν δεν υπάρχει ήδη στον πίνακα.
     * @param c ο χαρακτήρας που θέλουμε να προσθέσουμε.
     * @param charInventor Ο πίνακας
     */
    private static void addChar(char c, String[][] charInventor){
        for(int i = 0; i < charInventor.length; i++){
            if(charInventor[i][0] == null) {
                charInventor[i][0] = String.valueOf(c);
                charInventor[i][1] = "1";
                break;
            }
        }
    }

    /**
     * Αυτήν η μέθοδος μετράει το πόσες φορές ένας χαρακτήρας εμφανίζεται.
     * @param index είναι ο δείκτης της θέσης που βρίσκεται ο χαρακτήρας στον πίνακα
     * @param charInventor Είναι ο πίνακας με τους χαρακτήρες.
     */
    private static void countPlus(int index, String[][] charInventor){
        int count = Integer.parseInt(charInventor[index][1]); // επειδή είναι String ο πινακας το κάνουμε integer για να κάνουμε την πρόσθεση.
        count++;
        charInventor[index][1] = String.valueOf(count); // Το ξανα κάνουμε String.
    }

    private static void printCharInventor(String[][] charInventor) {
        //System.out.println();
        //System.out.println("Table character Statistic");

            for (String[] row : charInventor) {
                if (row[0] != null) {
                    System.out.println("       " + row[0] + "   ->       " + row[1]);
                }
            }

    }

    //Αυτήν η μέθοδος εκτυπώνει πρώτα την συχνότητα εμφάνισης και μετά τους χαρακτήρες.
    private static void printInvCharInventor(String[][] charInventor) {
        //System.out.println();
        //System.out.println("Table character Statistic");

        for (String[] row : charInventor) {
            if (row[0] != null) {
                System.out.println("       " + row[1] + "      ->       " + row[0]);
            }
        }

    }

    /**
     * Αυτήν η μέθοδος κάνει ταξινόμηση με βάση κάποια συγκεκριμένη στήλη.
     * @param array ο πίνακας που θέλουμε να ταξινομήσουμε.
     * @param col Η στήλη με βάση την οποία θέλουμε να γίνει η ταξινόμηση.
     * @param numOfCol ο αριθμός των στήλων που περιέχει ο πίνακας. (βασικά αυτό κάποια στιγμή θα το κάνω μεσα στην μέθοδο αυτόματα)
     * @param isOnlyNum True αν είναι η στήλη που θέλουμε να ταξινομήσουμε μόνο αριθμοί.
     */
    public static void sortByColumn(String[][] array, int col, int numOfCol, boolean isOnlyNum){
        for(int i = array.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if (array[j][col] != null && array[j+1][col] != null){
                    boolean canSwap;
                    if(isOnlyNum) {
                        int num1 = Integer.parseInt(array[j][col]);
                        int num2 = Integer.parseInt(array[j+1][col]);
                        canSwap = num1 > num2;
                    } else {
                        canSwap = array[j][col].compareTo(array[j+1][col]) > 0;
                    }
                    if (canSwap) {
                        for (int k = 0; k < numOfCol; k++) {
                            swapTwoDTable(j, j + 1, k, array);
                        }
                    }
                }
            }
        }
    }

    /**
     * Αυτήν η μέθοδος κάνει swap 2Δ πίνακες ανεξαρτήτου αριθμού στηλών.
     * Αν έχει 3 στήλες θα κάνεις swap 3 φορες, 1 για καθε στύλη αλλάζοντας το col.
     * @param i Ο δείκτης της 1ης for
     * @param j Ο δείκτης της 2ης for
     * @param col Πολύ σημαντικό! Είναι ο δείκτης για το σε ποιά στήλη να κάνει ανταλλαγή.
     * @param array ο πίνακας.
     */
    public static void swapTwoDTable(int i, int j, int col, String[][] array){
        String tmp = array[i][col];
        array[i][col] = array[j][col];
        array[j][col] = tmp;

    }

}

