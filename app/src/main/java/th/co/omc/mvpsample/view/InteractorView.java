package th.co.omc.mvpsample.view;

import java.util.List;

import th.co.omc.mvpsample.model.OrderhistoryModel;

/**
 * Created by teera-s on 12/9/2016 AD.
 */

public interface InteractorView {
    void setRecyclerView(List<OrderhistoryModel> orderhistoryModelList);
}
