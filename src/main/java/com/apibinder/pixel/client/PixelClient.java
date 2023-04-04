package com.apibinder.pixel.client;

import com.apibinder.pixel.model.mongo.Pixel;
import com.apibinder.pixel.services.BwAgentInfoService;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class PixelClient {

    private static final String USERAGENT_KEY_BROWSER = "useragent-browser";
    private static final String USERAGENT_KEY_BROWSER_TYPE = "useragent-browser-type";
    private static final String USERAGENT_KEY_BROWSER_VERSION = "useragent-browser-version";
    private static final String USERAGENT_KEY_OS = "useragent-os";
    private static final String USERAGENT_KEY_OS_GROUP = "useragent-os-group";
    private static final String USERAGENT_KEY_DEVICE_TYPE = "useragent-device-type";
    private static final String USER_ID = "uid";
    private String userAgent;

    private Map<String, String> requestParams;
    private Pixel pixel;

    public static PixelClient getInstance(){
        return new PixelClient();
    }

    public PixelClient pixel(Pixel pixel){
        this.pixel = pixel;
        return this;
    }

    public PixelClient userAgent(String userAgent){
        this.userAgent = userAgent;
        return this;
    }

    public PixelClient requestParams(Map<String, String> requestParams){
        this.requestParams = requestParams;
        return this;
    }

    public void sendRequest(){
        WebClient webClient = WebClient.builder().baseUrl(pixel.getRedirectionUrl()).build();
        WebClient.RequestHeadersUriSpec<?> spec = webClient.get();

        setupUserAgent(spec);
        setupUserId(spec);
        setupRequestParams(spec);

        spec.exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class)).block();
    }

    private void setupRequestParams(WebClient.RequestHeadersUriSpec<?> spec) {
        spec.uri(builder -> {
            requestParams.keySet().forEach(headerKey -> builder.queryParam(headerKey, requestParams.get(headerKey)));
            return builder.build();
        });
    }

    private void setupUserId(WebClient.RequestHeadersUriSpec<?> spec) {
        spec.header(USER_ID, getUserId());
    }

    private String getUserId() {
        if (requestParams.get(USER_ID) != null){
            return requestParams.get(USER_ID);
        }

        // TODO: 29.03.2023 check cookie
        // TODO: 29.03.2023 check database for ip
        return null;
    }

    private void setupUserAgent(WebClient.RequestHeadersUriSpec<?> spec) {
        if (!pixel.getSendUserAgent()){
            return;
        }

        BwAgentInfoService bwAgentInfoService = new BwAgentInfoService(userAgent);

        spec.header(USERAGENT_KEY_BROWSER, bwAgentInfoService.getBrowser());
        spec.header(USERAGENT_KEY_BROWSER_TYPE, bwAgentInfoService.getBrowserType());
        spec.header(USERAGENT_KEY_BROWSER_VERSION, bwAgentInfoService.getBrowserVersion());
        spec.header(USERAGENT_KEY_OS, bwAgentInfoService.getOs());
        spec.header(USERAGENT_KEY_OS_GROUP, bwAgentInfoService.getOsGroup());
        spec.header(USERAGENT_KEY_DEVICE_TYPE, bwAgentInfoService.getDeviceType());

    }
}
