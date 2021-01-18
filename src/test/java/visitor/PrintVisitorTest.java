package visitor;

import org.junit.Before;
import org.junit.Test;
import tokenizer.NumberToken;
import tokenizer.Token;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static tokenizer.BracketToken.LEFT;
import static tokenizer.BracketToken.RIGHT;
import static tokenizer.OperationToken.DIV;
import static tokenizer.OperationToken.MUL;

public class PrintVisitorTest {
    private StringWriter stringWriter;
    private PrintVisitor printTokenVisitor;

    @Before
    public void before() {
        stringWriter = new StringWriter();
        printTokenVisitor = new PrintVisitor(new PrintWriter(stringWriter));
    }

    private void testPrint(String expectedString, List<Token> actualTokens) {
        printTokenVisitor.visit(actualTokens);
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void emptyTest() {
        testPrint("", List.of());
    }

    @Test
    public void numberTest() {
        testPrint("NUMBER(1)", List.of(new NumberToken(1)));
    }

    @Test
    public void advancedTest() {
        testPrint("LEFT NUMBER(42) MUL NUMBER(4) RIGHT DIV NUMBER(1098)",
                List.of(LEFT, new NumberToken(42), MUL, new NumberToken(4), RIGHT, DIV, new NumberToken(1098)));
    }
}
