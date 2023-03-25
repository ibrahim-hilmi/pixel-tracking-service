package com.apibinder.pixel.repository;

import com.apibinder.pixel.model.mongo.Pixel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixelRepository extends MongoRepository<Pixel, String> {
}
