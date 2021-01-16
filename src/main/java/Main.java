import tokenizer.Token;
import tokenizer.Tokenizer;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hhe");
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize("(32 + 5) * (10 - 4 * 5) + 8 / 2");
//        List<Token> tokens = tokenizer.tokenize("2 +  2");

        ParserVisitor parserVisitor = new ParserVisitor();
        parserVisitor.visit(tokens);

        PrintVisitor printVisitor = new PrintVisitor();
        printVisitor.visit(tokens);


        System.out.println(tokens);
        System.out.println(parserVisitor.getResult());
    }
}
