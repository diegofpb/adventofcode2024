package es.diegofpb.utils;


import java.io.InputStream;

public class SharedUtils {

    public static InputStream loadFile(final ClassLoader classLoader, final String fileName) {
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found" + fileName);
        }
        return inputStream;
    }

}