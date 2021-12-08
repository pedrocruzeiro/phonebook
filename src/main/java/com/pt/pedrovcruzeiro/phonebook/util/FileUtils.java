package com.pt.pedrovcruzeiro.phonebook.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

@Component
public class FileUtils {

    public InputStream retrieveInputStream(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);

        return resource.getInputStream();
    }

    public void addFileLinesToStringSet(InputStream inputStream, Set<String> stringSet ){

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        while(true) {
            try {
                if (!reader.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String line = reader.readLine();
                stringSet.add(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
