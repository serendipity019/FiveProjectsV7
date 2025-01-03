package Projects;

public class ProjectTwo {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] locMax = new int[arr.length];
        int[] subArray;

        locMax[0]=arr[0];
        for( int i = 1; i< arr.length; i++) {
            locMax[i] = Math.max((locMax[i-1]+arr[i]),arr[i]);
            if (){
                //emeina sto seimio pou psaxno na bro pos tha vriskei ton ypopinaka
            }
        }

        printTable(locMax);
    }

    public static void printTable(int[] array){
        for (int el : array){
            System.out.print(el + " ");
        }
    }
}
