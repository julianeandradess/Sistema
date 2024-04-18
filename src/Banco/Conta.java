package Banco;



public class Conta {
    private Cliente cliente;
    private int numeroConta;
    private boolean status;
    private double saldo;

    public Conta(Cliente cliente, int numeroConta, boolean status, double saldo) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.status = status;
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Banco.Conta{" +
                "cliente=" + cliente +
                ", numeroConta='" + numeroConta + '\'' +
                ", status=" + status +
                ", saldo=" + saldo +
                '}';
    }
    public void depositar(double valor){
        this.saldo += valor;
    }
    public void transferirDinheiro(Conta destino, double valor) {
        if (this.status && destino.isStatus() && this.saldo >= valor){
            this.saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso.");
        }else {
            System.out.println("Transferência não realizada");
        }

    }
}
