package com.apibinder.pixel.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pixel")
public class PixelController {


    @GetMapping(value = "{pixelId}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam(required = false) Map<String, String> requestParams,
                                         @PathVariable(required = true) String pixelId) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/static/pixel.png");
        return IOUtils.toByteArray(in);
    }


}
