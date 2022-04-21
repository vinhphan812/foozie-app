package vn.edu.huflit.foozie_app.API;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import vn.edu.huflit.foozie_app.Models.Branch;
import vn.edu.huflit.foozie_app.Models.Food;
import vn.edu.huflit.foozie_app.Models.FoodType;
import vn.edu.huflit.foozie_app.Models.Notification;
import vn.edu.huflit.foozie_app.Models.Order;
import vn.edu.huflit.foozie_app.Models.ResponseDTO;
import vn.edu.huflit.foozie_app.Models.User;
import vn.edu.huflit.foozie_app.Models.Voucher;

public class API {
    // region Property
    String HOST = "http://192.168.1.3:3000";
    private final OkHttpClient client;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    final private Gson gson = new Gson();
    // endregion

    //region Constructor
    public API() {
        CookieJar cookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, @NonNull List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
        client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
    }
    //endregion

    // region Method

    // region Request Server
    private ResponseDTO requestServer(String path) {
        return requestServer(path, null, "GET");
    }

    private ResponseDTO requestServer(String path, RequestBody body) {
        return requestServer(path, null, body, "POST");
    }

    private ResponseDTO requestServer(String path, String params) {
        return requestServer(path, params, null, null);
    }

    private ResponseDTO requestServer(String path, RequestBody body, String method) {
        return requestServer(path, null, body, method);
    }

    private ResponseDTO requestServer(String path, String params, RequestBody body, String method) {
        RequestTask request = new RequestTask();
        try {
            return request.execute(path, params, body, method).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    // endregion

    // region Auth
    public String Login(String username, String password) throws Exception {
        HashMap map = new HashMap();
        map.put("user", username);
        map.put("pass", password);
        String jsonBody = gson.toJson(map);

        ResponseDTO res = requestServer("/auth/login", RequestBody.create(JSON, jsonBody));

        res.isInvalid();

        return res.message;
    }

    public String SignUp(String username, String password, String first_name, String last_name, String email, String phone) throws Exception {
        try {
            HashMap map = new HashMap();

            map.put("username", username);
            map.put("password", password);
            map.put("first_name", first_name);
            map.put("last_name", last_name);
            map.put("email", email);
            map.put("phone", phone);

            ResponseDTO res = requestServer("/auth/signup", RequestBody.create(JSON, gson.toJson(map)));

            res.isInvalid();

            return res.message;
        } catch (Exception e) {
            throw e;
        }
    }

    public String Logout() {
        ResponseDTO res = requestServer("/auth/logout");
        return res.message;
    }
    // endregion

    //region User
    public User getMe() throws Exception {
        ResponseDTO res = requestServer("/api/user");

        res.isInvalid();

        return gson.fromJson(gson.toJson(res.data), User.class);
    }

    public List<Voucher> getMyVouchers() throws Exception {

        ResponseDTO res = requestServer("/api/user/vouchers");

        res.isInvalid();

        Type listType = new TypeToken<List<Voucher>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public List<Notification> getNotifications() throws Exception {
        ResponseDTO res = requestServer("/api/user/notifications");

        res.isInvalid();

        Type listType = new TypeToken<List<Notification>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public List<Order> getHistoryOrders() throws Exception {
        ResponseDTO res = requestServer("/api/user/history-orders");

        res.isInvalid();

        Type listType = new TypeToken<List<Order>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public String seenNotifications(String[] ids) {
        HashMap map = new HashMap();
        map.put("ids", ids);
        map.put("seen", true);

        String jsonBody = gson.toJson(map);

        ResponseDTO res = requestServer("/api/user/seen-notifications", RequestBody.create(JSON, jsonBody));

        return res.message;
    }

    public String createOrders(String branchId, String note, String voucherShipId, String voucherUsingId) {
        HashMap map = new HashMap();
        map.put("branch", branchId);
        map.put("note", note);
        map.put("voucher_ship", voucherShipId);
        map.put("voucher_using", voucherUsingId);

        String jsonBody = gson.toJson(map);

        ResponseDTO res = requestServer("/api/user/orders", RequestBody.create(JSON, jsonBody));

        return res.message;
    }

    public Order getOrder(String id) throws Exception {
        ResponseDTO res = requestServer("/api/user/orders/" + id);

        res.isInvalid();

        return gson.fromJson(gson.toJson(res.data), Order.class);
    }


    public String takeVoucher(String id) {
        HashMap map = new HashMap();
        map.put("id", id);

        String jsonBody = gson.toJson(map);

        ResponseDTO res = requestServer("/api/user/take-voucher", RequestBody.create(JSON, jsonBody));

        return res.message;
    }
    //endregion

    //region Public
    public List<Food> getFoods() throws Exception {
        ResponseDTO res = requestServer("/api/foods");

        res.isInvalid();

        Type listType = new TypeToken<List<Food>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public List<FoodType> getFoodTypes() throws Exception {
        ResponseDTO res = requestServer("/api/food-type");

        res.isInvalid();

        Type listType = new TypeToken<List<FoodType>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public List<Branch> getBranches() throws Exception {
        ResponseDTO res = requestServer("/api/branches");

        res.isInvalid();

        Type listType = new TypeToken<List<Branch>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public List<Food> getCart() throws Exception {
        ResponseDTO res = requestServer("/api/cart");

        res.isInvalid();

        Type listType = new TypeToken<List<Food>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }

    public String addCart(String id) {
        HashMap map = new HashMap();
        map.put("id", id);
        String jsonBody = gson.toJson(map);

        ResponseDTO res = requestServer("/api/cart", RequestBody.create(JSON, jsonBody));

        return res.message;
    }

    public Food getFood(String id) throws Exception {
        ResponseDTO res = requestServer("/api/foods/" + id);
        res.isInvalid();
        return gson.fromJson(gson.toJson(res.data), Food.class);
    }

    public List<Voucher> getVoucherPublic() throws Exception {
        ResponseDTO res = requestServer("/api/vouchers");

        res.isInvalid();

        Type listType = new TypeToken<List<Voucher>>() {
        }.getType();

        return gson.fromJson(gson.toJson(res.data), listType);
    }
    //endregion

    // endregion

    // region RequestTask
    class RequestTask extends AsyncTask<Object, String, ResponseDTO> {
        @Override
        protected ResponseDTO doInBackground(Object... objects) {
            String path = (String) objects[0];
            String params = (String) objects[1];
            RequestBody body = (RequestBody) objects[2];
            String method = (String) objects[3];

            String url = HOST + path;

            Log.d("CALL_API", String.format("%s - %s", method, url));

            Request request = new Request.Builder().method(method, body).url(url).build();

            try {
                Response res = client.newCall(request).execute();
                if (!res.isSuccessful()) return null;

                String json = res.body().string();

                Log.d("RESPONSE_DATA", json);

                return gson.fromJson(json, ResponseDTO.class);
            } catch (IOException e) {
                Log.e("RequestError", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ResponseDTO responseDTO) {
            super.onPostExecute(responseDTO);
        }
    }
    //endregion
}