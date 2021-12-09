package com.pt.pedrovcruzeiro.phonebook.service;

import java.util.Set;

public interface PrefixesService {

  public boolean hasPrefix(String phoneNumber);

  public void setPrefixes(Set<String> prefixes);

  public void loadClientPrefixesFile();
}
