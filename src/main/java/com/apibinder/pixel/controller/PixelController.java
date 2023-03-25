package com.apibinder.pixel.controller;

import com.apibinder.pixel.services.BwAgentInfoService;
import eu.bitwalker.useragentutils.UserAgent;
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
    public @ResponseBody byte[] getImage(@RequestHeader(value = "User-Agent") String userAgent,
                                         @RequestParam(required = false) Map<String, String> requestParams,
                                         @PathVariable(required = true) String pixelId) throws IOException {

        BwAgentInfoService bwAgentInfoService = new BwAgentInfoService(userAgent);
        System.out.println(bwAgentInfoService.getBrowser());
        System.out.println(bwAgentInfoService.getBrowserType());
        //System.out.println(bwAgentInfoService.getBrowserVersion());
        System.out.println(bwAgentInfoService.getOs());
        System.out.println(bwAgentInfoService.getOsGroup());
        System.out.println(bwAgentInfoService.getDeviceType());

        InputStream in = getClass()
                .getResourceAsStream("/static/pixel.png");
        return IOUtils.toByteArray(in);
    }
}
