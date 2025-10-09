package org.example.designpattern;

interface PaymentStratergy
{
    void pay();
}

class CredicardPymanet implements PaymentStratergy
{
    @Override
    public void pay(){
        System.out.println("Payed using creditcard");
    }
}

class UPIPayment implements PaymentStratergy
{
    @Override
    public void pay(){
        System.out.println("Payed using UPI");
    }
}

class Payment
{
    PaymentStratergy paymentStratergy;
    Payment(PaymentStratergy paymentStratergy)
    {
        this.paymentStratergy = paymentStratergy;
    }
    public void processPayment(){
        paymentStratergy.pay();
    }
}

public class StratergyDemo {
    public static void main(String[] args) {
        Payment payment = new Payment(new CredicardPymanet());
        payment.processPayment();
    }
}
