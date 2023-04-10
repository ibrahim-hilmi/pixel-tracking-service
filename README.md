# Tracking Pixel Service

A Spring Boot microservice that provides tracking pixel functionality for collecting browser and user activity data through an embedded 1x1 pixel image.

## Overview

The service serves tracking pixels that can be embedded in emails or web pages. When loaded, it captures browser information (User-Agent, request parameters) and forwards tracking data to configured endpoints while returning a transparent PNG image.

## Features

- **Pixel Image Delivery**: Returns a transparent 1x1 PNG image for tracking
- **Browser Data Capture**: Automatically extracts User-Agent and request parameters
- **External Forwarding**: Sends tracking data to configured endpoints
- **MongoDB Storage**: Persists pixel configurations (name, redirect URL, settings)

## Tech Stack

- Spring Boot 3.0.4
- Java 17
- MongoDB
- MapStruct (DTO mapping)
- Lombok

## API Endpoints

- `GET /pixel/{pixelId}` - Returns tracking pixel image and processes tracking data
- `POST /pixel` - Creates a new pixel configuration

## Configuration

Default port: `8083`  
MongoDB: `localhost:27017` (database: `restgateway`)

Configure in `application.yml`.
