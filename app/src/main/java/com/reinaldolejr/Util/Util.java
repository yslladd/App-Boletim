package com.reinaldolejr.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Reinaldo on 1/23/2015.
 */
public class Util {

    public static boolean verificaConexao(Context contexto){

        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);//Pego a conectividade do contexto o qual o metodo foi chamado

        NetworkInfo netInfo = cm.getActiveNetworkInfo();//Crio o objeto netInfo que recebe as informacoes da NEtwork

        if ( (netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable()) ) //Se o objeto for nulo ou nao tem conectividade retorna false
            return true;
        else
            return false;
    }

}
