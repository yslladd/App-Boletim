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

import com.google.gson.Gson;
import com.reinaldolejr.Util.Util;
import com.reinaldolejr.vo.bulletinVO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Reinaldo on 1/13/2015.
 */
public class Look_note extends Activity {

    private final String URL = "https://api-boletim.herokuapp.com/api/boletim/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_note);

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

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String code = getIntent().getStringExtra("code");
        HttpClient client = new DefaultHttpClient();

        HttpGet request = new HttpGet(URL+code);

        HttpResponse response;
        try {
            response = client.execute(request);
            HttpEntity ent = response.getEntity();

            Gson gson = new Gson();
            bulletinVO vo =  gson.fromJson(EntityUtils.toString(ent), bulletinVO.class);
            if(vo != null) {
                matter.setText(vo.getMatter());
                team.setText(vo.getTeam());
                student1.setText(vo.getStudents().get(0).getName());
                note1.setText(vo.getStudents().get(0).getNote() == null ? "" : vo.getStudents().get(0).getNote().toString());
                student2.setText(vo.getStudents().get(1).getName());
                note2.setText(vo.getStudents().get(1).getNote() == null ? "" : vo.getStudents().get(1).getNote().toString());
                student3.setText(vo.getStudents().get(2).getName());
                note3.setText(vo.getStudents().get(2).getNote() == null ? "" : vo.getStudents().get(2).getNote().toString());
                student4.setText(vo.getStudents().get(3).getName());
                note4.setText(vo.getStudents().get(3).getNote() == null ? "" : vo.getStudents().get(3).getNote().toString());
                student5.setText(vo.getStudents().get(4).getName());
                note5.setText(vo.getStudents().get(4).getNote() == null ? "" : vo.getStudents().get(4).getNote().toString());
            }else{
                Intent intent = new Intent(getBaseContext(), Main.class);
                startActivity(intent);

                Toast toast = Toast.makeText(getApplicationContext(), "Favor inserir um código válido", Toast.LENGTH_LONG);
                toast.show();
            }
            Log.d("Response of GET request", response.toString());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(Util.verificaConexao(Look_note.this)) {

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
