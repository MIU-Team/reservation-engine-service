package edu.miu.cs544.group4.engine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author knguyen93
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorInfo {
  private String message;
}
