package com.pt.pedrovcruzeiro.phonebook.service;

import com.pt.pedrovcruzeiro.phonebook.util.FileUtils;
import com.pt.pedrovcruzeiro.phonebook.util.constant.PhonebookConstants;
import com.pt.pedrovcruzeiro.phonebook.util.formatter.PhonebookLogFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrefixesService {

    private Set<String> filePrefixes = new HashSet<>();;

    private final FileUtils fileUtils;

    @Value("${phonebook.prefixes.filepath:prefixes.txt}")
    private String prefixesFilePathName;

    private String defaultPrefixesFilePathName = "prefixes.txt";

    public boolean hasPrefix(String phoneNumber) {
        return filePrefixes.contains(phoneNumber);
    }

    public void setPrefixes(Set<String> prefixes) {
        this.filePrefixes = prefixes;
    }

    public void loadClientPrefixesFile(){

        try {
            InputStream prefixesInputStream = fileUtils.retrieveInputStream(prefixesFilePathName);
            fileUtils.addFileLinesToStringSet(prefixesInputStream, filePrefixes);
            log.info(PhonebookLogFormatter.format("Prefixes file was loaded successfully."));
        } catch ( Exception e ) {
            log.error(PhonebookLogFormatter.format("Failed to load prefixes file :: )" + e.getMessage() + "."));
            log.debug(PhonebookLogFormatter.format("Loading default prefixes file."));
            try {
                InputStream prefixesInputStream = fileUtils.retrieveInputStream(defaultPrefixesFilePathName);
                fileUtils.addFileLinesToStringSet(prefixesInputStream, filePrefixes);
            } catch (IOException ioException) {
                log.error(PhonebookLogFormatter.format("Failed to load default prefixes file :: )" + e.getMessage() + "."));
            }
        }
    }

}
