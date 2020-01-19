package shop.cli;

import java.io.PrintWriter;

public class CommandLineOutput {
    private PrintWriter writer;

    public CommandLineOutput(PrintWriter writer) {
        this.writer = writer;
    }

    public void showLine(String line) {
        writer.println(line);
    }
}
