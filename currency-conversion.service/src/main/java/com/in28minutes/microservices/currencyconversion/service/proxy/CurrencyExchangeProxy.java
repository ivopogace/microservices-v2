package com.in28minutes.microservices.currencyconversion.service.proxy;

import com.in28minutes.microservices.currencyconversion.service.beans.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
/**
 * Feign can talk to Eureka and get the instance of currency-exchange
 * and do load balancing between them
 */
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
