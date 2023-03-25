package com.apibinder.pixel.mapper;

import com.apibinder.pixel.dto.PixelDto;
import com.apibinder.pixel.model.mongo.Pixel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PixelMapper {

    PixelDto map(Pixel pixel);

    Pixel map(PixelDto pixelDto);
}
