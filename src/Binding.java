
import javax.sound.midi.ShortMessage;

public class Binding {
    ShortMessage midiMessage;
    String actionName;

    public Binding(ShortMessage midiMessage, String actionName) {
        this.midiMessage = midiMessage;
        this.actionName = actionName;
    }
}
