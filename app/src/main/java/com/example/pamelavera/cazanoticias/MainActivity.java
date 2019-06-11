package com.example.pamelavera.cazanoticias;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private TextView txtUserMain,  txt_DateMain;
    private EditText etxt_tituloNoticiaMain, etxt_descripcion_noticiaMain;
    private static final int INTERVALO = 2000; //2 segundos para salir
    private long tiempoPrimerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserMain =findViewById(R.id.txtUser);
        txt_DateMain= findViewById(R.id.txt_date);
        etxt_tituloNoticiaMain=findViewById(R.id.etxt_tituloNoticia);
        etxt_descripcion_noticiaMain=findViewById(R.id.etxt_descripcion_noticia);

        String etEmail= getIntent().getStringExtra("Email");
       // String etNombre= getIntent().getStringExtra("etNombre");
        txtUserMain.setText("¡BIENVENIDO" +
                " "+ etEmail +"! ");
        calendario();

    }

    public void calendario(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        txt_DateMain.setText(simpleDateFormat.format(calendar.getTime()));
    }

   @Override
    public void onBackPressed() {
        if ((tiempoPrimerClick + INTERVALO) > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        tiempoPrimerClick = System.currentTimeMillis();
    }

    public void btnLimpiar(View view){
        etxt_tituloNoticiaMain.setText("");
        calendario();
        etxt_descripcion_noticiaMain.setText("");
        etxt_tituloNoticiaMain.requestFocus();
    }

    class asyncMain extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage(getResources().getString(R.string.pb_ingresando));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

    return null;
        }


        protected void onPostExecute(String result) {
            pDialog.dismiss();
        }
    }

    public void btnRegistro(View view) {
        //Obtenemos el email y la contraseña desde las cajas de texto
        String str_tituloNoticia = etxt_tituloNoticiaMain.getText().toString().trim();
        String str_descripcion_noticia = etxt_descripcion_noticiaMain.getText().toString().trim();
        //Verificamos que las cajas de texto no esten vacías
        if ((TextUtils.isEmpty(str_tituloNoticia))) {
            Toast.makeText(MainActivity.this, R.string.main_titulo, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_descripcion_noticia)) {
            Toast.makeText(MainActivity.this, R.string.main_descripcion, Toast.LENGTH_SHORT).show();
        } else {
            new asyncMain().execute( str_descripcion_noticia,str_tituloNoticia);
            etxt_tituloNoticiaMain.setText("");
            etxt_descripcion_noticiaMain.setText("");
            etxt_tituloNoticiaMain.requestFocus();
            Toast.makeText(MainActivity.this, R.string.ingreso_noticia_exitoso, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","La actividad Login ahora se encuentra Resumida");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","La actividad Main se encuentra pausada");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","La actividad Main se encuentra detenida");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","La actividad Main ha sido destruida o cerrada");
    }
}
