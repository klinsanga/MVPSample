package th.co.omc.mvpsample.implement;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import th.co.omc.mvpsample.interactor.Interactor;
import th.co.omc.mvpsample.model.OrderhistoryModel;
import th.co.omc.mvpsample.utils.MyApplication;

/**
 * Created by teera-s on 12/9/2016 AD.
 */

public class InteractorImplement implements Interactor {
    public static final String TAG = InteractorImplement.class.getSimpleName();

    private OrderhistoryModel orderhistoryModel;
    private List<OrderhistoryModel> orderhistoryModelList = new ArrayList<OrderhistoryModel>();

    public InteractorImplement() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://203.146.170.60/~demomlm/new/app/v1.0/index.php/member/report/sale/bill", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                JSONObject obj;
                JSONArray jsonArray;
                JSONObject jsonObject;
                try {
                    if (response.startsWith("[")) {
                        jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            orderhistoryModel = new OrderhistoryModel(
                                    jsonObject.getString("date"),
                                    jsonObject.getString("sano"),
                                    jsonObject.getString("ability"),
                                    jsonObject.getString("tot_pv"),
                                    jsonObject.getString("total"),
                                    jsonObject.getString("uid"),
                                    jsonObject.getBoolean("sendsend"),
                                    jsonObject.getBoolean("sender"),
                                    jsonObject.getBoolean("receive"),
                                    jsonObject.getString("remark"),
                                    jsonObject.getString("lid")
                            );
                            orderhistoryModelList.add(orderhistoryModel);
                        }
                    } else {
                        obj = new JSONObject(response);
                        if (obj.getString("STATUS").equals("FAIL")) {
                            Log.e(TAG, obj.getString("MESSAGE"));
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json order history parsing error: " + e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                    }
                }){

            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id", "0000001");
                params.put("api_key", "1234");
                params.put("month", "2016-11");
                return params;
            }

        };
        MyApplication.getInstance().getRequestQueue().add(stringRequest);
    }

    @Override
    public List<OrderhistoryModel> getOrderhistoryModel() {
        return orderhistoryModelList;
    }
}
