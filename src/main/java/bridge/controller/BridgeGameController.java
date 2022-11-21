package bridge.controller;

import bridge.domain.BridgeSize;
import bridge.domain.MoveCommand;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGame bridgeGame;

    public BridgeGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.bridgeGame = new BridgeGame();
    }

    public void run() {
        startGame();
        insertBridgeSize();
        operate();
    }

    private void operate() {
        do {
            String nextMove = insertMoveCommand();
            bridgeGame.move(nextMove);
        } while (true);
    }

    private String insertMoveCommand() {
        try {
            String command = inputView.readMoving();
            return new MoveCommand(command).getCommand();
        } catch (IllegalArgumentException ie) {
            outputView.printErrorMessage(ie.getMessage());
            return insertMoveCommand();
        }
    }

    private void insertBridgeSize() {
        try {
            String size = inputView.readBridgeSize();
            bridgeGame.createBridge(new BridgeSize(size));
        } catch (IllegalArgumentException ie) {
            outputView.printErrorMessage(ie.getMessage());
            insertBridgeSize();
        }
    }

    private void startGame() {
        outputView.printStartMessage();
    }
}
