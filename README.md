Jackson1 Codec
===================

[![Build Status](https://travis-ci.org/velo/feign-jackson1.svg?branch=master)](https://travis-ci.org/velo/feign-jackson1?branch=master) 
[![Coverage Status](https://coveralls.io/repos/github/velo/feign-jackson1/badge.svg?branch=master)](https://coveralls.io/github/velo/feign-jackson1?branch=master) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.marvinformatics.feign/feign-jackson1/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.marvinformatics.feign/feign-jackson1/) 
[![Issues](https://img.shields.io/github/issues/velo/feign-jackson1.svg)](https://github.com/velo/feign-jackson1/issues) 
[![Forks](https://img.shields.io/github/forks/velo/feign-jackson1.svg)](https://github.com/velo/feign-jackson1/network) 
[![Stars](https://img.shields.io/github/stars/velo/feign-jackson1.svg)](https://github.com/velo/feign-jackson1/stargazers)

This module adds support for encoding and decoding JSON via Jackson1.

This is pretty much a copy of https://github.com/Netflix/feign/tree/master/jackson, but different Jackson version

Add `JacksonEncoder` and/or `JacksonDecoder` to your `Feign.Builder` like so:

```java
GitHub github = Feign.builder()
                     .encoder(new JacksonEncoder())
                     .decoder(new JacksonDecoder())
                     .target(GitHub.class, "https://api.github.com");
```

If you want to customize the `ObjectMapper` that is used, provide it to the `JacksonEncoder` and `JacksonDecoder`:

```java
ObjectMapper mapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(SerializationFeature.INDENT_OUTPUT, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

GitHub github = Feign.builder()
                     .encoder(new JacksonEncoder(mapper))
                     .decoder(new JacksonDecoder(mapper))
                     .target(GitHub.class, "https://api.github.com");
```
