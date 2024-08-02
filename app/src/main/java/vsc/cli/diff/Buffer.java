package vsc.cli.diff;

import java.util.List;
import java.util.Scanner;

public class Buffer {
    List<String> output;

    public Buffer(List<String> output) { 
        this.output = output;
    }

    public void displayDifference() {
        var scanner = new Scanner(System.in);
        var quit = false;

        for (var line : output) {
            System.out.println(line);
        }
    }
}
