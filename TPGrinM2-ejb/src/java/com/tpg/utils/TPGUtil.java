/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.utils;

/**
 *
 * @author EBI
 */
public abstract class TPGUtil {
    public String[] getTagsFromString(String tags){
        return tags.split(";");
    }
}
