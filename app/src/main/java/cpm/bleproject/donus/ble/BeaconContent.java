package cpm.bleproject.donus.ble;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.util.EncodingUtils;

import java.util.Locale;

public class BeaconContent extends AppCompatActivity {
    private final String URL = "http://ibeacon-donus.rhcloud.com/getPage.php";
    private String uuid;
    private String major;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_content);
        WebView webView = (WebView) findViewById(R.id.web_view);
        Bundle extras = getIntent().getExtras();
        if(!extras.isEmpty()){
            uuid = extras.getString("UUID");
            major = extras.getString("major");
            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status == TextToSpeech.SUCCESS) {
                        textToSpeech.setLanguage(Locale.UK);
                        textToSpeech.speak("you are got a promotion",TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
            });
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String postData = "uuid="+uuid+"&major="+major;
        webView.postUrl(URL, EncodingUtils.getBytes(postData, "BASE64"));
    }
}
