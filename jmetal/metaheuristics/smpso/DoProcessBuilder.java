package jmetal.metaheuristics.smpso;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class DoProcessBuilder {

    public static void main(String args[]) throws IOException {

        File imageFile = new File("/tmp/7ThnASzg.jpg");

        double bytes = imageFile.length();

        System.out.println("File Size: " + String.format("%.2f", bytes / 1024) + "kb");

        try {

            BufferedImage image = ImageIO.read(imageFile);
            System.out.println("Width: " + image.getWidth());
            System.out.println("Height: " + image.getHeight());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        if (args.length <= 0) {
//            System.err.println("Need command to run");
//            System.exit(-1);
//        }
//
//        Process process = new ProcessBuilder(args).start();
//        InputStream is = process.getInputStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//
//        System.out.printf("Output of running %s is:",
//                Arrays.toString(args));
//
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
    }
}
