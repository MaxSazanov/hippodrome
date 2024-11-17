import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void whenConstructWithNullList_thenException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void whenConstructWithEmptyList_thenException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void whenGetHorses_thenGetCorrectList() {
        //given
        List<Horse> expected = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            expected.add(new Horse(String.valueOf(i), i));
        }
        Hippodrome hippodrome = new Hippodrome(expected);
        //when
        List<Horse> actual = hippodrome.getHorses();
        //then
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void whenMove_thenMoveAllHorses() {
        //given
        List<Horse> horses = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        hippodrome.move();
        //then
        horses.forEach((horse) -> Mockito.verify(horse, Mockito.only()).move());
    }

    @Test
    void whenGetWinner_thenHorseWithBiggestDistance() {
        //given
        List<Horse> horses = new ArrayList<>(3);
        horses.add(new Horse("1", 20, 100));
        horses.add(new Horse("2", 15, 70));
        Horse expected = new Horse("winner", 30, 140);
        horses.add(expected);
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        Horse actual = hippodrome.getWinner();
        //then
        assertEquals(expected, actual);
    }
}