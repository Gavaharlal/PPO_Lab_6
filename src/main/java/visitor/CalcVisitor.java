package visitor;

import tokenizer.BracketToken;
import tokenizer.NumberToken;
import tokenizer.OperationToken;
import tokenizer.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CalcVisitor extends AbstractVisitor {
    private final Deque<Integer> stack = new ArrayDeque<>();
    private int result;

    @Override
    protected void doVisit(NumberToken token) {
        stack.push(token.getNumber());
    }

    @Override
    protected void doVisit(OperationToken token) {
        if (stack.size() < 2) {
            throw new VisitException();
        }
        Integer second = stack.pop();
        Integer first = stack.pop();

        int res;

        switch (token) {
            case ADD:
                res = first + second;
                break;
            case SUB:
                res = first - second;
                break;
            case MUL:
                res = first * second;
                break;
            case DIV:
                res = first / second;
                break;
            default:
                throw new VisitException("Unsupported token");
        }

        stack.push(res);
    }


    @Override
    protected void doVisit(BracketToken token) {
        throw new VisitException();
    }

    @Override
    public void visit(List<Token> inputToken) {
        super.visit(inputToken);
        if (stack.size() != 1) {
            throw new VisitException();
        }
        result = stack.pop();
    }

    public int getResult() {
        return result;
    }
}
