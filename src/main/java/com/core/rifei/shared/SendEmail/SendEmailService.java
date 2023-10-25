package com.core.rifei.shared.SendEmail;

import com.core.rifei.modules.templatesEmail.entities.Templates;
import com.core.rifei.modules.templatesEmail.repository.TemplatesRepository;
import com.core.rifei.shared.SendEmail.dto.RecoveryPasswordEmailDTO;
import com.core.rifei.shared.SendEmail.dto.SendEmailServiceDTO;
import com.core.rifei.shared.mercadoPago.services.verifyStatusPayment.VerifyPayment;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class SendEmailService {


    @Autowired
    private  JavaMailSender javaMailSender;

    @Autowired
    private TemplatesRepository templatesRepository;

  private static Logger logger = LoggerFactory.getLogger(VerifyPayment.class);


    @Value("${app.number_whatsapp}")
    private String whatsApp;


    public void SendEmail(SendEmailServiceDTO data){
      try{
        Templates templates = templatesRepository.findByTemplateTypeAndStatus(data.getTemplateType(), true);

        if(templates == null){
          throw new Error("templateNotFound");
        }

        String template = templates.getTemplate()
          .replaceAll("\\{name}", data.getUserName())
          .replaceAll("\\{url}", data.getLink())
          .replaceAll("\\{whatsApp}", whatsApp)
          .replaceAll("\\{campaignName}", data.getCampaignName())
          .replaceAll("\\{numbersLook}", data.getNumbersLook());

        String titleReplace = templates.getTitle().replace("{name}",data.getUserName());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(data.getUserMail());
        helper.setSubject(titleReplace);
        helper.setText(template, true);

        javaMailSender.send(message);
      }catch (Exception e){
        logger.error("Erro ao enviar e-mail",e);
        //throw new Error("ErrorSendEmail");
      }
    }

}
