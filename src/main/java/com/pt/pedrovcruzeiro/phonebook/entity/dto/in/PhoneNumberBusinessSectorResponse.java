package com.pt.pedrovcruzeiro.phonebook.entity.dto.in;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberBusinessSectorResponse {

    private String number;
    private String sector;
}
