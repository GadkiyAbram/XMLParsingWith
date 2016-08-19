package com.example.aleksandrabramovski.xmlparsingwith;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView xmlContentTextView = (TextView) findViewById(R.id.textView);
        String xmlContent;
        try {
            xmlContent = getEventsFromXML(this);
            xmlContentTextView.setText(xmlContent);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getEventsFromXML(Activity activity) throws XmlPullParserException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Resources res = activity.getResources();
        XmlResourceParser xmlResourceParser = res.getXml(R.xml.valute);
        xmlResourceParser.next();
        int eventType = xmlResourceParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
           if (eventType == XmlPullParser.TEXT) {
                   stringBuilder.append("\nTEXT: ").append(xmlResourceParser.getText());
            }
            eventType = xmlResourceParser.next();
        }
        return stringBuilder.toString();
    }
}
