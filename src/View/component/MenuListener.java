package View.component;

import java.util.EventListener;

public interface MenuListener extends EventListener {
    void menuSelected(int index, int subIndex);
} 