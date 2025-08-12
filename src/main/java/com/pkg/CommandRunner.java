package com.pkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandRunner {
    public int run(String... command) throws Exception {
        var builder = new ProcessBuilder(command);
        Process process = builder.start();
        var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

        int exitCode = process.waitFor();
        return exitCode;
    }
}
