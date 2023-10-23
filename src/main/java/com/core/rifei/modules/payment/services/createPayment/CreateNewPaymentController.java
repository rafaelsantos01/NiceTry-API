package com.core.rifei.modules.payment.services.createPayment;

import com.core.rifei.modules.payment.services.createPayment.dto.CreatePaymentRequestDTO;
import com.core.rifei.modules.payment.services.createPayment.dto.CreatePaymentResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Pagamento")
@RestController
@RequestMapping("payment")
public class CreateNewPaymentController {

  @Autowired
  CreateNewPaymentService createNewPaymentService;

  @ApiOperation("Endpoint responsável gerar uma nova compra.")
  @Tag(name = "Pagamento")
  @PostMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<CreatePaymentResponseDTO> hanlde(@RequestBody @Valid CreatePaymentRequestDTO data){


    CreatePaymentResponseDTO rsponse = createNewPaymentService.execute(data);


    return new ResponseEntity<>(rsponse, HttpStatus.OK);
  }
}
