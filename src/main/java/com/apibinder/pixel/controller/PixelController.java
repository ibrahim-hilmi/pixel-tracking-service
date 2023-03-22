package com.apibinder.pixel.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pixel")
public class PixelController {

    @GetMapping(
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] getImage() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/static/pixel.png");
        return IOUtils.toByteArray(in);
    }


}
