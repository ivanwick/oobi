import com.intellij.ui.AddEditRemovePanel;
import org.jetbrains.annotations.Nullable;

public class BindingTableModel extends AddEditRemovePanel.TableModel<Binding> {
    @Override
    public int getColumnCount() {
        return 2;
    }

    @Nullable
    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Action Name";
            case 1:
                return "MIDI message";

            default:
                return null;
        }
    }

    @Override
    public Object getField(Binding b, int i) {
        switch (i) {
            case 0:
                return b.actionName;
            case 1:
                return b.midiMessage;

            default:
                return null;
        }
    }

    @Override
    public void setValue(Object aValue, Binding data, int columnIndex) {
        System.out.println("setValue: " + aValue + " " + data+ " "  + columnIndex);
        switch (columnIndex) {
            case 0:
                data.actionName = (String) aValue;
                break;
            case 1:
                System.err.println("nothing for midi message");
                break;
        }
    }

    @Override
    public boolean isEditable(int column) {
        return true;
    }
}
