package com.example.pamelavera.cazanoticias;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private TextView etxtUserRegistro, etxtEmailRegistro, etxtContraseñaRegistro1, etxtContraseñaRegistro2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etxtUserRegistro=findViewById(R.id.etxtUser);
        etxtEmailRegistro= findViewById(R.id.etxtEmail);
        etxtContraseñaRegistro1= findViewById(R.id.etxtContraseña1);
        etxtContraseñaRegistro2= findViewById(R.id.etxtContraseña2);
    }

    public void btnRegistro(View view){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String strNombreUser= etxtUserRegistro.getText().toString().trim();
        String strEmail = etxtEmailRegistro.getText().toString().trim();
        String strContraseña1 = etxtContraseñaRegistro1.getText().toString().trim();
        String strContraseña2 = etxtContraseñaRegistro1.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if ((TextUtils.isEmpty(strNombreUser))) {
            etxtUserRegistro.setError(getResources().getString((R.string.vacio_nombreUser)));
        } else if ((TextUtils.isEmpty(strEmail))) {
            etxtEmailRegistro.setError(getResources().getString((R.string.vacio_email)));
        } else if (TextUtils.isEmpty(strContraseña1)&& TextUtils.isEmpty(strContraseña2) ) {
            etxtContraseñaRegistro1.setError(getResources().getString((R.string.vacio_contraseña)));
            etxtContraseñaRegistro2.setError(getResources().getString((R.string.vacio_contraseña)));

        }else{
            new asyncRegistro().execute(strContraseña1, strContraseña2);

        }

    }
    class asyncRegistro extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        protected void onPreExecute() {

            pDialog = new ProgressDialog(Registro.this);
            pDialog.setMessage(getResources().getString(R.string.pb_ingresando));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            if ((etxtContraseñaRegistro1.getText().toString().trim().equals(etxtContraseñaRegistro2.getText().toString().trim()))) {
                return "OK"; //true
            } else {
                return "ERROR"; //false
            }
        }


        protected void onPostExecute(String result) {
            pDialog.dismiss();
            if (result.equals("OK")) {
                Log.e("onPostExecute OK ", result);

             Intent intent = new Intent(Registro.this, LoginActivity.class);
               startActivity(intent);

            } else {
                Log.e("onPostExecute ERROR", result);

                Toast.makeText(Registro.this, R.string.validacion_datos, Toast.LENGTH_SHORT).show();

                etxtContraseñaRegistro1.setText("");
                etxtContraseñaRegistro2.setText("");
                etxtContraseñaRegistro1.requestFocus();

            }


        }
    }
    protected void onStop() {
        super.onStop();
        Log.d("onStop","La actividad Login se encuentra detenida");

    }
}
