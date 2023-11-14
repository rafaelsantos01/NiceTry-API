package com.core.nicetry.shared.mercadoPago.services.webhook;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
