package org.example.codetohire.util;

public class languageIdUtil {
    public static int getLanguageId(String language){
        return  switch (language.toLowerCase()){
            case "java" -> 62;
            case "cpp" -> 54;
            case "python" -> 71;
            default ->  throw new RuntimeException("Language not supported");
        };
    }
}
