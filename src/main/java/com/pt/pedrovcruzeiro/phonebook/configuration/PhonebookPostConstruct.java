package com.pt.pedrovcruzeiro.phonebook.configuration;

import com.pt.pedrovcruzeiro.phonebook.service.PrefixesService;
import com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants;
import com.pt.pedrovcruzeiro.phonebook.util.formatter.PhonebookLogFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PhonebookPostConstruct {

    private final PrefixesService prefixesService;

    @PostConstruct
    public void init() {
        MDC.put(PhonebookConstants.API_OPERATION,"prefixesLoader");
        log.info(PhonebookLogFormatter.format("Loading prefixesFile"));
        prefixesService.loadClientPrefixesFile();
        log.info(PhonebookLogFormatter.format("Finished loading prefixesFile"));
        MDC.remove(PhonebookConstants.API_OPERATION);
    }



}
