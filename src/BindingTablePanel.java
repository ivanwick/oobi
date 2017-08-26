import com.intellij.ui.InplaceAddEditRemovePanel;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.ShortMessage;
import java.util.List;

public class BindingTablePanel extends InplaceAddEditRemovePanel<Binding> {

    public BindingTablePanel(TableModel<Binding> model, List<Binding> data, @Nullable String label) {
        super(model, data, label);
    }

    @Nullable
    @Override
    protected Binding addItem() {
        return new Binding(new ShortMessage(), "A");
    }

    @Override
    protected boolean removeItem(Binding binding) {
        return true;
    }
}
