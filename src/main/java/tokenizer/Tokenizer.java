package tokenizer;

import tokenizer.state.MainState;
import tokenizer.state.State;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private State state = new MainState(this);
    private final List<Token> tokens = new ArrayList<>();

    public List<Token> tokenize(String str) {
        for (int i = 0; i < str.length(); i++) {
            state.process(str.charAt(i));
        }
        state.end();
        return tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
