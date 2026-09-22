package br.edu.ifsp.orderflow.events;

/**
 * Algo que irá consumir/reagir a tipo especifico de evento
 *
 * o parametro de tipo e garante, em tempo de compilação, que
 * um handler de PagamentoAprovado nunca receba um pagamentoRecusado
 */
public interface IEventHandler<E extends IDomainEvent> {
    void handle(E event);

    /**
     * Qual tipo de evento este handler trata/consome?
     *
     * Necessario por causa do apagamento de tipo do java (Type erasure)
     * @return
     */
    Class<E> eventType();
}
