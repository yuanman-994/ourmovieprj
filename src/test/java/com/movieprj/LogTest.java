package com.movieprj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LogTest {

    public static void main(String[] args) {
        Path path = Paths.get("logs","log-sql.log");
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            for(int i= 0;i<lines.size();i++){
            System.out.println(lines.get(i));
            System.out.println("\n");}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
