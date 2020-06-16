package edu.miu.cs544.group4.engine.exception.handler;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs544.group4.engine.exception.BusinessException;
import edu.miu.cs544.group4.engine.exception.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author knguyen93
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler({IllegalArgumentException.class, BusinessException.class, ResourceNotFoundException.class})
  public ResponseEntity<ErrorInfo> handleIllegalException(RuntimeException ex) {
    return ResponseEntity
        .badRequest()
        .body(new ErrorInfo(ex.getMessage()));
  }
}
