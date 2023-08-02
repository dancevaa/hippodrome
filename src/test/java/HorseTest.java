import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void nullNameException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> new Horse(null, 1, 1) );
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> new Horse(name,1,1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -3, -223})
    public void negativeSpeedException(int speed){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> new Horse("name",speed,1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -3, -223})
    public void negativeDistanceException(int distance){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> new Horse("name",1,distance));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName(){
        Horse horse = new Horse("Name", 1, 1);
        assertEquals("Name", horse.getName());
    }

    @Test
    public void getSpeed(){
        Horse horse = new Horse("Name", 22, 1);
        assertEquals(22, horse.getSpeed());
    }

    @Test
    public void getDistance(){
        Horse horse1 = new Horse("Name", 1, 22);
        Horse horse2 = new Horse("Name", 1);
        assertEquals(22, horse1.getDistance());
        assertEquals(0, horse2.getDistance());
    }

    @Test
    public void moveUsesGetRandom(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("Name", 1,21).move();
            mockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
