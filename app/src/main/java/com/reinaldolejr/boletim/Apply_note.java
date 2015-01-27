package com.reinaldolejr.boletim;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reinaldolejr.Util.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reinaldo on 1/13/2015.
 */
public class Apply_note extends Activity {

    private final String URL = "https://api-boletim.herokuapp.com/api/boletim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_note);

        Button save = (Button)findViewById(R.id.button_save);
        final TextView code = (TextView)findViewById(R.id.textView_code);
        final EditText matter = (EditText)findViewById(R.id.editText_matter);
        final EditText team = (EditText)findViewById(R.id.editText_team);
        final EditText student1 = (EditText)findViewById(R.id.editText_student1);
        final EditText note1 = (EditText)findViewById(R.id.editText_note1);
        final EditText student2 = (EditText)findViewById(R.id.editText_student2);
        final EditText note2 = (EditText)findViewById(R.id.editText_note2);
        final EditText student3 = (EditText)findViewById(R.id.editText_student3);
        final EditText note3 = (EditText)findViewById(R.id.editText_note3);
        final EditText student4 = (EditText)findViewById(R.id.editText_student4);
        final EditText note4 = (EditText)findViewById(R.id.editText_note4);
        final EditText student5 = (EditText)findViewById(R.id.editText_student5);
        final EditText note5 = (EditText)findViewById(R.id.editText_note5);


        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                if(Util.verificaConexao(Apply_note.this)) {


                    HttpClient httpClient = new DefaultHttpClient();
                    // replace with your url
                    HttpPost httpPost = new HttpPost(URL);


                    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                    nameValuePair.add(new BasicNameValuePair("matter", matter.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("team", team.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("name1", student1.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("note1", note1.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("name2", student2.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("note2", note2.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("name3", student3.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("note3", note3.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("name4", student4.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("note4", note4.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("name5", student5.getText().toString()));
                    nameValuePair.add(new BasicNameValuePair("note5", note5.getText().toString()));
                    //Encoding POST data
                    try {
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        // log exception
                        e.printStackTrace();
                    }
                    //making POST request.
                    try {
                        HttpResponse response = httpClient.execute(httpPost);
                        HttpEntity ent = response.getEntity();
                        code.setText(EntityUtils.toString(ent));

                        Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT);
                        toast.show();

                        // write response to log
                        Log.d("Http Post Response:", response.toString());
                    } catch (ClientProtocolException e) {
                        // Log exception
                        e.printStackTrace();
                    } catch (IOException e) {
                        // Log exception
                        e.printStackTrace();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Favor verificar a conexão de internet!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(Util.verificaConexao(Apply_note.this)) {

            if (id == R.id.apply_note) {
                Intent intent_apply = new Intent(getBaseContext(), Apply_note.class);
                startActivity(intent_apply);
            } else if (id == R.id.look_note) {
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_confirm_code);
                dialog.setTitle("Favor inserir o código!");

                final EditText editText = (EditText) dialog.findViewById(R.id.editText_code);
                Button confirm = (Button) dialog.findViewById(R.id.button_confirm);
                dialog.show();

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editText.getText().toString().isEmpty()) {
                            Intent intent_look = new Intent(getBaseContext(), Look_note.class);
                            intent_look.putExtra("code", editText.getText().toString());
                            startActivity(intent_look);
                        }


                    }
                });
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Favor verificar a conexão de internet!", Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
