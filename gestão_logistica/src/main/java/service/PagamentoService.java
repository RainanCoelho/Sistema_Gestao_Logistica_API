package service;

import entity.Pagamento;
import org.springframework.stereotype.Service;
import repository.PagamentoRepository;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listar() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));
    }

    public Pagamento atualizar(
            Long id,
            Pagamento pagamentoAtualizado) {

        Pagamento pagamento = buscarPorId(id);

        pagamento.setTipoPagamento(
                pagamentoAtualizado.getTipoPagamento()
        );

        pagamento.setStatusPagamento(
                pagamentoAtualizado.getStatusPagamento()
        );

        pagamento.setValorPagamento(
                pagamentoAtualizado.getValorPagamento()
        );

        return pagamentoRepository.save(pagamento);
    }

    public void excluir(Long id) {

        Pagamento pagamento = buscarPorId(id);

        pagamentoRepository.delete(pagamento);
    }


}
