package com.core.rifei.shared.mercadoPago.services.webhook;

import com.core.rifei.shared.mercadoPago.services.webhook.dto.WebHookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/webhook")
public class WebhookController {

  //@Autowired
  WebhookService webhookService;

  @PostMapping
  public void onWebhook(@RequestBody Object data) {

    //payment.updated
    System.out.println("Chegou");

  }
}
