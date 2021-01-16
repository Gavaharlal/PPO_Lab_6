import tokenizer.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hhe");
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize("(32 + 5) * (10 - 4 * 5) + 8 / 2"); // -366
//        List<Token> tokens = tokenizer.tokenize("(10 - 4 * 5)"); // -364
//        List<Token> tokens = tokenizer.tokenize("2 +  2");

        ParserVisitor parserVisitor = new ParserVisitor();
        parserVisitor.visit(tokens);

        PrintVisitor printVisitor = new PrintVisitor();
        printVisitor.visit(tokens);

        CalcVisitor calcVisitor = new CalcVisitor();
        calcVisitor.visit(parserVisitor.getResult());


        System.out.println(tokens);
        System.out.println(parserVisitor.getResult());
        System.out.println(calcVisitor.getResult());
    }
}
