package visitor;

import tokenizer.Token;

import java.util.List;

public interface TokenVisitor {

    void visit(Token token);

    void visit(List<Token> tokens);
}
