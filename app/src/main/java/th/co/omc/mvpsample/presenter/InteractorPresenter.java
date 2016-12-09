package th.co.omc.mvpsample.presenter;

import th.co.omc.mvpsample.interactor.Interactor;
import th.co.omc.mvpsample.view.InteractorView;

/**
 * Created by teera-s on 12/9/2016 AD.
 */

public class InteractorPresenter {

    InteractorView view;
    Interactor interactor;

    public InteractorPresenter(Interactor interactor) {
        this.interactor = interactor;
    }

    public void bind(InteractorView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    private void displayList() {
        interactor.getOrderhistoryModel();
    }
}
