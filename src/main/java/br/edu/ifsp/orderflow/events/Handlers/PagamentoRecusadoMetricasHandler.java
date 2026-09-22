package br.edu.ifsp.orderflow.events.Handlers;

import br.edu.ifsp.orderflow.events.Handlers.Metricas;
import br.edu.ifsp.orderflow.events.IEventHandler;
import br.edu.ifsp.orderflow.events.PagamentoRecusado;

public class PagamentoRecusadoMetricasHandler implements IEventHandler<PagamentoRecusado> {

    private final Metricas metricas;

    PagamentoRecusadoMetricasHandler(Metricas metricas){
        this.metricas = metricas;
    }
    @Override
    public void handle(PagamentoRecusado event) {
        this.metricas.incrementarQtdReprovado();
    }

    @Override
    public Class<PagamentoRecusado> eventType() {
        return PagamentoRecusado.class;
    }

}
