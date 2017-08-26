
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@State(
        name = "OobiSettings",
        storages = {@Storage("OobiSettings.xml")}
)
public class OobiSettings implements PersistentStateComponent<OobiSettings> {

    List<Binding> bindings;

    public OobiSettings() {
        this.bindings = new ArrayList<>();
    }

    @Nullable
    @Override
    public OobiSettings getState() {
        return this;
    }

    @Override
    public void loadState(OobiSettings settings) {
        XmlSerializerUtil.copyBean(settings, this);
    }

    public List<Binding> getBindings() {
        return bindings;
    }

    public void setBindings(List<Binding> bindings) {
        this.bindings = new ArrayList<>(bindings);
    }


/*
    @SuppressWarnings("RedundantIfStatement")
    public boolean equals(Object o) {
        // TODO implement
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OobiSettings that = (OobiSettings) o;

        if (allButtonsEnabled != that.allButtonsEnabled) return false;
        if (menusEnabled != that.menusEnabled) return false;
        if (proposeToCreateShortcutCount != that.proposeToCreateShortcutCount) return false;
        if (toolWindowButtonsEnabled != that.toolWindowButtonsEnabled) return false;
        if (toolbarButtonsEnabled != that.toolbarButtonsEnabled) return false;
        if (editorPopupEnabled != that.editorPopupEnabled) return false;
        if (maxNumberOfTips != that.maxNumberOfTips) return false;
        return true;
    }

    public int hashCode() {
        // TODO implement
        int result;
        result = (menusEnabled ? 1 : 0);
        result = 31 * result + (toolbarButtonsEnabled ? 1 : 0);
        result = 31 * result + (toolWindowButtonsEnabled ? 1 : 0);
        result = 31 * result + (editorPopupEnabled ? 1 : 0);
        result = 31 * result + (allButtonsEnabled ? 1 : 0);
        result = 31 * result + proposeToCreateShortcutCount;
        result = 31 * result + maxNumberOfTips;
        return result;
    }
*/

}
