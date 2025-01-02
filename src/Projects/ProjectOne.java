package Projects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectOne {



    public static void main(String[] args) throws Exception {
        String inFilePath = "~/coding-factory/'java exercises'/FiveProjectsV7/files/six_Number.txt";
        String outFilePath = "~/coding-factory/'java exercises'/FiveProjectsV7/files/six_NumberOutput.txt";
        File inputFile = new File(inFilePath);
        File outputFile = new File(outFilePath);
        ArrayList<Integer> numbers = readFile(inputFile); // Δημιουργώ την λίστα αριθμών με την μέθοδο readFile.
        //int [] array = {1, 25, 14, 45, 23, 2, 3, 12, 28, 31, 33, 36, 44, 43, 16, 11, 7, 5};

        // Εδώ καλώ την μέθοδο για να κάνω την ArrayList<Integer> σε int[]
        int[] intNum = convArrayListToIntArr(numbers);
        //Ταξινομώ τον πίνακα
        sort(intNum);
        /*Δημιουργώ τις ν εξάδες και τις φιλτράρω.
        H παρακάτω μέθοδος καλή επίσης μια μέθοδο για να περάσει
        δεδομένα στο αρχείο εξόδου. */
        combinations(intNum, outputFile);
    }

    // Ελέγχουμε αν έχουμε παραπάνω απο 3 νούμερα στην ίδια δεκάδα.
    private static boolean moreThanThreeInSameTen(int[] array){
        int[] distribution = new int[5];
        int count = 0;
        for (int num : array) {
            distribution[num / 10] += 1;
        }

        for (int i = 0; i < distribution.length; i++) {
            if (distribution[i] > 3){
                count++;
            }
        }

        return (count != 0); // An einai to count diaforo 0 exoume parapano apo 3 stin idia dekada. epistrefei true.
    }

    // Ελέγχουμε αν έχουμε παραπάνω απο 3 νούμερα με τον ίδιο λήγοντα.
    private static boolean moreThanTwoSameLast(int[] array){
        int[] distribution = new int[10];
        int count = 0;
        for (int num : array) {
            distribution[num % 10] += 1;
        }

        for (int i = 0; i < distribution.length; i++) {
            if (distribution[i] > 3){
                count++;
            }
        }

        return (count != 0); // An periexei pano apo 3 noymera me ton idio ligonta epistrefei true.
    }

    //elegxoume an exoume to polu 4 artious
    private static boolean moreThanFourEvens(int[] array){
        int evens = 0;
        for (int num : array){
            if (num % 2 == 0 ) {
                evens++;
            }
        }
        return evens > 4; // Epistrefei true an parapano apo 4
    }

    //elegxoume an exoume to polu 4 peritous
    private static boolean moreThanFourOdds(int[] array){
        int odds = 0;
        for (int num : array){
            if (num % 2 == 0 ) {
                odds++;
            }
        }
        return odds > 4; // Epistrefei true an parapano apo 4
    }

    //Ελέγχουμε αν έχουμε πάνω απο 2 συνεχόμενους αριθμούς
    private static boolean contMoreThanTwo(int[] array){
        int count = 0;
        for (int i = 0; i < array.length-2; i++) {
            if ((array[i] + 1 == (array[i+1])) && (array[i] + 2) == array[i+2]){
                count++;
            }
            if (count != 0) break; // Το κόβω πρόωρα γιατι ουτος η άλλος δεν θα πληροί τα κριτήρια
        }
        return (count != 0) ; //επιστρέφει true αν δεν είναι πάνω 2 απο συνεχείς.
    }

    //Kano mia methodo gia tin taksinomisi. methodos thisalida
    public static void sort(int[] array){
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j+1]) swap(j, j+1, array);
            }
        }
    }

    //Edo dimiourgo tin swap pou tin xreiazete i sort
    public  static void swap(int i, int j, int[] array){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Μέθοδος που διαβάζει το αρχείο εισόδου.
    public static ArrayList<Integer> readFile(File inputFile) throws Exception {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try(Scanner sc = new Scanner(inputFile)){
            while (sc.hasNextInt()){
                numbers.add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        if (numbers.size() >= 6 && numbers.size() <= 49 ) {
            return numbers;
        } else {
            throw new Exception("Το αρχείο πρέπει να περιέχει 6 εώς 49 αριθμούς");
        }
    }

    /**
     * Αυτήν η μέθοδος Δημιουργεί συνδιασμούς n αριθμών.
     * Τους φιλτράρει αν πληρούν κάποιες προϋποθέσεις.
     * Και καλή μια μέθοδο για να γράψει τους αριθμούς σε ένα εξωτερικό αρχείο.
     * @param array Δέχετε ως είσοδο έναν πίνακα ακεραίων.
     * @param outputFile Το αρχείο εξόδου το οποίο το μεταβιβάζει στην επόμενη μέθοδο.
     */
    private static void combinations(int[] array, File outputFile){
        final int n = 6; // edo simaini sundiasmoi 6 arithmon
        int[] sixNumbers = new int[6];
        for (int i = 0; i <= array.length - n; i++) {
            for (int j = 0; j <= array.length - n + 1; j++) {
                for (int k = 0; k <= array.length - n + 2; k++) {
                    for (int l = 0; l <= array.length - n + 3; l++) {
                        for (int m = 0; m <= array.length - n + 4; m++) {
                            for (int o = 0; o <= array.length - n + 5; o++) {
                                sixNumbers[0] = array[i];
                                sixNumbers[1] = array[j];
                                sixNumbers[2] = array[k];
                                sixNumbers[3] = array[l];
                                sixNumbers[4] = array[m];
                                sixNumbers[5] = array[o];
                                //Εδώ κάνουμε έλεγχο αν πληρούνται οι προϋποθέσεις.
                                if(!moreThanFourEvens(sixNumbers) && !moreThanFourOdds(sixNumbers)
                                    && !contMoreThanTwo(sixNumbers) && !moreThanTwoSameLast(sixNumbers)
                                    && !moreThanThreeInSameTen(sixNumbers)) {
                                    //Καλεί την μέθοδο για να το βάλει στο άρχειο.
                                    printFile(outputFile, i, j, k, l, m, o, sixNumbers);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Εξάγει σε αρχείο τα δεδομένα που παίρνει απο την μέθοδο combination.
     * @param outputFile To εξαγώγημο αρχείο.
     * @param i Δείκτες των for απο την combination.
     * @param j             >>
     * @param k             >>
     * @param l             >>
     * @param m             >>
     * @param o             >>
     * @param array ο πίνακας με τους 6 ακεραίους που στέλνει η combination.
     */
    private static void printFile(File outputFile, int i ,int j, int k ,int l, int m, int o, int[] array){
        try(PrintStream ps = new PrintStream(outputFile)){
            ps.printf("%d\t%d\t%d\t%d\t%d\t%d\n",
                    array[i],
                    array[j],
                    array[k],
                    array[l],
                    array[m],
                    array[o]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Αυτήν η μέθοδος μετατρέπει την λίστα ακεραίων σε πίνακα ακεραίων.
     * Μας βοηθάει για να δουλεύουμε με δεδομένα που γνωρίζουμε καλύτερα
     * πώς τα χειριζόμαστε.
     * @param arrayList Δέχεται μια λίστα με ακεραίους
     *
     */
    public static int[] convArrayListToIntArr(ArrayList<Integer> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] =arrayList.get(i);
        }
        return array;
    }
}
