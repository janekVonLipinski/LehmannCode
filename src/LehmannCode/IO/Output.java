package LehmannCode.IO;

import java.io.FileOutputStream;
import java.io.IOException;

public class Output {

    public void writeToFile(String fileName, String output) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.write(output.getBytes());
        fileOutputStream.close();
    }
}
