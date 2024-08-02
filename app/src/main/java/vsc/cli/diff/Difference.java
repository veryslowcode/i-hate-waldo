package vsc.cli.diff;

import vsc.cli.Help;
import vsc.cli.HelpException;
import vsc.cli.arguments.Parser;
import vsc.cli.diff.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Difference {
    // Help information
    Map<String, String> possibleArguments;
    Map<String, String> possibleFlags;

    String compare;
    String to;

    // Color output
    boolean color = true;
    String errorPrefix = "\033[31m";
    String diffPrefix = "\033[33m";
    String addPrefix = "\033[32m";
    String resetPrefix = "\033[0m";

    public Difference() { 
        this.possibleArguments = new HashMap<String, String>();
        this.possibleArguments.put("compare", "The comparison file (the one displayed)");
        this.possibleArguments.put("to", "The file compared against");

        this.possibleFlags = new HashMap<String, String>();
        this.possibleFlags.put("h", "Display this menu");
        this.possibleFlags.put("n", "Disable color output");
    }

    public void showDifference(String[] args) throws Exception {
        this.handleFlags(args);
        this.handleArguments(args);
        
        List<String> output = this.handleFiles();
        var buffer = new Buffer(output);
        buffer.displayDifference();
    }
    
    void handleArguments(String[] args) throws Exception {
        var arguments = Parser.parseArguments(args);
        
        this.compare = arguments.get("compare");
        this.to = arguments.get("to");

        if (this.compare == null || this.to == null) {
            this.handleError("Invalid or missing arguments");
        }
    }

    void handleError(String message) throws Exception {
        System.out.println("An error occurred:");
        System.out.println("\t" + this.errorPrefix + message + this.resetPrefix);
        this.handleHelp();
        throw new Exception();
    }

    List<String> handleFiles() throws Exception {
        List<String> compareLines = Files.readAllLines(Paths.get(this.compare));
        List<String> toLines = Files.readAllLines(Paths.get(this.to));

        for (int i = 0; i < compareLines.size(); i++) {
            if (i < toLines.size()) {
                if (compareLines.get(i) != toLines.get(i)) {
                    var update = this.diffPrefix + "!= " + compareLines.get(i) + this.resetPrefix;
                    compareLines.set(i, update);
                }           
            } else {
                var update = this.addPrefix + "+ " + compareLines.get(i) + this.resetPrefix;
                compareLines.set(i, update);
            }
        }

        return compareLines;
    }

    void handleFlags(String[] args) throws HelpException {
        var flags = Parser.parseFlags(args);

        if (flags.get("n") != null) {
            this.color = false;
            this.errorPrefix = "";
            this.diffPrefix = "";
            this.addPrefix = "";
            this.resetPrefix = "";
        }

        if (flags.get("h") != null) {
            this.handleHelp();
            throw new HelpException();
        }
    }

    void handleHelp() {
        var help = new Help(this.possibleArguments, this.possibleFlags);
        help.color = this.color;
        help.displayHelp();
    }
}
