package servico;

import banco.Cliente;
import banco.contaCorrente;
import banco.contaPoupanca;
public class Bancario {


    static {
        contaCorrente contaA;
        contaPoupanca contaB;
    }


   public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Jordana", "012.345.678-910");
        Cliente cliente2 = new Cliente("Miguel", "012.345.678-910");

        contaCorrente contaA = new contaCorrente(cliente1, 1, true, 500.0);
        contaPoupanca contaB = new contaPoupanca(cliente2, 1, true, 500.0);

        System.out.println("--- Transferências ---");
        contaA.transferirDinheiro(contaB, 90.0);
        contaB.transferirDinheiro(contaA, 30.0);
        System.out.println("Saldo: "+ contaB.getSaldo());
        System.out.println("Saldo: "+ contaA.getSaldo());

        System.out.println("--- Saque ---");
        contaA.sacar(10.0);
        contaB.sacar(10.0);
        System.out.println("Saldo: " + contaA.getSaldo());
        System.out.println("Saldo: " + contaB.getSaldo());

       System.out.println("--- Depósito ---");
       contaA.depositar(10.0);
       contaB.depositar(10.0);
       System.out.println("Saldo: " + contaA.getSaldo());
       System.out.println("Saldo: " + contaB.getSaldo());

       System.out.println("--- Extratos ---");
       contaA.getExtrato().ifPresentOrElse(extrato -> {extrato.stream().reduce(0.0, Double::sum);
           System.out.println("Saldo final: " + contaA.getSaldo());
       }, () -> System.out.println("Extrato da conta Corrente: Nenhuma movimentação"));

       contaB.getExtrato().ifPresentOrElse(extratos -> {extratos.stream().reduce(0.0, Double::sum);
           System.out.println("Saldo final: " + contaB.getSaldo());
       }, () -> System.out.println("Extrato da conta Corrente: Nenhuma movimentação"));

    }
}
