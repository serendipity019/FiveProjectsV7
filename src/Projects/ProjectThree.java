package Projects;

import java.io.File;

public class ProjectThree {
    public static void main(String[] args) {
        String homeDir = System.getProperty("user.home");
        String inFilePath =homeDir + "/coding-factory/java exercises/FiveProjectsV7/files/six_Numbers.txt";
        String outFilePath =homeDir + "/coding-factory/java exercises/FiveProjectsV7/files/six_NumbersOutput.txt";
        File inputFile = new File(inFilePath);
        File outputFile = new File(outFilePath);

        if (!inputFile.exists()){
            System.err.println("Το αρχείο εισόδου δεν υπάρχει: " + inputFile.getAbsolutePath());
            return;
        }
    }
}
