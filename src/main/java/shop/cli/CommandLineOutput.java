package shop.cli;

import java.io.PrintWriter;

public class CommandLineOutput {
    public static final String PROMPT = "> ";

    private PrintWriter writer;

    public CommandLineOutput(PrintWriter writer) {
        this.writer = writer;
    }

    public void showPrompt() {
        writer.print(PROMPT);
        writer.flush();
    }

    public void showLine(String line) {
        writer.println(line);
    }
}
