package org.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;

@Service
public class EditFiles {
    String file1 = Paths.get("src/main/resources/file1.txt").toString();
    String file2 = Paths.get("src/main/resources/file2.txt").toString();

    @PostConstruct
    public void PostConstruct() throws IOException{
        var bufferedWriter = new BufferedWriter(new FileWriter(file2));

        if (new File(file1).exists()){
            var bufferedReader = new BufferedReader(new FileReader(file1));
            var strBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null){
                strBuilder.append(line);
            }
            bufferedWriter.write(String.valueOf(strBuilder.hashCode()));
            System.out.println("The text from file 1 was transferred to video hash in 2");
            bufferedReader.close();
        }
        else        {
            System.out.println("The first file is missing");
            bufferedWriter.write("null");
        }
        bufferedWriter.close();
    }

    @PreDestroy
    public void PreDestroy(){
        if(new File(file1).delete()){
            System.out.println("First file deleted");
        }
    }
}