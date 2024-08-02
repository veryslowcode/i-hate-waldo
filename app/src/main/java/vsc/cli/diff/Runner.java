package vsc.cli.diff;

import vsc.cli.HelpException;
import vsc.cli.diff.Difference;

class Runner {
    public static void main(String[] args) {
        try {
            var difference = new Difference();
            difference.showDifference(args);
        } catch (HelpException e) {
            System.exit(0);
        } catch (Exception e) {
            var message = e.getMessage();
            if (message != null && message.trim() != "") {
                System.out.println(message);
            }
            System.exit(1);
        }
    }
}
