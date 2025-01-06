package Projects;

/**
 *  Αυτό το προγραμματάκι είναι ένα παιχνίδι Τρίλιζα.
 *  Ελέγχει την σειρά των παιχτών, ελέγχει την είσοδο των τιμών
 *  και να μην μπορεί να επιλέξει ήδη πιασμένο κουτί κάποιος παίχτης.
 *
 */

import java.util.InputMismatchException;
import java.util.Scanner;
public class ProjectFour {
    public static void main(String[] args) {

        String player1 = "O";
        String player2 = "X";
        int choose1 = -1;
        int choose2 = -1;
        String[][] trilizaTable = new String[3][3];
        //boolean isWinner = false;
        Scanner cs1 = new Scanner(System.in);

        for (int i = 0; i < trilizaTable.length; i++) {
            for (int j = 0; j < trilizaTable.length; j++) {
                trilizaTable[i][j]= " ";
            }
        }

        System.out.println("Παιχνίδι Τρίλιζα");
        System.out.println("Ο παίχτης 1 έχει τα Ο και ο παίχτης 2 τα Χ");
        System.out.println("Οι επιλογές σας είναι οι παρακάτω");
        System.out.println("|-----------|");
        System.out.println("|0.0|0.1|0.2|");
        // System.out.println("|-----------|");
        System.out.println("|1.0|1.1|1.2|");
        // System.out.println("|-----------|");
        System.out.println("|2.0|2.1|2.2|");
        System.out.println("|-----------|");

        try(Scanner sc = new Scanner(System.in)){
            int count = 0;
            while (true){
                count++;
                //System.out.println("count is: " + count); //Debug print
                if ((count % 2) != 0) {
                    do {
                        System.out.println("Παίχτη 1 διάλεξε γραμμή απο 0 εώς 2");
                        choose1 = sc.nextInt();
                    } while (choose1 < 0 || choose1 > 2);
                    do {
                        System.out.println("Παίχτη 1 διάλεξε στήλη απο 0 εώς 2");
                        choose2 = sc.nextInt();
                    } while (choose2 < 0 || choose2 > 2);

                    importIn(choose1, choose2, player1, trilizaTable, cs1);

                    if (count > 4){
                        if (isTriliza(choose1,choose2, player1, trilizaTable)){
                            System.out.println("Ο παίχτης 1 είναι ο νικητής!");
                            break;
                        }
                    }

                    if (count >= 9) {
                        System.out.println("Το παιχνίδι τελείωσε με ισοπαλία!");
                        break;
                    }
                } else {

                    do {
                        System.out.println("Παίχτη 2 διάλεξε γραμμή απο 0 εώς 2");
                        choose1 = sc.nextInt();
                    } while (choose1 < 0 || choose1 > 2);
                    do {
                        System.out.println("Παίχτη 2 διάλεξε στήλη απο 0 εώς 2");
                        choose2 = sc.nextInt();
                    } while (choose2 < 0 || choose2 > 2);
                    importIn(choose1, choose2, player2, trilizaTable, cs1);

                    if (count > 4){ //Δεν υπάρχει λόγος να γίνεται έλεγχος αν δεν έχουν παίξει τουλάχιστον
                        // 2 φορές ο καθένας.
                        if (isTriliza(choose1,choose2, player2, trilizaTable)){
                            System.out.println("Ο παίχτης 2 είναι ο νικητής!");
                            break;
                        }
                    }

                    if (count >= 9) {
                        System.out.println("Το παιχνίδι τελείωσε με ισοπαλία!");
                        break;
                    }
                }



                //Εκτύπωση του πίνακα της τρίλιζα
                for (String[] row : trilizaTable ) {
                    System.out.print("| ");
                    for (String col : row){
                        System.out.print(col + " | ");
                    }
                    System.out.println();
                    System.out.println("-------------");
                }

            }
        } catch (NumberFormatException e){
            System.err.println("Εισάγετε έναν ακέραιο μεταξύ 0 εώς 2");
        }

        cs1.close(); // Κλείνω τον σκάνερ με εντολή για σιγουριά γιατί είναι εκτός try & catch.
    }

    /**
     * Αυτήν η μέθοδος δημιουργεί το σχήμα του παιχνιδιού εισάγωντας της επιλογές
     * απο τους παίχτες. Κάνει έλεγχο τιμών και έλεγχο ότι κάποιος δεν θα πατήσει πάνω
     * σε πιασμένη θέση.
     * @param chooseA Επιλογή για την γραμμή.
     * @param chooseB Επιλογή για την στήλη.
     * @param player Το σύμβολο του παίχτη.
     * @param trilizaTable Ο πίνακς
     * @param cs Βάζω τον σκάνερ cs1 γιατί όταν έμπενε στο else αφού εκανε την δουλειά
     *           μετά έπιανε στο catch σφάλμα που είχε να κάνει με το σκάνερ και ετσι λύθηκε
     *           το πρόβλημα.
     */
    private static void importIn(int chooseA, int chooseB, String player, String[][] trilizaTable, Scanner cs) {
        Scanner sc = new Scanner(System.in);
        if(trilizaTable[chooseA][chooseB].equals(" ")){
            trilizaTable[chooseA][chooseB] = player;
        } else {
            System.out.println("This place have: " + trilizaTable[chooseA][chooseB]);

            int choose1 = -1;
            int choose2 = -1;

                try{

                    do {
                        System.out.println("Διάλεξε γραμμή απο 0 εώς 2");
                        choose1 = sc.nextInt();
                    } while (choose1 < 0 || choose1 > 2);

                    do {
                        System.out.println("Διάλεξε στήλη απο 0 εώς 2");
                        choose2 = sc.nextInt();
                    } while (choose2 < 0 || choose2 > 2);

                    sc.nextLine();

                } catch(InputMismatchException e){
                    System.err.println("Μή έγκυρη τιμή! Βάλτε μόνο ακέραια νούμερα. ");
                    throw e;
                }

            // Η μέθοδος ξανακαλεί τον εαυτό της για να μπούν τα καινούργια δεδομένα.
            importIn(choose1, choose2, player, trilizaTable, cs);
        }
    }

    /**
     * Αυτήν η μέθοδος κάνει τους ελέγχους αν υπάρχει τρίλιζα απο το σημείο που δίνει ο κάθε παίχτης
     * βάση του συμβόλου που χρησιμοποιεί.
     * @param choose1 θέση στον άξονα χ
     * @param choose2 θέση στον αξονα ψ
     * @param player Αυτό είναι το σύμβολο του καθε παίκτη Ο η Χ
     * @param trilizaTable ο πίνακας της τρίλιζας (το σχήμα)
     * @return true αν έχουμε τρίλιζα αλλιώς false.
     */
    private static boolean isTriliza(int choose1, int choose2, String player, String[][] trilizaTable){
        trilizaTable[choose1][choose2] = player;
        //pano aristera
        if(choose1 == 0 && choose2 == 0) {
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1+2][choose2])){
                return true;
            }
            //elegxo diagonio
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1+2][choose2+2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+2])){
                return true;
            }
        }
        //pano deksia
        if (choose1 == 0 && choose2 == 2){
            //Elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1+2][choose2])){
                return true;
            }
            //Elegxo diagonio
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1+2][choose2-2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-2])){
                return true;
            }
        }
        //kato aristera
        if(choose1 == 2 && choose2 == 0){
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+2])){
                return true;
            }
            //elegxo diagonia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-2][choose2+2])){
                return true;
            }
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-2][choose2])){
                return true;
            }
        }
        //kato deksia
        if (choose1 == 2 && choose2 == 2) {
            //elegxo diagonia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-2][choose2-2])){
                return true;
            }
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-2][choose2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-2])){
                return true;
            }
        }
        //kentro
        if (choose1 == 1 && choose2 == 1){
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-1])){
                return true;
            }
            //elegxo pano diagonia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2+1])){
                return true;
            }
            //elegxo kato diagonia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2+1])){
                return true;
            }


        }
        //Mesi aristera
        if (choose1 == 1 && choose2 == 0){
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+2])){
                return true;
            }
        }
        //Mesi deksia
        if (choose1 == 1 && choose2 == 2){
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-2])){
                return true;
            }
        }
        //Mesi pano
        if (choose1 == 0 && choose2 == 1){
            //elegxo katheti
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1+1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1+2][choose2])){
                return true;
            }
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-1])){
                return true;
            }
        }
        //Mesi Kato
        if (choose1 == 2 && choose2 == 1){
            //elegxo orizontia
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2+1]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1][choose2-1])){
                return true;
            }
            //elegxo katheta
            if(trilizaTable[choose1][choose2].equals(trilizaTable[choose1-1][choose2]) && trilizaTable[choose1][choose2].equals(trilizaTable[choose1-2][choose2])){
                return true;
            }
        }
        return false;
    }
}
