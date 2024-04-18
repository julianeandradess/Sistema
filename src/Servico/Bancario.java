package Servico;

import Banco.Cliente;
import Banco.Conta;

public class Bancario {

      static Conta contaA;
      static Conta contaB;
   static {
       Cliente cliente1 = new Cliente("Jordana", "012.345.678-910");
       Cliente cliente2 = new Cliente("Miguel", "109.876.543-21");

       Conta contaA = new Conta(cliente1, 1, true, 1000);
       Conta contaB = new Conta(cliente2, 2, true, 500);
   }
   public static void main(String[] args) {

        contaA.transferirDinheiro(contaB, 90);
        contaB.transferirDinheiro(contaA, 30);
        System.out.println("saldo "+ contaB.getSaldo());
        System.out.println("saldo "+ contaA.getSaldo());

    }
}
