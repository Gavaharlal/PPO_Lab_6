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
        for (int i = 0; i < tokens.size(); i++) {
            tokens.get(i).accept(this);
            if (i != tokens.size() - 1) {
                printWriter.print(' ');
            }
        }
    }
}
