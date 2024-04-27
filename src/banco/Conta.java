package banco;


import java.util.List;
import java.util.Optional;

public interface Conta {
    Cliente getCliente();
    Integer getNumeroConta();
    Boolean isStatus();
    Double getSaldo();
    Optional<Double> depositar(Double valor);

    Optional<Double> transferirDinheiro(Conta destino, Double valor);

    Optional<Double> sacar(Double valor);
    Optional<List<Double>> getExtrato();

    Optional<Double> adicionarExtrato(Double valorMovimentado);

}
