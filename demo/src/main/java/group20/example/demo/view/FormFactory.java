package group20.example.demo.view;

import org.springframework.context.ApplicationContext;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class FormFactory {

    public static IForm createForm(FormType type, ApplicationContext context, UserModel user, AccountModel account) {
        switch (type) {
            case MAIN:
                return new MainForm(context, user, account);
            case WITHDRAW:
                return new WithDrawForm(context, user, account);
            case DEPOSIT:
                return new DepositForm(context, user, account);
            case TRANSFER:
                return new MoneyTransferForm(context, user, account);
            case PIN:
                return new PINForm(context, user, account);
            case PROFILE:
                return new ProfileForm(context, MainForm.getInstance(context, user, account), user, account);
            default:
                throw new IllegalArgumentException("Unknown form type: " + type);
        }
    }
}
