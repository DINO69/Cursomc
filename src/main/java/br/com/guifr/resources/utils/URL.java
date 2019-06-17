package br.com.guifr.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String str) {
		String retorno;
		try {
			retorno = URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			retorno = "";
		}
		
		return retorno;
	}
	
	public static List<Integer> decodeIntList(String str, String delimiter){
		String[] vet = str.split(delimiter);
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
		
		//return Arrays.asList(str.split(delimiter)).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		
	}

}
