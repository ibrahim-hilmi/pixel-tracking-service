package com.apibinder.pixel.services;

import com.apibinder.pixel.dto.PixelDto;
import com.apibinder.pixel.mapper.PixelMapper;
import com.apibinder.pixel.model.mongo.Pixel;
import com.apibinder.pixel.repository.PixelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
