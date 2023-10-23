package com.core.rifei.shared.mercadoPago.services.verifyStatusPayment;

import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.order.repository.OrdersRepository;
import com.core.rifei.shared.mercadoPago.ENUM.STATUSPAYMENTMP;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerifyPayment {


  @Autowired
  OrdersRepository ticketsRepository;

  @Value("${app.access_token_mp}")
  private String acessToken;

  private static Logger logger = LoggerFactory.getLogger(VerifyPayment.class);

  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  public VerifyPayment() {
    // Inicialize o agendador no construtor
    scheduler = Executors.newScheduledThreadPool(1);

    long initialDelay = 1;
    long period = 3;
    TimeUnit unit = TimeUnit.HOURS;

    scheduler.scheduleAtFixedRate(this::isPixPaid, initialDelay, period, unit);
  }


  public void isPixPaid(){
    try{
      System.out.println("Iniciando a verificação do status do pagamento");
      logger.info("Iniciando a verificação do status do pagamento");
      MercadoPagoConfig.setAccessToken(acessToken);

      List<Orders> ordersList = ticketsRepository.findByStatusPaymentIgnoreCase(STATUSPAYMENTMP.PENDING.getKey());

      PaymentClient client = new PaymentClient();
      for(Orders orders: ordersList){

        Payment payment = client.get(orders.getIdTransaction());

        if(!payment.getStatus().equals(STATUSPAYMENTMP.PENDING.getKey())){
          if(payment.getStatus().equals(STATUSPAYMENTMP.APPROVED.getKey())){
            Instant instant = Instant.now();
            Timestamp timestamp = Timestamp.from(instant);

            orders.setPayment(true);
            orders.setCheckoutDate(timestamp);
          }
          orders.setStatusPayment(payment.getStatus());

          ticketsRepository.saveAndFlush(orders);
        }
      }
    }catch (Exception e){
      throw new Error("ErrorVerifyPayment");
    }

  }
}
