package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;

public class PedidoService {

    private IEstoqueService estoqueService;

    public PedidoService(IEstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    public Pedido processar(Pedido pedido) {

        boolean foiReservado = this.estoqueService.reservar(pedido);

        if (!foiReservado) {
            pedido.marcarCancelado();
            // salvar o pedido
            return pedido;
        }


        // processar o pagamento

        // salvar se o pagamente ocorreu com sucesso

        // notificar o cliente

        //retorna o pedido

        return pedido;
    }
}
