package com.example.languagetranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class MainActivity extends AppCompatActivity {

    //Initializing all the views in layout.
    private EditText source;
    private TextView target;
    private Button button;
    private Spinner fromSpinner;
    private Spinner toSpinner;

    //Languages array data
    String[] fromLanguages = {
            "from", "English", "Hindi", "Urdu", "Japanese", "Arabic", "French", "Spanish", "German",
            "Italian", "Korean", "Tamil", "Bengali", "Chinese", "Russian"
    };

    String[] toLanguages = {

            "to",  "English", "Hindi", "Urdu", "Japanese", "Arabic", "French", "Spanish", "German",
            "Italian", "Korean", "Tamil", "Bengali", "Chinese", "Russian"
    };

    //permission
    private static  final int REQUEST_CODE = 1;

    private String fromLanguageCode, toLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        source = findViewById(R.id.sourceText);
        target = findViewById(R.id.targetText);
        button = findViewById(R.id.translateButton);
        fromSpinner = findViewById(R.id.sourceSpinner);
        toSpinner = findViewById(R.id.targetSpinner);

        //Spinner-1
        //1-Setting date in spinner
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fromLanguages);

        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguageCode = getLanguageCode(fromLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner-2
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, toLanguages);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguageCode = getLanguageCode(toLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Translate functionality when translate button has been clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target.setText("");

                if(source.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter the text", Toast.LENGTH_SHORT).show();
                }else if(fromLanguageCode.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please select the source language", Toast.LENGTH_SHORT).show();
                }else if(toLanguageCode.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please select the target language", Toast.LENGTH_SHORT).show();
                }else{
                    translateText(fromLanguageCode, toLanguageCode, source.getText().toString());
                }
            }
        });

    }

    //Method to translate languages using ML kit
    @SuppressLint("SetTextI18n")
    private void translateText(String fromLanguageCode, String toLanguageCode, String src) {
        target.setText("Downloading Model...");

        try {
            TranslatorOptions options = new TranslatorOptions.Builder().
                    setSourceLanguage(fromLanguageCode).setTargetLanguage(toLanguageCode).build();

            Translator translator = Translation.getClient(options);

            DownloadConditions downloadConditions = new DownloadConditions.Builder().build();

            translator.downloadModelIfNeeded(downloadConditions).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    target.setText("Translating...");

                    translator.translate(src).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            target.setText(s);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "" +
                                    "failed to translate", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,
                            "Failed to download the language", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to get the language code.
    private String getLanguageCode(String fromLanguage) {

        String languageCode = "";

        switch (fromLanguage){
            case "English":
                languageCode = TranslateLanguage.ENGLISH;
                break;
            case "Hindi":
                languageCode = TranslateLanguage.HINDI;
                break;
            case "Urdu":
                languageCode = TranslateLanguage.JAPANESE;
                break;
            case "Arabic":
                languageCode = TranslateLanguage.ARABIC;
                break;
            case "French":
                languageCode = TranslateLanguage.FRENCH;
                break;
            case "Spanish":
                languageCode = TranslateLanguage.SPANISH;
                break;
            case "German":
                languageCode = TranslateLanguage.GERMAN;
                break;
            case "Italian":
                languageCode = TranslateLanguage.ITALIAN;
                break;
            case "Korean":
                languageCode = TranslateLanguage.KOREAN;
                break;
            case "Tamil":
                languageCode = TranslateLanguage.TAMIL;
                break;
            case "Bengali":
                languageCode = TranslateLanguage.BENGALI;
                break;
            case "Chinese":
                languageCode = TranslateLanguage.CHINESE;
                break;
            case "Russian":
                languageCode = TranslateLanguage.RUSSIAN;
                break;
            default:
                languageCode = "";
        }

        return languageCode;


    }
}