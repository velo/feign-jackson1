/**
 * Copyright (C) 2016 Marvin Herman Froeder (marvin@marvinformatics.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package feign.jackson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

public class JacksonEncoder implements Encoder {

  private final ObjectMapper mapper;

  public JacksonEncoder() {
    this(Collections.<Module>emptyList());
  }

  public JacksonEncoder(Iterable<Module> modules) {
    this(new ObjectMapper()
            .setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL)
            .configure(SerializationConfig.Feature.INDENT_OUTPUT, true));
    for (Module module : modules) {
      this.mapper.registerModule(module);
    }

  }

  public JacksonEncoder(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void encode(Object object, Type bodyType, RequestTemplate template) {
    try {
      JavaType javaType = mapper.getTypeFactory().constructType(bodyType);
      template.body(mapper.writerWithType(javaType).writeValueAsString(object));
    } catch (IOException e) {
      throw new EncodeException(e.getMessage(), e);
    }
  }
}
