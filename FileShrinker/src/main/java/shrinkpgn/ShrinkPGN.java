/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shrinkpgn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author mim
 */
public class ShrinkPGN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length<4){
            System.out.println("Got "+args.length+" arguments.");
            System.out.println("please provide arguments \"input\" \"output\" \"from\" \"to\"");
            System.out.println("eg if you only want the first 10 moves of a game");
            System.out.println("java -jar SchrinkPgn.jar c:\\input.txt c:\\output.txt \"11. \"  \"[Event\" ");
            System.exit(1);
        }
        String from = args[2];
        String to = args[3];
        String output = args[1];
        Path outputFile = Paths.get(output);
        String dir = args[0];
        Path file = Paths.get(dir);
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset);
             BufferedWriter writer = Files.newBufferedWriter(outputFile, charset);
             ) {
            String line;
            boolean startDelete =false;
            System.out.println("Input:"+dir);
            System.out.println("Output:"+output);
            System.out.println("from:"+from);
            System.out.println("To:"+to);
            while ((line = reader.readLine()) != null) {
                if(line.contains(from)){
                    line = line.substring(0,line.indexOf(from));
                    startDelete = true;
                    writeLineTo(line,writer);
                }
                if(line.contains(to)){
                    startDelete = false;
                }
                if(startDelete == false){
                       writeLineTo(line,writer);
                }
                
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static void writeLineTo(String line, BufferedWriter writer) throws IOException {
        writer.write(line+"\n");
    }

    

}
