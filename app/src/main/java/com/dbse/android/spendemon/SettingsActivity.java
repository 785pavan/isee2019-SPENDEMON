package com.dbse.android.spendemon;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceFragmentCompat;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class SettingsActivity extends AppCompatActivity {

    Button bDeleteAll;
    private SummaryViewModel summaryViewModel;
    Button bDownloadReport;
    private int testNumber = 0;
    private static final int STORAGE_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class);
        bDeleteAll = findViewById(R.id.bDeleteAll);
        bDownloadReport = findViewById(R.id.download_report);

//        adding the warning button for delete all:
//        bDeleteAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Button","deleteAll clicked");
//                AlertDialog.Builder builder;
//                builder = new AlertDialog.Builder(getApplicationContext());
//                Log.i("builder: ", String.valueOf(builder));
//                builder.setMessage("Are you sure you want to reset all the data?")
//                        .setTitle("Reset All")
//                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Log.i("builder setMessage:","successful");
//                                summaryViewModel.deleteAllData();
//                            }
//                        })
//                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                        // CANCEL
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        bDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(R.string.del_msg)
                        .setTitle(R.string.confirm_dialog_title)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                summaryViewModel.deleteAllData();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            private Object PrintAttributes;

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Cancel
                                //PrintedPdfDocument doncumentPdf = new PrintedPdfDocument(getApplicationContext(), (android.print.PrintAttributes) PrintAttributes);



                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        bDownloadReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("clicked","inside onClick");
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){

                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                        Log.i("clicked","permission denied");
                    }
                    else {
                        try {
                            createPDF();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }

                    }
                }
                else{
                    try {
                        createPDF();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }

                }
//                try {
//                    createPDF();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (DocumentException e) {
//                    e.printStackTrace();
//                }

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                permission granted
                }
                else {
//                    permission denied
                    Toast.makeText(this, "permission denied...!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void createPDF() throws FileNotFoundException, DocumentException {
        // DocumentFile doc = new DocumentFile();
        Document mDoc = new Document();
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();

            String mText = "testing";
            mDoc.addAuthor("Behnam");
            mDoc.add(new Paragraph(mText));
            mDoc.close();

            Toast.makeText(this,"try initiated", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


//        Document document = new Document();
//
//        PdfWriter.getInstance(document, new FileOutputStream("sample1.pdf"));
//        document.open();
//        document.add(new Paragraph("Sample 1: Hello, this is SPENDEMON"));
//        document.close();
//        Log.i("clicked","Done");
    }
}