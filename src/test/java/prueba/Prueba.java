package prueba;

import services.Encriptar;

public class Prueba {

    public static void main(String[] args) {
        System.out.println("Encriptar Hola: " + Encriptar.encriptar("Hola"));
    }

    public static String convertirCadena(String texto) {
        String cadenaConvertida = "", cadenaTemp = "";
        int indice=0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                indice = i;
                cadenaTemp = texto.substring(0, i);
                cadenaTemp = cadenaTemp.substring(0, 1).toUpperCase() + cadenaTemp.substring(1).toLowerCase();
                break;
            }
        }
        cadenaConvertida += cadenaTemp;
        for (int i = indice + 1; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                cadenaTemp = texto.substring(indice, i);
                cadenaTemp = cadenaTemp.substring(0, 1).toUpperCase() + cadenaTemp.substring(1).toLowerCase();
                break;
            }
        }
        cadenaConvertida += cadenaTemp;
        return cadenaConvertida;
    }
}

//public static void main(String[] args) {
//      String str = "maximo renato huaman allccahuaman";
//      System.out.println(str);
//      StringBuffer strbf = new StringBuffer();
//      Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
//      while(match.find()) 
//      {
//         match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
//      }
//      System.out.println(match.appendTail(strbf).toString());
//  }
