package com.pt.pedrovcruzeiro.phonebook.feign;

import com.pt.pedrovcruzeiro.phonebook.entity.dto.in.PhoneNumberBusinessSectorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "businessSectorAPI", url = "${server.business.sector.api.hostname}")
public interface BusinessSectorFeignClient {

  @GetMapping("${server.business.sector.api.sector.endpoint}")
  PhoneNumberBusinessSectorResponse retrieveBusinessSector(
      @RequestParam("phoneNumber") String phoneNumber);
}
