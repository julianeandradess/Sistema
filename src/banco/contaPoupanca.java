package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class contaPoupanca implements Conta{
    private Cliente cliente;
    private Integer numeroConta;
    private Boolean status;
    private Double saldo;
    private List<Double> extrato;

    public contaPoupanca(Cliente cliente, Integer numeroConta, Boolean status, Double saldo, List<Double> extrato) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.status = status;
        this.saldo = saldo;
        this.extrato = new ArrayList<>(extrato);
    }

    public contaPoupanca(Cliente cliente, Integer numeroConta, Boolean status, Double saldo) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.status = status;
        this.saldo = saldo;
        this.extrato = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "contaPoupanca{" +
                "cliente=" + cliente +
                ", numeroConta=" + numeroConta +
                ", status=" + status +
                ", saldo=" + saldo +
                ", extrato=" + extrato +
                '}';
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public Integer getNumeroConta() {
        return numeroConta;
    }

    @Override
    public Boolean isStatus() {
        return status;
    }

    @Override
    public Double getSaldo() {
        return saldo;
    }

    @Override
    public Optional<Double> depositar(Double valor) {
        Optional.ofNullable(valor).ifPresent(v -> {saldo += v;
            adicionarExtrato(v);
        });
        return Optional.empty();
    }

    @Override
    public Optional<Double> transferirDinheiro(Conta destino, Double valor) {
        try {
            Optional.ofNullable(destino).filter(conta -> status && conta.isStatus() && saldo >= valor)
                    .map(conta -> {
                        saldo -= valor;
                        destino.depositar(valor);
                        adicionarExtrato(-valor);
                        System.out.println("Transferência de R$ " + valor + " realizada com sucesso.");
                        return valor;
                    }).orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("Saldo insuficiente para transferência " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Double> sacar(Double valor) {
        return Optional.ofNullable(valor).filter(v -> status && saldo >= v).map(v -> {saldo -= v;
                    adicionarExtrato(-v);
                    System.out.println("Saque de R$ "+ v + " realizado com sucesso");
                    return v;
                }).map(Optional::of)
                .orElseGet(() -> {System.out.println("Saque não realizado"); return Optional.empty();
                });
    }

    @Override
    public Optional<List<Double>> getExtrato() {
        if (extrato.isEmpty()){
            System.out.println("Extrato Conta Poupança: Nenhuma movimentação");
            return Optional.empty();
        }
        System.out.println("Extrato da Conta Poupança: ");
        double saldo = 0;
        List<Double> extratos = new ArrayList<>();
        for (Double valor : extrato){
            extratos.add(valor);
            saldo += valor;
        }
        System.out.println("Movimentação do Extrato: R$ " + saldo);
        return Optional.of(extrato);
    }

    @Override
    public Optional<Double> adicionarExtrato(Double valorMovimentado) {
        return Optional.ofNullable(valorMovimentado).filter(v -> v > 0)
                .map(v -> { extrato.add(v); return v;});
    }
}
