package com.lucenyo.application.rest

import com.lucenyo.application.rest.responses.ErrorResponse
import com.lucenyo.domain.exceptions.NotFoundException
import com.lucenyo.domain.exceptions.UploadErrorException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandlerController {

  @ExceptionHandler(value = [(NotFoundException::class)])
  fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorResponse> {
    return ResponseEntity(
      ErrorResponse(
        code = exception.code,
        message = exception.message,
        field = exception.field
      ),
      HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(value = [(UploadErrorException::class)])
  fun handleNotFoundException(exception: UploadErrorException): ResponseEntity<ErrorResponse> {
    return ResponseEntity(
      ErrorResponse(
        code = exception.code,
        message = exception.message
      ),
      HttpStatus.NOT_FOUND)
  }

}