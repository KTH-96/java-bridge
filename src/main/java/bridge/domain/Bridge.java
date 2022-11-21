package bridge.domain;

import static bridge.utils.Constant.POOR_CONSTRUCTION;
import static bridge.utils.Constant.POOR_CONSTRUCTION_MESSAGE;

import java.util.List;

public class Bridge {

    private final List<String> bridge;

    public Bridge(List<String> bridge) {
        checkPoorConstruction(bridge);
        this.bridge = bridge;
    }

    private void checkPoorConstruction(List<String> bridge) {
        for (String position : bridge) {
            validPosition(position);
        }
    }

    private void validPosition(String position) {
        if (position.equals(POOR_CONSTRUCTION)) {
            throw new IllegalArgumentException(POOR_CONSTRUCTION_MESSAGE);
        }
    }
}