package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panjw
 * @date 2021/3/18 15:41
 */
@RestController
public class CaseSourceController {

    @GetMapping(value = "/caseSource")
    public ResponseEntity<String> getCaseSource(){

        return ResponseEntity.status(HttpStatus.OK).body("CaseSource Get");
    }
}
