package org.example;

import java.time.OffsetDateTime;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
public class Ticket implements Comparable<Ticket> {
    private static final String HIGHEST_PRIORITY = "pension";
    private static int idSeq;

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    String type;

    /**
     * Приоритет для ранней регистрации
     */
    OffsetDateTime registerTime = OffsetDateTime.now();

    public Ticket(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }

    @Override
    public int compareTo(Ticket ticket) {
        if (this == ticket) {
            return 0;
        }
        if (ticket == null) {
            return 1;
        }

        if (type == null && ticket.type == null) {
            return registerTime.compareTo(ticket.registerTime) * (-1);
        } else if (type != null && ticket.type == null) {
            if (type.equals(HIGHEST_PRIORITY)) {
                return 1;
            } else {
                return registerTime.compareTo(ticket.registerTime) * (-1);
            }
        } else if (type == null) {
            if (ticket.type.equals(HIGHEST_PRIORITY)) {
                return -1;
            } else {
                return registerTime.compareTo(ticket.registerTime) * (-1);
            }
        } else {
            if (type.equals(ticket.type)) {
                return registerTime.compareTo(ticket.registerTime) * (-1);
            } else {
                if (type.equals(HIGHEST_PRIORITY)) {
                    return 1;
                }
                if (ticket.type.equals(HIGHEST_PRIORITY)) {
                    return -1;
                } else {
                    return registerTime.compareTo(ticket.registerTime) * (-1);
                }
            }
        }
    }
}
