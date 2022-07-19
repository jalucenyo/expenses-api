package com.lucenyo.infraestructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.time.OffsetDateTime
import java.time.ZoneOffset


@Configuration
class MongoDbConfiguration {

  @Bean
  fun dateConversions(): MongoCustomConversions {
    return MongoCustomConversions(listOf(
      OffsetDateTimeWriteConverter(),
      OffsetDateTimeReadConverter()
    ))
  }

  internal class OffsetDateTimeWriteConverter : Converter<OffsetDateTime, String> {
    override fun convert(source: OffsetDateTime): String {
      return source.toInstant().atZone(ZoneOffset.UTC).toString()
    }
  }

  internal class OffsetDateTimeReadConverter : Converter<String, OffsetDateTime> {
    override fun convert(source: String): OffsetDateTime {
      return OffsetDateTime.parse(source)
    }
  }

}