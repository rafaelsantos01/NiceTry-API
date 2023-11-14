package com.core.nicetry.modules.winners.services.numberslook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin/numberlook/{id_order}")
public class NumberLookController {

  @Autowired
  NumbersLookService numbersLookService;

  @PostMapping
  public ResponseEntity<Void> handle(@PathVariable("id_order")UUID id_order){

    numbersLookService.handle(id_order);

    return new ResponseEntity<>(HttpStatus.OK);
  }

}
