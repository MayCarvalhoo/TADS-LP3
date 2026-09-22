package br.edu.ifsp.orderflow.events;

import java.time.Instant;

public interface IDomainEvent {
    Instant ocorridoEm();
}
