package com.github.gabrielalbernazdev.infra.extractor;

import java.io.*;
import java.util.List;

public class FilePartExtractor {
    public static String extractFileContent(InputStream fileInputStream) throws IOException {
        StringBuffer content = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line;
            while((line = br.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }

    public static List<String> extractFileLines(InputStream fileInputStream) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            return br.lines().toList();
        }
    }
}
