package com.apibinder.pixel.services;

import com.apibinder.pixel.client.PixelClient;
import com.apibinder.pixel.dto.PixelDto;
import com.apibinder.pixel.mapper.PixelMapper;
import com.apibinder.pixel.model.mongo.Pixel;
import com.apibinder.pixel.repository.PixelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PixelService {

    private final PixelRepository pixelRepository;
    private final PixelMapper pixelMapper;

    public PixelDto persist(PixelDto pixelDto) {
        Pixel pixel = pixelMapper.map(pixelDto);
        pixel = persist(pixel);
        return pixelMapper.map(pixel);
    }

    public Pixel persist(Pixel pixel){
        return pixelRepository.save(pixel);
    }

    public void doRequest(String pixelId, Map<String, String> requestParams, String userAgent) {
        Pixel pixel = pixelRepository.findById(pixelId).orElseThrow(); // TODO: 25.03.2023 throw not found exception
        PixelClient.getInstance()
                .userAgent(userAgent)
                .pixel(pixel)
                .requestParams(requestParams)
                .sendRequest();
    }
}
