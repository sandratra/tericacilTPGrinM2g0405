/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Util {
    public static Timestamp getDateDuJour(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
    
    public static String recherche(String title) {
        String joined = null;
        if(title==null){
            joined = "%%";
        }else{
            String[] liste = title.split(" ");
            joined = String.join("%", liste);
            joined = "%"+joined+"%";
        }
        return joined;
    }
}
