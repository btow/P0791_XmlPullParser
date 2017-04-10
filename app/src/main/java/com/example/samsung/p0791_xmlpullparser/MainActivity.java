package com.example.samsung.p0791_xmlpullparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tmp = "", message = "";

        try {
            XmlPullParser xmlPullParser = prepareXpp();

            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                message = "";

                switch (xmlPullParser.getEventType()) {
                    //Начало документа
                    case XmlPullParser.START_DOCUMENT :
                        message = "START_DOCUMENT";
                        break;
                    //Начало тэга
                    case XmlPullParser.START_TAG :
                        message = "START_TAG: name = " + xmlPullParser.getName()
                                + ", depth = " + xmlPullParser.getDepth()
                                + ", attrCount = " + xmlPullParser.getAttributeCount();
                        tmp = "";

                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                            tmp = tmp + xmlPullParser.getAttributeName(i)
                                    + "=" + xmlPullParser.getAttributeValue(i) + ", ";
                        }

                        if (!TextUtils.isEmpty(tmp)) {
                            message = "Attributes: " + tmp;
                        }

                        break;
                    //Конец тега
                    case XmlPullParser.END_TAG :
                        message = "END_TAG: name = " + xmlPullParser.getName();
                        break;
                    //Содержимое тэга
                    case XmlPullParser.TEXT :
                        message = "text = " + xmlPullParser.getText();
                        break;
                    default:
                        break;
                }

                Log.d(LOG_TAG, message);
                //Следующий элемент
                xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data);
    }
}
