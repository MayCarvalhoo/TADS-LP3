package br.edu.ifsp.orderflow.events.Handlers;

import br.edu.ifsp.orderflow.events.IEventHandler;
import br.edu.ifsp.orderflow.events.PedidoCriado;

public class PedidoCriadoLogHandler implements IEventHandler<PedidoCriado> {
    
    @Override
    public void handle(PedidoCriado event) {
        System.out.println("[Log] novo pedido "+ event.pedidoId() +" em" + event.ocorridoEm());
    }

    @Override
    public Class<PedidoCriado> eventType() {
        return null;
    }
}
