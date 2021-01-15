import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hhe");
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize("(32 + 5) * (10 - 4 * 5) + 8 / 2");

        System.out.println(tokens);
    }
}
