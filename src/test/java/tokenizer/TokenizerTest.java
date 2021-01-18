package tokenizer;

import org.junit.Before;
import org.junit.Test;
import tokenizer.state.StateProcessException;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static tokenizer.OperationToken.*;

public class TokenizerTest {
    private Tokenizer tokenizer;

    @Before
    public void before() {
        tokenizer = new Tokenizer();
    }

    private void testTokenize(String expectedString, List<Token> actual) {
        List<Token> expected = tokenizer.tokenize(expectedString);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void emptyTest() {
        testTokenize("", List.of());
    }

    @Test
    public void numberTest() {
        testTokenize("100", List.of(new NumberToken(100)));
    }

    @Test
    public void operationTest() {
        testTokenize("10 - 20", List.of(new NumberToken(10), SUB, new NumberToken(20)));
    }

    @Test
    public void multipleOperationsTest() {
        testTokenize("1 + 2 - 3 * 4 / 5", List.of(new NumberToken(1),
                ADD, new NumberToken(2), SUB, new NumberToken(3),
                MUL, new NumberToken(4), DIV, new NumberToken(5)));
    }

    @Test
    public void bracketsTest() {
        testTokenize("(1 + 2) / 3", List.of(BracketToken.LEFT, new NumberToken(1), ADD, new NumberToken(2),
                BracketToken.RIGHT, DIV, new NumberToken(3)));
    }

    @Test(expected = StateProcessException.class)
    public void errorTest() {
        testTokenize("x", List.of());
    }

}