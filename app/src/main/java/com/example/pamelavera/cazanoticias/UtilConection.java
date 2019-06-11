package com.example.pamelavera.cazanoticias;

import android.os.SystemClock;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hp1 on 10-11-2017.
 */

public class UtilConection {
    private static String strConexion = "http://www.pvera.cazanoticias.desarrollociisa.cl/webservices/funciones.php";
    Httppostaux post = new Httppostaux();
    String User;
    String Pass;
    String Email;

    String titulo;
    String fecha;
    String cuerpo;
    String ubicacion;
    String imagen;

    public UtilConection(String User) {
        this.User = User;
    }

    public UtilConection(String User, String Pass) {
        this.User = User;
        this.Pass = Pass;
    }

    public UtilConection(String User, String Pass, String Email) {
        this.User = User;
        this.Pass = Pass;
        this.Email = Email;
    }
    public UtilConection(String User, String titulo, String fecha, String cuerpo, String ubicacion, String imagen) {
        this.User = User;
        this.titulo = titulo;
        this.fecha = fecha;
        this.cuerpo = cuerpo;
        this.ubicacion = ubicacion;
        this.imagen = imagen;

    }

    public boolean login() {
        int IntSuccess=-1;
        String StrMessage = "";

        //Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
        //y enviarlo mediante POST a nuestro sistema para relizar la validacion
        ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
        parametros.add(new BasicNameValuePair("correo",Email));
        parametros.add(new BasicNameValuePair("contrasena",Pass));

        //realizamos una peticion y como respuesta un array JSON
        String strParametros = "?metodo=read&correo="+Email+"&contrasena="+Pass;
        org.json.JSONArray jdata = post.getserverdata(parametros, strConexion + strParametros);

        SystemClock.sleep(200);

        //si lo que obtuvimos no es null
        if (jdata != null && jdata.length() > 0){
            JSONObject json_data; //creamos un objeto JSON

            try {
                json_data = jdata.getJSONObject(0);
                IntSuccess = json_data.getInt("success");//accedemos al valor
                StrMessage = json_data.getString("message");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (IntSuccess == 0){// [{"IntSuccess":"0"}]
                return false;
            }
            else{// [{"IntSuccess":"1"}]
                return true;
            }
        }else{	//json obtenido invalido verificar parte WEB.
            return false;
        }
    }
    public boolean crearRegistro() {
        int IntSuccess=-1;
        String StrMessage = "";

        //Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
        //y enviarlo mediante POST a nuestro sistema para relizar la validacion
        ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
        parametros.add(new BasicNameValuePair("nombre",User));
        parametros.add(new BasicNameValuePair("contrasena",Pass));
        parametros.add(new BasicNameValuePair("correo",Email));

        //realizamos una peticion y como respuesta un array JSON
        String strParametros = "?metodo=insertar&nombre="+User+"&contrasena="+Pass+"&correo="+Email;
        org.json.JSONArray jdata = post.getserverdata(parametros, strConexion + strParametros);

        SystemClock.sleep(450);

        //si lo que obtuvimos no es null
        if (jdata != null && jdata.length() > 0){
            JSONObject json_data; //creamos un objeto JSON

            try {
                json_data = jdata.getJSONObject(0);
                IntSuccess = json_data.getInt("success");//accedemos al valor
                StrMessage = json_data.getString("message");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (IntSuccess == 0){// [{"IntSuccess":"0"}]
                return false;
            }
            else{// [{"IntSuccess":"1"}]
                return true;
            }
        }else{	//json obtenido invalido verificar parte WEB.
            return false;
        }
    }
    public boolean crearNoticia() { //falta bd
        int IntSuccess=-1;
        String StrMessage = "";

        //Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
        //y enviarlo mediante POST a nuestro sistema para relizar la validacion
        ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
        parametros.add(new BasicNameValuePair("nombre_usuario",User));
        parametros.add(new BasicNameValuePair("titulo",titulo));
        parametros.add(new BasicNameValuePair("fecha",fecha));
        parametros.add(new BasicNameValuePair("cuerpo",cuerpo));
        parametros.add(new BasicNameValuePair("ubicacion",ubicacion));
        parametros.add(new BasicNameValuePair("imagen",imagen));

        //realizamos una peticion y como respuesta un array JSON

        String strParametros = "?metodo=registro_noticia2&nombre_usuario="+User+"&titulo="+titulo+"&fecha="+fecha+"&cuerpo="+cuerpo+"&ubicacion="+ubicacion+"&imagen="+imagen;
        org.json.JSONArray jdata = post.getserverdata(parametros, strConexion + strParametros);

        SystemClock.sleep(450);

        //si lo que obtuvimos no es null
        if (jdata != null && jdata.length() > 0){
            JSONObject json_data; //creamos un objeto JSON

            try {
                json_data = jdata.getJSONObject(0);
                IntSuccess = json_data.getInt("success");//accedemos al valor
                StrMessage = json_data.getString("message");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (IntSuccess == 0){// [{"IntSuccess":"0"}]
                return false;
            }
            else{// [{"IntSuccess":"1"}]
                return true;
            }
        }else{	//json obtenido invalido verificar parte WEB.
            return false;
        }
    }
    public boolean recuperarPassword(){
        int IntSuccess=-1;
        String StrMessage = "";

        //Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
        //y enviarlo mediante POST a nuestro sistema para relizar la validacion
        ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
        parametros.add(new BasicNameValuePair("nombre",User));


        //realizamos una peticion y como respuesta un array JSON
        String strParametros = "?metodo=read&nombre="+User;
        org.json.JSONArray jdata = post.getserverdata(parametros, strConexion + strParametros);

        SystemClock.sleep(450);

        //si lo que obtuvimos no es null
        if (jdata != null && jdata.length() > 0){
            JSONObject json_data; //creamos un objeto JSON

            try {
                json_data = jdata.getJSONObject(0);
                IntSuccess = json_data.getInt("success");//accedemos al valor
                StrMessage = json_data.getString("message");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //validamos el valor obtenido
            if (IntSuccess == 0){// [{"IntSuccess":"0"}]
                return false;
            }
            else{// [{"IntSuccess":"1"}]
                return true;
            }
        }else{	//json obtenido invalido verificar parte WEB.
            return false;
        }
    }
}
