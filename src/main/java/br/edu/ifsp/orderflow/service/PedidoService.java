package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.ResultadoPagamento;

public class PedidoService {

    private final IEstoqueService estoqueService;
    private final IPedidoRepository pedidoRepository;
    private final IPagamentoGateway pagamentoGateway;
    private  final INotificacaoService notificacaoService;

    public PedidoService(
            IEstoqueService estoqueService,
            IPedidoRepository pedidoRepository,
            IPagamentoGateway pagamentoGateway,
            INotificacaoService notificacaoService
    )  {
        this.estoqueService = estoqueService;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoGateway = pagamentoGateway;
        this.notificacaoService = notificacaoService;
    }

    public Pedido processar(Pedido pedido) {

        boolean foiReservado = this.estoqueService.reservar(pedido);

        if (!foiReservado) {
            pedido.marcarCancelado();
            // salvar o pedido
            return pedido;
        }

        ResultadoPagamento resultado = this.pagamentoGateway.pagar(pedido);

        if (!resultado.isAprovado()) {
            this.estoqueService.liberar(pedido);
            pedido.marcarCancelado();
            this.pedidoRepository.save(pedido);
            return pedido;

        }

        pedido.marcarComoPago();
        this.pedidoRepository.save(pedido);

        this.notificacaoService.notificar(
                pedido.getCliente(),
                "Pagamento aprovado! Pedido!" + pedido.getIdCurto() + "confirmado"

        );

        // notificar o cliente

        //retorna o pedido

        return pedido;
    }
}
