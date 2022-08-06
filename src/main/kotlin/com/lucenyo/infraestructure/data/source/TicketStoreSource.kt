package com.lucenyo.infraestructure.data.source

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.util.IOUtils
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import java.io.SequenceInputStream

@Service
class TicketStoreSource(
  private val store: AmazonS3,
  @Value("\${amazon.s3.bucket-name}") val bucketName: String,
  ) {

  suspend fun upload(fileName: String, metadata: Map<String, String>, file: FilePart): String? {

    val objectMetadata = ObjectMetadata();
    objectMetadata.setHeader("Content-Type", file.headers().contentType.toString())
    metadata.map { objectMetadata.addUserMetadata(it.key, it.value) }

    return file.content()
      .map { it.asInputStream(true) }
      .reduce { s, d -> SequenceInputStream(s, d) }
      .map { store.putObject(bucketName, fileName, it, objectMetadata) }
      .map { it.contentMd5 }
      .awaitFirstOrNull()
  }

  fun download(fileName: String): ByteArray{
    return IOUtils.toByteArray(store.getObject(bucketName, fileName).objectContent)
  }

}
