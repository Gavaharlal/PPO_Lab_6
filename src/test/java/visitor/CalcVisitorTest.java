package visitor;

import org.junit.Assert;
import org.junit.Test;
import tokenizer.NumberToken;
import tokenizer.Token;

import java.util.List;

import static tokenizer.BracketToken.LEFT;
import static tokenizer.OperationToken.*;

public class CalcVisitorTest {
    private final CalcVisitor calculateTokenVisitor = new CalcVisitor();

    private void testCalculate(int expected, List<Token> actualTokens) {
        calculateTokenVisitor.visit(actualTokens);
        Assert.assertEquals(calculateTokenVisitor.getResult(), expected);
    }

    @Test(expected = VisitException.class)
    public void emptyTest() {
        testCalculate(0, List.of());
    }

    @Test
    public void numberTest() { // 1
        testCalculate(1, List.of(new NumberToken(1)));
    }

    @Test
    public void simpleTest() { // 1 - 2 - 3
        testCalculate(-4, List.of(new NumberToken(1), new NumberToken(2), SUB, new NumberToken(3), SUB));
    }

    @Test
    public void priorityTest() { // 1 - 2 * 3
        testCalculate(-5, List.of(new NumberToken(1), new NumberToken(2), new NumberToken(3), MUL, SUB));
    }

    @Test
    public void advancedTest() { // (23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2
        testCalculate(1279, List.of(new NumberToken(23), new NumberToken(10), ADD, new NumberToken(5), MUL,
                new NumberToken(3), new NumberToken(32), new NumberToken(5), ADD, MUL, new NumberToken(10),
                new NumberToken(4), new NumberToken(5), MUL, SUB, MUL, SUB, new NumberToken(8),
                new NumberToken(2), DIV, ADD));
    }

    @Test(expected = VisitException.class)
    public void errorTest() { // 1
        testCalculate(0, List.of(LEFT, new NumberToken(1)));
    }
}