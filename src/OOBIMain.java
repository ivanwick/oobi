import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OOBIMain implements ApplicationComponent {

    private final Map<String, Integer> withoutShortcutStats = Collections.synchronizedMap(new HashMap<String, Integer>());
    // Presentation and stats fields.

    private final OobiSettings oobiSettings = ServiceManager.getService(OobiSettings.class);


    public void initComponent() {

        // Initialize the MIDI listener
        MidiListener midiListener = new MidiListener();

        System.out.println("initComponent called");
    }

    public void disposeComponent() {
        System.out.println("disposeComponent called");
    }

    @NotNull
    public String getComponentName() {
        return "OOBI component name";
    }

}
