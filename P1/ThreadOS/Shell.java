/**
 * @author Professor Erika Parsons
 * Editied by Timothy Caole 1/19/2024
 *
 * There's a separate folder with the ThreadOS .class files
 */

import java.io.*; // for ...
import java.util.*; // for ..
import java.lang.System;
import java.lang.Thread;


public class Shell extends Thread {

    // ---- constructors --- //
    public Shell()  {
    }

    /**
     * Using intellij IDE
     * [solved] Error w/ SysLib: cannot resolve symbol
     *     Solution: https://washington.zoom.us/rec/play/8jw0cAwQInMUM3V6sAmWRQJ_cSrfr5fqKN3OsEsa5N1MXG0_SFA8i1eJCPEwpVkYFdaXR4-W8NONcsBm.W4FjTN7pMo6cB-_d
     *     select File > Program Structure > Module > Dependencies > + > Path of project AKA P1> JARs or Directories >
     *         add the path to the ThreadOS folder then click apply and ok.
     */


    public void run() { // code is executed first, skips the main method, when the Thread is executed

        for (int line = 1;; line++) { // reads line from termainl

             String cmdLine = ""; // string is empty for user input from line

            do {
                StringBuffer inputBuf = new StringBuffer();
                SysLib.cerr("shell[" + line + "]% ");
                SysLib.cin(inputBuf); // gets the user's input
                cmdLine = inputBuf.toString(); // users input is converted to string
            } while (cmdLine.length() == 0); // user input value
            String[] args = SysLib.stringToArgs(cmdLine); //users input is stored in an array args
            int first = 0; // used for generateCmd para

            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(";") || args[i].equals("&") || i == args.length - 1) {
                    String[] command = generateCmd(args, first, (i == args.length - 1) ? i + 1 : i);
                    if (command != null) { // checks if command is not empty

                        // ----- task to implement ---- //

                        // Part 1: check if command[0] is “exit”. If so, get terminated
                        if (command[0].equals("exit")) {
                            SysLib.exit(); // terminated
                            return;
                        }
                        // Part 2: otherwise, pass command to SysLib.exec( ) if args[i]=“&” don’t call SysLib.join( ), Otherwise (i.e., “;”), keep calling
                        // SysLib.join( )

                        // for asynchronous commands
                        SysLib.exec(command); // command is done executing

                        if (args[i] != "&") { // for  synchronous  commands
                            SysLib.join();
                        }
                    }
                    first = i + 1; // goes to the next command
                }
            } // end of for loop
        } //
    } // end of run method

    /**
     * Method generateCMD, creates a commandy arry from the parameters provided.
     *
     * @param args the array of arguments
     * @param first The first index of the array command
     * @param last the last index of the rarray commandy
     * @return creates an array called command
     */

    private String[] generateCmd(String[] args, int first, int last) {
        // check if sub array args is empty
        if (last - first <= 0) {
            return null;
        }

        // size  for sub array to look into each command
        int size = last - first ; // <- recommend!!  or could hardcode it, size = 3
        String[] command = new String[size];

        for (int i = 0 ; i < size ; i++){
            command[i] = args[i + first];
            // agrs [i + first] is used as a reference

            // for each section
            // _ _ _ ; _ _ _ ;  _ _ _ ;
            //         ^     ^
            //        First  Last
        }
        return command; 
    } // end of generateCmd Method

        /**
         * @param args an array of arguments
         */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Shell shell = new Shell();
        shell.run();

    } // end of main method
} // end of shell class


