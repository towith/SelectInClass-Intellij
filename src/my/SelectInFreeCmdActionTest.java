package my;

import org.junit.Test;

import static org.junit.Assert.*;

public class SelectInFreeCmdActionTest {

    @Test
    public void actionPerformed() {
        assert 2 == SelectInFreeCmdAction.countDot("a.b.c");
        assert 1 == SelectInFreeCmdAction.countDot("a.b");
        assert 1 == SelectInFreeCmdAction.countDot("/fljfl/abc.java");

    }
}