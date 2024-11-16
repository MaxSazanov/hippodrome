import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void whenConstructHorseWithNullName_thenException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 25));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @ParameterizedTest
    void whenConstructHorseWithBlankName_thenException(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 25));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenConstructHorseWithNegativeSpeed_thenException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -10));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenConstructHorseWithNegativeDistance_thenException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 25, -10));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void whenGetName_thenGetCorrectName() {
        String expected = "name";
        Horse horse = new Horse(expected, 30);
        assertEquals(expected, horse.getName());
    }

    @Test
    void whenGetSpeed_thenGetCorrectSpeed() {
        double expected = 25;
        Horse horse = new Horse("name", expected);
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    void whenGetDistance_thenGetCorrectDistance() {
        double expected = 10;
        Horse horse = new Horse("name", 25, expected);
        assertEquals(expected, horse.getDistance());
    }

    @Test
    void whenGetDistanceAfterConstructWithTwoParameters_thenGetZeroDistance() {
        double expected = 0;
        Horse horse = new Horse("name", 25);
        assertEquals(expected, horse.getDistance());
    }

    @ValueSource(doubles = {0.4, 0.6, 0.8})
    @ParameterizedTest
    void move(double random) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            //given
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("name", 25);
            double expected = horse.getDistance() + horse.getSpeed() * random;
            //when
            horse.move();
            //then
            assertEquals(expected, horse.getDistance());
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.only());
        }
    }
}