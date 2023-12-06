package com.example.chatgptapp;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CallOpenAITask extends AsyncTask<String, Void, String> {

    private OnTaskCompleted listener;
    private String OpenAPIKey = "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";

    public CallOpenAITask(OnTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{ \"prompt\":\"" + strings[0] + "\", \"max_tokens\": 150 }");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/davinci/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + OpenAPIKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.isSuccessful()) {
                JSONObject jsonObject = new JSONObject(responseBody);
                return jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "Error fetching response";
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            listener.onTaskCompleted(result);
        }
    }
}
