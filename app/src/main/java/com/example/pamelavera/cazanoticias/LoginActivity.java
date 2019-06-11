package com.example.pamelavera.cazanoticias;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private MediaPlayer mp3Error;
    private EditText etxtEmailLogin, etxtPasswordLogin;
    String strEmailLogin, strPasswordLogin;
    //final static String emailValidar = "admin";
    //final static String contraseñaValidar = "admin";
    // String emailMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etxtEmailLogin = findViewById(R.id.etxtEmail);
        etxtPasswordLogin = findViewById(R.id.etxtPassword);
        // emailMatch= "/^(([^<>()[\\]\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@(([^<>()[\\]\\.,;:\\s@\\\"]+\\.)+[^<>()[\\]\\.,;:\\s@\\\"]{2,})$/i\n";
    }

    public void botonIngresar(View view) {
        strEmailLogin = etxtEmailLogin.getText().toString();
        strPasswordLogin = etxtPasswordLogin.getText().toString();
        Log.e("CAJAS DE TEXTO", strEmailLogin + " " + strPasswordLogin);
        //(TextUtils.isEmpty(strEmailLogin)) && (!strEmailLogin.matches(emailMatch))
        //Verificamos que las cajas de texto no esten vacías
        if ((TextUtils.isEmpty(strEmailLogin))) {
            etxtEmailLogin.requestFocus();
            etxtEmailLogin.setError(getResources().getString((R.string.vacio_email)));
            errorMp3();

        } else if (TextUtils.isEmpty(strPasswordLogin)) {
            etxtPasswordLogin.requestFocus();
            etxtPasswordLogin.setError(getResources().getString((R.string.vacio_contraseña)));
            errorMp3();
        } else {
            new AsyncLogin().execute(strEmailLogin, strPasswordLogin);
        }
    }

    class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() { //se realizan los trabajos previos a la ejecucion
            pDialog = new ProgressDialog(LoginActivity.this); //getApplicationContext()
            pDialog.setMessage(getResources().getString(R.string.pb_ingresando)); //se le dio un contexto, ya que el setMessage admite int no String
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {//aqui se realiza la tarea
            strEmailLogin = etxtEmailLogin.getText().toString();
            strPasswordLogin = etxtPasswordLogin.getText().toString();
            UtilConection util= new UtilConection(strEmailLogin,strPasswordLogin);
           if (util.login()) {
                return "OK"; //true
            } else {
                return "ERROR"; //false
            }
        }

        @Override
        protected void onPostExecute(String result) { //se muestra al usuario el resultado de la tarea
            pDialog.dismiss();
            if (result.equals("OK")) {
                Log.e("onPostExecute OK ", result);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Email", etxtEmailLogin.getText().toString().trim());
                startActivity(intent);
            } else {
                Log.e("onPostExecute ERROR", result);

                Toast.makeText(LoginActivity.this, R.string.validacion_datos, Toast.LENGTH_SHORT).show();
                etxtEmailLogin.setText("");
                etxtPasswordLogin.setText("");
                etxtEmailLogin.requestFocus();
            }
        }
    }

    private void errorMp3() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(400);
        mp3Error = MediaPlayer.create(this, R.raw.error);
        mp3Error.start();
    }

    public void registroUsuario(View view) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","La actividad Login ahora se encuentra Resumida");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","La actividad Login se encuentra pausada");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","La actividad Login se encuentra detenida");
        etxtEmailLogin.setText("");
        etxtPasswordLogin.setText("");
        etxtEmailLogin.requestFocus();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","La actividad Login ha sido destruida o cerrada");
    }
}