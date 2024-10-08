package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabricTest_threeTickets() {
        TicketManager manager = homeWork.managerFabric();
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("lol"));
        tickets.add(new Ticket("pension"));
        tickets.add(new Ticket("pension"));

        for (Ticket ticket : tickets){
            manager.add(ticket);
        }

        assertEquals(tickets.get(1), manager.next());
        assertEquals(tickets.get(2), manager.next());
        assertEquals(tickets.get(0), manager.next());
    }

    @Test
    void managerFabricTest_hugeTickets() {
        TicketManager manager = homeWork.managerFabric();
        List<Ticket> tickets = new ArrayList<>();

        for(int i = 0; i < 10; ++i){
            tickets.add(new Ticket("lol"));
        }

        tickets.add(new Ticket("1212"));
        tickets.add(new Ticket("12123"));

        for (Ticket ticket : tickets){
            manager.add(ticket);
        }

        for (Ticket ticket : tickets) {
            assertEquals(ticket, manager.next());
        }
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }


}