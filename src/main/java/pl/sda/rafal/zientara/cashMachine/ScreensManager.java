package pl.sda.rafal.zientara.cashMachine;

import pl.sda.rafal.zientara.cashMachine.dashboard.DashboardScreen;
import pl.sda.rafal.zientara.cashMachine.mainMenu.MenuScreen;
import pl.sda.rafal.zientara.cashMachine.model.Cash;
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
        StartScreen.ScreenListener {

    private PinScreen pinScreen;
    private StartScreen startScreen;
    private WrongPinScreen wrongPinScreen;
    private DashboardScreen dashboardScreen;
    private ThanksScreen thanksScreen;
    private MenuScreen menuScreen;
    private ChangePinScreen changePinScreen;

    private String cardNumber;

    public void start() {
        startScreen = new StartScreen(this);
        startScreen.show();
    }

    @Override
    public void onCorrectPin() {
        pinScreen.hide();
        showMenu();
    }



 private void showMenu() {
        menuScreen = new MenuScreen(this);
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
        showMenu();
    }

    @Override
    public void onBalance() {

    }

    @Override
    public void onWithdraw() {
        menuScreen.hide();
        showDashboard();
    }
    private void showDashboard() {
        dashboardScreen = new DashboardScreen(this);
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
        changePinScreen = new ChangePinScreen(this);
    }

    @Override
    public void onInfo() {

    }

    @Override
    public void onBackButtonPress() {
        changePinScreen.hide();
        showMenu();
    }

    @Override
    public void onCorrectCardNum() {
        this.cardNumber = startScreen.getCardNumber();
        System.out.println(cardNumber);
        startScreen.hide();
        pinScreen = new PinScreen(this, this.cardNumber);
        pinScreen.show();

    }


}
