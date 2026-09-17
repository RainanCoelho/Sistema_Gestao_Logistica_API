package service;

import DTO.Pagamento.PagamentoRequestDTO;
import DTO.Pagamento.PagamentoResponseDTO;
import entity.Pagamento;
import org.springframework.stereotype.Service;
import repository.PagamentoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoResponseDTO salvar(PagamentoRequestDTO request) {

        Pagamento pagamento = new Pagamento();

        pagamento.setTipoPagamento(request.getTipoPagamento());
        pagamento.setStatusPagamento(request.getStatusPagamento());
        pagamento.setValorPagamento(request.getValorPagamento());

        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        return converterParaResponse(pagamentoSalvo);
    }

    public List<PagamentoResponseDTO> listar() {

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        List<PagamentoResponseDTO> responses = new ArrayList<>();

        for (Pagamento pagamento : pagamentos) {
            PagamentoResponseDTO response = converterParaResponse(pagamento);
            responses.add(response);
        }

        return responses;
    }

    public PagamentoResponseDTO buscarPorId(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));

        return converterParaResponse(pagamento);
    }

    public PagamentoResponseDTO atualizar(
            Long id,
            PagamentoRequestDTO request) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));

        pagamento.setTipoPagamento(request.getTipoPagamento());
        pagamento.setStatusPagamento(request.getStatusPagamento());
        pagamento.setValorPagamento(request.getValorPagamento());

        Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);

        return converterParaResponse(pagamentoAtualizado);
    }

    public void excluir(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));

        pagamentoRepository.delete(pagamento);
    }

    private PagamentoResponseDTO converterParaResponse(Pagamento pagamento) {

        PagamentoResponseDTO response = new PagamentoResponseDTO();

        response.setIdPagamento(pagamento.getIdPagamento());
        response.setTipoPagamento(pagamento.getTipoPagamento());
        response.setStatusPagamento(pagamento.getStatusPagamento());
        response.setValorPagamento(pagamento.getValorPagamento());

        return response;
    }
}
