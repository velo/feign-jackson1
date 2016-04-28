Jackson Codec
===================

[![Build Status](https://travis-ci.org/${owner}/${name}.svg?branch=master)](https://travis-ci.org/${owner}/${name}?branch=master) 
[![Coverage Status](https://coveralls.io/repos/github/${owner}/${name}/badge.svg?branch=master)](https://coveralls.io/github/${owner}/${name}?branch=master) 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.marvinformatics/${name}/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.marvinformatics/${name}/) 
[![Issues](https://img.shields.io/github/issues/${owner}/${name}.svg)](https://github.com/${owner}/${name}/issues) 
[![Forks](https://img.shields.io/github/forks/${owner}/${name}.svg)](https://github.com/${owner}/${name}/network) 
[![Stars](https://img.shields.io/github/stars/${owner}/${name}.svg)](https://github.com/${owner}/${name}/stargazers)

This module adds support for encoding and decoding JSON via Jackson.

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
