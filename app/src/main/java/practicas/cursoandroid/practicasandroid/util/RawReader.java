package practicas.cursoandroid.practicasandroid.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class RawReader {

    public static String readRaw(InputStream is) {
        try {
            Scanner read = new Scanner(is);
            StringBuilder sb = new StringBuilder();
            while(read.hasNext()) {
                sb.append(read.nextLine());
            }
            return sb.toString();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
