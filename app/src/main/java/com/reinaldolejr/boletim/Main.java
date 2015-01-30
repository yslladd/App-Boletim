package com.reinaldolejr.boletim;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.reinaldolejr.Util.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView image = (ImageView) findViewById(R.id.imageView_boletim);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(Util.verificaConexao(Main.this)) {

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
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Favor inserir um código válido", Toast.LENGTH_LONG);
                            toast.show();
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
