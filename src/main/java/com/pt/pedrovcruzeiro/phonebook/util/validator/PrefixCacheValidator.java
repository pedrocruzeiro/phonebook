package com.pt.pedrovcruzeiro.phonebook.util.validator;

import com.pt.pedrovcruzeiro.phonebook.service.PrefixesService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrefixCacheValidator {

    private final PrefixesService prefixesService;

    @Cacheable(cacheNames={"prefixes"}, key = "#splitPhoneNumber")
    public boolean isValidPrefix(String splitPhoneNumber){
       return prefixesService.hasPrefix(splitPhoneNumber);
    }
}
