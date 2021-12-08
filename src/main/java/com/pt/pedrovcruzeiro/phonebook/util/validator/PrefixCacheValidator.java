package com.pt.pedrovcruzeiro.phonebook.util.validator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class PrefixCacheValidator {

    @Cacheable(cacheNames={"prefixes"}, key = "#splitPhoneNumber")
    public boolean isValidPrefix(String splitPhoneNumber){
        return true;
    }
}
