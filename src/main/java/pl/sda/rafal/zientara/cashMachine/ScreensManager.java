package pl.sda.rafal.zientara.cashMachine;

import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.dashboard.DashboardScreen;
import pl.sda.rafal.zientara.cashMachine.deposit.DepositScreen;
import pl.sda.rafal.zientara.cashMachine.mainMenu.MenuScreen;
import pl.sda.rafal.zientara.cashMachine.model.Cash;
import pl.sda.rafal.zientara.cashMachine.model.CashMachineStorage;
import pl.sda.rafal.zientara.cashMachine.model.RandomMachineStorage;
import pl.sda.rafal.zientara.cashMachine.pin.PinScreen;
import pl.sda.rafal.zientara.cashMachine.pin.changePin.ChangePinScreen;
import pl.sda.rafal.zientara.cashMachine.startScreen.StartScreen;
import pl.sda.rafal.zientara.cashMachine.thanks.ThanksScreen;
import pl.sda.rafal.zientara.cashMachine.wrong.WrongPinScreen;

import java.util.List;

public class ScreensManager implements
        PinScreen.ScreenListener,
        WrongPinScreen.ScreenListener,
        DashboardScreen.ScreenListener,
        ThanksScreen.ScreenListener,
        MenuScreen.ScreenListener,
        ChangePinScreen.ScreenListener,
        StartScreen.ScreenListener,
        DepositScreen.ScreenListener {

    private PinScreen pinScreen;
    private StartScreen startScreen;
    private WrongPinScreen wrongPinScreen;
    private DashboardScreen dashboardScreen;
    private DepositScreen depositScreen;
    private ThanksScreen thanksScreen;
    private MenuScreen menuScreen;
    private ChangePinScreen changePinScreen;

    private CashMachineStorage storage;

    private Card card;

    public void start() {
        startScreen = new StartScreen(this);
        startScreen.show();
        storage = new RandomMachineStorage();
    }

    @Override
    public void onCorrectPin(Card card) {
        this.card = pinScreen.getCard();
        pinScreen.hide();
        showMenu();
    }

    @Override
    public void showMenu() {
        menuScreen = new MenuScreen(this, card);
        menuScreen.show();
    }


    @Override
    public void onWrongPin() {
        pinScreen.hide();
        wrongPinScreen = new WrongPinScreen(this);
        wrongPinScreen.show();
    }

    @Override
    public void onWrongPinConfirm() {
        pinScreen.hide();
        wrongPinScreen.hide();
        System.exit(0);
    }

    @Override
    public void onCashWithdrawal(List<Cash> money) {
        dashboardScreen.hide();
        thanksScreen = new ThanksScreen(this, money);
        thanksScreen.show();
    }

    @Override
    public void onWithdrawalConfirm() {
        thanksScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onWithdraw() {
        menuScreen.hide();
        showDashboard();
    }

    private void showDashboard() {
        dashboardScreen = new DashboardScreen(this, card, storage);
        dashboardScreen.show();
    }

    @Override
    public void onExit() {
        menuScreen.hide();
        System.exit(0);
    }

    @Override
    public void onChangePin() {
        menuScreen.hide();
        changePinScreen = new ChangePinScreen(this, card);
        changePinScreen.show();
    }

    @Override
    public void onWrongChangePin() {
        onWrongPinConfirm();
    }

    @Override
    public void onCorrectChangePin() {
        changePinScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onBackButtonPress() {
        changePinScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onCorrectCardNum() {
        String cardNumber = startScreen.getCardNumber();
        startScreen.hide();
        pinScreen = new PinScreen(this, cardNumber);
        pinScreen.show();

    }

    @Override
    public void onCashDeposit() {
        depositScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onBackFromDeposit() {
        depositScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onDeposit() {
        menuScreen.hide();
        depositScreen = new DepositScreen(this, card, storage);
        depositScreen.show();
    }
}
