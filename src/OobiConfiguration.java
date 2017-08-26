import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.AddEditRemovePanel;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OobiConfiguration extends BaseConfigurable implements SearchableConfigurable, PersistentStateComponent<OobiConfiguration> {

    private JPanel configPanel;
    private AddEditRemovePanel<Binding> addEditRemovePanel;

    private List<Binding> bindings;

    private BindingTableModel bindingTableModel;

    private OobiSettings oobiSettings = ServiceManager.getService(OobiSettings.class);


    private void createUIComponents() {
        bindingTableModel = new BindingTableModel();
        bindings = new ArrayList<>();
        addEditRemovePanel = new BindingTablePanel(bindingTableModel, bindings, "Bindings");
    }

    @NotNull
    @Override
    public String getId() {
        return "OobiConfiguration";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "OOBI";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return configPanel;
    }

    @Override
    public void apply() throws ConfigurationException {
        System.err.println("apply()");
        oobiSettings.setBindings(bindings);
    }

    @SuppressWarnings("RedundantIfStatement")
    public boolean isModified() {
        if (!bindings.equals(oobiSettings.getBindings())) {
            return true;
        }

        return false;
    }

    public void reset() {
        System.err.println("reset()");
        bindings = new ArrayList<>(oobiSettings.getBindings());
        addEditRemovePanel.setData(bindings);
    }

    @Nullable
    @Override
    public OobiConfiguration getState() {
        return this;
    }

    @Override
    public void loadState(OobiConfiguration oobiConfiguration) {
        XmlSerializerUtil.copyBean(oobiConfiguration, this);
    }

}
