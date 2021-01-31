public class ChipsetException extends RuntimeException {

  ChipsetException(String message, Throwable cause) {
    super(message, cause);
  }

  public ChipsetException(final String message) {
    super(message);
  }
}
