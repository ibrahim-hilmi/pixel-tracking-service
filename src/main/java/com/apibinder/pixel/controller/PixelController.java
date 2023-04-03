package com.apibinder.pixel.controller;

import com.apibinder.pixel.dto.PixelDto;
import com.apibinder.pixel.services.BwAgentInfoService;
import com.apibinder.pixel.services.PixelService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pixel")
public class PixelController {

    private final PixelService pixelService;

    @GetMapping(value = "{pixelId}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@RequestHeader(value = "User-Agent") String userAgent,
                                         @RequestParam(required = false) Map<String, String> requestParams,
                                         @PathVariable(required = true) String pixelId) throws IOException {

        pixelService.doRequest(pixelId, requestParams, userAgent);

        InputStream in = getClass()
                .getResourceAsStream("/static/pixel.png");
        return IOUtils.toByteArray(in);
    }

    @PostMapping
    public ResponseEntity<PixelDto> createPixel(@RequestBody PixelDto pixelDto){
        return new ResponseEntity<>(pixelService.persist(pixelDto), HttpStatus.CREATED);
    }
}
