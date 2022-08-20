/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

/**
 *
 * @author larsi
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class JuinChessTestUtils {

    public static void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    // public static <T> void assertEqualsIgnoringOrder(List<T> expected, List<T> actual) {
    //     final Set<T> actualSet = Set.copyOf(actual);
    //     final Set<T> expectedSet = Set.copyOf(expected);
    //     Supplier<String> makeAssertionMessage = () -> {
    //         Set<T> missing = difference(expectedSet, actualSet);
    //         Set<T> unexpected = difference(actualSet, expectedSet);

    //         return String.format("missing: %s -- unexpected: %s", missing, unexpected);
    //     };
    //     assertEquals(expectedSet, actualSet, makeAssertionMessage);
    // }

    private static <T> Set<T> difference(final Set<T> set1, final Set<T> set2) {
        Set<T> difference = new HashSet(set1);
        difference.removeAll(set2);
        return difference;
    }

    public static List<String> getNonPrivateMethodNames(final Class aClass) throws SecurityException {
        Method[] methods = aClass.getDeclaredMethods();
        return Arrays
                .stream(methods)
                .filter(method -> !Modifier.isPrivate(method.getModifiers()) && !Modifier.isProtected(method.getModifiers()))
                .filter(method -> isOwnMethod(method))
                .map(method -> method.getName())
                .toList();
    }

    private static boolean isOwnMethod(Method method) {
        try {
            // if superclass has the same method...
            method.getDeclaringClass().getSuperclass().getMethod(method.getName(), method.getParameterTypes());
            // then, not our own method.
            return false;
        } catch (NoSuchMethodException e) {
            return true;
        }
    }

    public static Object callMethodOnInstance(Object instance, String method, Object... params) {
        try {
            final Class[] classes = Arrays.stream(params).map(Object::getClass).toArray(Class[]::new); // a plain toArray() would return array of Object, which we can't cast to Class[]... thank you for nothing Java !!!
            final Method declaredMethod = instance.getClass().getDeclaredMethod(method, classes);
            return declaredMethod.invoke(instance, params);
        } catch (IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            throw new AssertionError(String.format("Could not invoke method on given arguments: %s.%s(%s)", instance, method, params.length > 0 ? Arrays.toString(params) : ""));
        }
    }
    private static final int SIZE = 8;

    /**
     * Convert chess notation to a Position object
     *
     * @param input chess notation such as a1 or h8 (1-based)
     * @return the corresponding position (0-based), or null if the position is
     * invalid
     */
    public static Position asPos(String input) {
        if (input.length() != 2) {
            return null;
        }
        String inputlc = input.toLowerCase();
        int col = inputlc.charAt(0) - 'a';
        int row = inputlc.charAt(1) - '1';

        if (0 <= row && row < SIZE && 0 <= col && col < SIZE) {
            return new Position(row, col);
        }

        return null;
    }

    public static String toChess(Position pos) {
        char col = (char) ('a' + pos.getColumn());
        char row = (char) ('1' + pos.getRow());
        return col + "" + row;

    }


    static void move(Model game, String from, String to) {
        Position fromPos = JuinChessTestUtils.asPos(from);
        Position toPos = JuinChessTestUtils.asPos(to);
        game.movePiecePosition(fromPos, toPos);
    }
    static void assertInvalidMove(Model game, String from, String to) {
        assertFalse(game.isValidMove(asPos(from), asPos(to)));
    }

    static void assertValidMove(Model game, String from, String to) {
        assertTrue(game.isValidMove(asPos(from), asPos(to)));
    }
}
