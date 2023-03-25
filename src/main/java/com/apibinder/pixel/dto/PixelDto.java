package com.apibinder.pixel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PixelDto {

    private String id;
    private String pixelName;
    private String redirectionUrl;
    private Boolean sendUserAgent;

}
