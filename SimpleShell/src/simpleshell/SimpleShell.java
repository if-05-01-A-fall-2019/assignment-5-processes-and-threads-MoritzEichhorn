/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleshell;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author moritz
 */
public class SimpleShell {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        boolean stay = true;
        while (stay) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter commands (e for exit): ");
                String input = scanner.nextLine();
                String[] commands = input.split("&");

                for (String command : commands) {
                    if (!command.equals("e")) {
                        Process p = Runtime.getRuntime().exec(command);
                        p.waitFor();
                        InputStream s = p.getInputStream();
                        int b = s.read();
                        while (b != -1) {
                            System.out.print((char) b);
                            b = s.read();
                        }
                        System.out.println();
                    } else {
                      stay = false;  
                    }
                }
            } catch (Exception e) {
                System.out.println("Couldn't execute this command!");
            }

        }
    }
}
