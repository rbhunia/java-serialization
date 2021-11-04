package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ThirdPartyObject {

    private String name;

    private MoreThirdPartyObject moreThirdPartyObject;
}
