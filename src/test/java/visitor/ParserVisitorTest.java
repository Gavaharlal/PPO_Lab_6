package visitor;

import org.junit.Assert;
import org.junit.Test;
import tokenizer.NumberToken;
import tokenizer.Token;

import java.util.List;

import static tokenizer.BracketToken.LEFT;
import static tokenizer.BracketToken.RIGHT;
import static tokenizer.OperationToken.*;

public class ParserVisitorTest {
    private final ParserVisitor parseTokenVisitor = new ParserVisitor();

    private void testParse(List<Token> expected, List<Token> actualTokens) {
        parseTokenVisitor.visit(actualTokens);
        List<Token> actual = parseTokenVisitor.getResult();
        Assert.assertArrayEquals(actual.toArray(), expected.toArray());
    }

    @Test
    public void emptyTest() {
        testParse(List.of(), List.of());
    }

    @Test
    public void simpleTest() {
        testParse(
                List.of(new NumberToken(1), new NumberToken(2), SUB, new NumberToken(3), ADD),
                List.of(new NumberToken(1), SUB, new NumberToken(2), ADD, new NumberToken(3)));
    }

    @Test
    public void priority() {
        testParse(
                List.of(new NumberToken(1), new NumberToken(2), new NumberToken(3), MUL, SUB),
                List.of(new NumberToken(1), SUB, new NumberToken(2), MUL, new NumberToken(3)));
    }

    @Test
    public void advancedTest() {
        testParse(
                List.of(new NumberToken(32), new NumberToken(5), ADD, new NumberToken(10), new NumberToken(4),
                        new NumberToken(5), MUL, SUB, MUL, new NumberToken(8), new NumberToken(2), DIV, ADD),
                List.of(LEFT, new NumberToken(32), ADD, new NumberToken(5), RIGHT, MUL, LEFT, new NumberToken(10), SUB,
                        new NumberToken(4), MUL, new NumberToken(5), RIGHT, ADD, new NumberToken(8), DIV, new NumberToken(2)));
    }

    @Test(expected = VisitException.class)
    public void errorTest() {
        testParse(List.of(), List.of(RIGHT));
    }

}