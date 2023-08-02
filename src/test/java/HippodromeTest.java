import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nullHorsesException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null) );
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void emptyHorsesException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++){
            horses.add(new Horse("horse" + i, 1,1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i<50; i++){
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse: horses){
            verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++){
            horses.add(new Horse("horse" + i, i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(hippodrome.getHorses().get(horses.size()-1), hippodrome.getWinner());
    }


}
