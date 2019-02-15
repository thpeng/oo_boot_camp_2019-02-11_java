package unit;

import node.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {

    private static final Node A = new Node("A");
    private static final Node B = new Node("B");
    private static final Node C = new Node("C");
    private static final Node D = new Node("D");
    private static final Node E = new Node("E");
    private static final Node F = new Node("F");
    private static final Node G = new Node("G");

    @BeforeAll
    public static void init() {
        B.addTarget(A, 5);
        B.addTarget(C, 6);
        B.addTarget(F, 4);
        C.addTarget(D, 1);
        C.addTarget(D, 7);
        C.addTarget(E, 8);
        D.addTarget(E, 2);
        E.addTarget(B, 3);
    }

    @Test
    void cost() {
        assertEquals(9, B.cost(E));
        assertEquals(1, C.cost(D));
        assertEquals(7, E.cost(F));
        assertEquals(0, G.cost(G));
        assertThrows(IllegalArgumentException.class, ()-> A.cost(F));
    }

    @Test
    void hopCount() {
       // assertEquals(0, C.hopCount(C));
        assertEquals(2, C.hopCount(B));
        assertThrows(IllegalArgumentException.class, ()-> C.hopCount(G));
        assertEquals(1, B.hopCount(F));
    }

    @Test
    void testReach() {
        assertTrue(A.canReach(A));
        assertFalse(A.canReach(B));
        assertFalse(A.canReach(C));
        assertFalse(A.canReach(D));
        assertFalse(A.canReach(E));
        assertFalse(A.canReach(F));
        assertFalse(A.canReach(G));

        assertTrue(B.canReach(A));
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(C));
        assertTrue(B.canReach(D));
        assertTrue(B.canReach(E));
        assertTrue(B.canReach(F));
        assertFalse(B.canReach(G));

        assertTrue(C.canReach(A));
        assertTrue(C.canReach(B));
        assertTrue(C.canReach(C));
        assertTrue(C.canReach(D));
        assertTrue(C.canReach(E));
        assertTrue(C.canReach(F));
        assertFalse(C.canReach(G));

        assertTrue(D.canReach(A));
        assertTrue(D.canReach(B));
        assertTrue(D.canReach(C));
        assertTrue(D.canReach(D));
        assertTrue(D.canReach(E));
        assertTrue(D.canReach(F));
        assertFalse(D.canReach(G));

        assertTrue(E.canReach(A));
        assertTrue(E.canReach(B));
        assertTrue(E.canReach(C));
        assertTrue(E.canReach(D));
        assertTrue(E.canReach(E));
        assertTrue(E.canReach(F));
        assertFalse(E.canReach(G));

        assertFalse(F.canReach(A));
        assertFalse(F.canReach(B));
        assertFalse(F.canReach(C));
        assertFalse(F.canReach(D));
        assertFalse(F.canReach(E));
        assertTrue(F.canReach(F));
        assertFalse(F.canReach(G));

        assertFalse(G.canReach(A));
        assertFalse(G.canReach(B));
        assertFalse(G.canReach(C));
        assertFalse(G.canReach(D));
        assertFalse(G.canReach(E));
        assertFalse(G.canReach(F));
        assertTrue(G.canReach(G));
    }

    @Test
    void path(){
        assertEquals(6.0d, C.path(B).cumulatedCosts());
        assertEquals(7.0d, B.path(D).cumulatedCosts());
        assertEquals(11.0d, D.path(C).cumulatedCosts());
        assertEquals(4.0d, B.path(F).cumulatedCosts());
    }
}
