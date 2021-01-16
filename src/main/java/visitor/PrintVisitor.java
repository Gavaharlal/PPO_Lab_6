package visitor;


import tokenizer.Token;

import java.io.PrintWriter;
import java.util.List;

public class PrintVisitor implements TokenVisitor {

    private final PrintWriter printWriter;

    public PrintVisitor() {
        this(new PrintWriter(System.out));
    }

    public PrintVisitor(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    @Override
    public void visit(Token token) {
        printWriter.print(token.toString());
    }

    @Override
    public void visit(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
            printWriter.print(' ');
        }
        printWriter.print('\n');
        printWriter.flush();
    }
}
