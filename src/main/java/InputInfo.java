public class InputInfo {
  private final int[] frequencyPerMachine;
  private final int chipsetsNeededPerMinute;

  public InputInfo(final int[] frequencyPerMachine, final int chipsetsNeededPerMinute) {
    this.frequencyPerMachine = frequencyPerMachine;
    this.chipsetsNeededPerMinute = chipsetsNeededPerMinute;
  }

  public int[] getFrequencyPerMachine() {
    return frequencyPerMachine;
  }

  public int getChipsetsNeededPerMinute() {
    return chipsetsNeededPerMinute;
  }
}
