import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();
    Ticket ticket1 = new Ticket("Antalya","Kazan",80_000,12,19);
    Ticket ticket2 = new Ticket("Antalya","Kazan",65_000,12,19);
    Ticket ticket11 = new Ticket("Antalya","Kazan",80_000,24,5);
    Ticket ticket22 = new Ticket("Antalya","Kazan",65_000,13,25);
    Ticket ticket3 = new Ticket("Saint-Petersburg","Istanbul",77_000,8,13);
    Ticket ticket4 = new Ticket("Рязань","Саратов",6_000,5,9);
    Ticket ticket5 = new Ticket("Елабуга","Казань",6_000,7,8);
    Ticket ticket33 = new Ticket("Antalya","Саратов",65_000,12,19);
    @BeforeEach
    public void setup(){
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket11);
        aviaSouls.add(ticket22);
        aviaSouls.add(ticket33);
    }
    @Test
    public void testForGetter(){

        String[] expected = {"Antalya","Antalya","Saint-Petersburg","Рязань","Елабуга"};
        String[] actual = {ticket1.getFrom(),ticket2.getFrom(),ticket3.getFrom(),ticket4.getFrom(),ticket5.getFrom()};
        Assertions.assertArrayEquals(expected,actual);

        String[] expected2 = {"Kazan","Kazan","Istanbul","Саратов","Казань"};
        String[] actual2 = {ticket1.getTo(),ticket2.getTo(),ticket3.getTo(),ticket4.getTo(),ticket5.getTo()};
        Assertions.assertArrayEquals(expected2,actual2);

        int[] expected3 = {80_000, 65_000, 77_000, 6_000, 6_000};
        int[] actual3 = {ticket1.getPrice(),ticket2.getPrice(),ticket3.getPrice(),ticket4.getPrice(),ticket5.getPrice()};
        Assertions.assertArrayEquals(expected3,actual3);

        int[] expected4 = {12,12,8,5,7};
        int[] actual4 = {ticket1.getTimeFrom(),ticket2.getTimeFrom(),ticket3.getTimeFrom(),ticket4.getTimeFrom(),ticket5.getTimeFrom()};
        Assertions.assertArrayEquals(expected4,actual4);

        int[] expected5 = {19,19,13,9,8};
        int[] actual5 = {ticket1.getTimeTo(),ticket2.getTimeTo(), ticket3.getTimeTo(), ticket4.getTimeTo(), ticket5.getTimeTo()};
        Assertions.assertArrayEquals(expected5,actual5);
    }
    @Test
    public void compareIfLess() {
        int expected = -1;
        int actual = ticket2.compareTo(ticket1);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void compareIfMore() {
        int expected = 1;
        int actual = ticket3.compareTo(ticket4);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void compareEquals() {
        int expected = 0;
        int actual = ticket4.compareTo(ticket5);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void sortByPrice(){
        Ticket[] expected = {ticket2, ticket22, ticket1, ticket11};
        Ticket[] actual = aviaSouls.search("Antalya", "Kazan");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void findOne(){
        Ticket[] expected = {ticket3};
        Ticket[] actual = aviaSouls.search("Saint-Petersburg","Istanbul");
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void findZero(){
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Екатеринбург","Ницца");
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void sortByFlightTime(){
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket11, ticket1, ticket2, ticket22};
        //80_000,24,5
        //80_000,12,19
        //65_000,12,19
        //65_000,13,25
        Ticket[] actual = aviaSouls.searchAndSortBy("Antalya", "Kazan", comparator);
        Assertions.assertArrayEquals(expected,actual);
    }

}
