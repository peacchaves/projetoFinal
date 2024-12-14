package acc.br.demo.service;

import acc.br.demo.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepService {
    @GetMapping("/{cep}/json")
    Address findAddressByCep(@PathVariable("cep") String cep);
}