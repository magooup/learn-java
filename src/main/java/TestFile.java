import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFile {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        File file = new File(filePath);
        System.out.println(file.length());
        FileOutputStream fos = new FileOutputStream(filePath, true);
        System.out.println(fos.getChannel().position());
        fos.close();
    }
}
