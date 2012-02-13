package hu.bearmaster.itm.common.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ItmException extends Exception {

   private static final long serialVersionUID = -1841866237621472120L;

   public enum ErrorCode {
      NO_ERROR,
      USER_PASSWORD_EMPTY,
      USER_PASSWORD_SHORT,
      USER_PASSWORD_NOT_COMPLEX,
      USER_REGISTRATION_FAILED,
      USER_AUTHENTICATION_FAILED,
      UNKNOWN
   }

   private ErrorCode errorCode;

   public ItmException(ErrorCode errorCode) {
      this(errorCode, "", null);
   }

   public ItmException(ErrorCode errorCode, String message) {
      this(errorCode, message, null);
   }

   public ItmException(ErrorCode errorCode, String message, Throwable cause) {
      super(message, cause);
      this.errorCode = errorCode;
   }

   public String toString() {
      return new ToStringBuilder(this)
            .append("errorCode", errorCode)
            .append("message", getMessage())
            .append("cause", getCause())
            .toString();
   }
}