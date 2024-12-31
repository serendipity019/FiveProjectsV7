package Projects;

import java.util.Scanner;

public class ProjectFive {
    static Boolean[][] theaterTable= new Boolean[30][12];
    public static void main(String[] args) {
        int bookOrCancel = 0;
        Scanner sc = new Scanner(System.in);

        //Arxikopoioume ton pinaka me falses
        for (int i = 0; i < theaterTable.length; i++) {
            for (int j = 0; j < theaterTable[i].length; j++) {
                theaterTable[i][j] = false;
            }
        }

        do {
            try {
                System.out.println("Αν θέλετε να κάνετε κράτηση επιλέξτε 1\n" + "Αν θέλετε να κάνετε ακύρωση επιλέξτε 2.");
                bookOrCancel = sc.nextInt();

            } catch (NumberFormatException e) {
                System.err.println("Εισάγετε αριθμό. 1 για κράτηση ή 2 για ακύρωση θέσης");
            }
        } while (bookOrCancel != 1 && bookOrCancel != 2);
        sc.nextLine(); // katharizo ton buffer tis Scanner

        char column = 'A';
        int row = 1;
        String seat;
        while (true) {
            try {
                System.out.println("Επιλέξτε θέση απο A εώς L (Αγγλικοί κεφαλαίοι χαρακτήρες) για στήλη και απο 1 εώς 30 για την σειρά. ");
                System.out.println("π.χ για 3η στήλη, 2η σειρά εισάγετε C2.");
                seat = sc.nextLine().trim().toUpperCase();
                if (seat.length() < 2 || seat.length() > 3) {
                    throw new IllegalArgumentException("Δεν εισάγατε κατάληλη θέση, προσπαθήστε ξανά!");
                }
                column = seat.charAt(0);
                if (seat.length() == 3){
                String sRow = seat.substring(1,3);
                row = Integer.parseInt(sRow);
                } else {
                    row = Character.getNumericValue(seat.charAt(1));
                }
                if ((column > 'L') || (column < 'A') || (row > 30 || row < 1)) {
                    throw new IllegalArgumentException("Δεν εισάγατε κατάληλη θέση, προσπαθήστε ξανά!");

                }

                break;
            } catch (IllegalArgumentException e){
                System.err.println("Λάθος Εισαγωγή! " + e.getMessage());
            }catch (Exception e) {
                System.err.println("Ουπς κάτι πήγε στραβά! " + e.getMessage());
            }
        }

        if (bookOrCancel == 1) {
            book(column, row);
        } else{
            cancel(column, row);
        }


       // doubleTablePrinter(theaterTable);
        sc.close();
    }




    private static void book(char column, int row){
        int colNum = column % 65;
        if (theaterTable[row-1][colNum]) {
            System.out.println("η θέση: "+ column + row + " είναι κλεισμένη." );
        } else {
            theaterTable[row-1][colNum] = true;
            System.out.println("η θέση: "+ column + row + " έκλεισε επιτυχώς." );
        }
    }

    private static void cancel(char column, int row){
        int colNum = column % 65;
        if (theaterTable[row-1][colNum]) {
            theaterTable[row-1][colNum] = false;
            System.out.println("η θέση: "+ column + row + " ακυρώθηκε επιτυχώς." );
        } else {
            System.out.println("η θέση: "+ column + row + " δεν είναι κλεισμένη. Δοκιμάστε ξανά!" );
        }

    }

    public static void doubleTablePrinter(Boolean[][] table) {
        System.out.println(); // gia na afinei mia keni grammi oste na ksexorizei sigoura
        // apo pithanon proigoumena
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++){
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
