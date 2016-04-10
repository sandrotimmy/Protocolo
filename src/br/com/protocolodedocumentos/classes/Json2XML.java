package br.com.protocolodedocumentos.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class Json2XML {

    public static void main(String[] args) throws JSONException, IOException {

        // Monta o objeto em uma string 
        File f = new File("C:/Users/Sandro/Documents/RepositorioGIT/ProtocoloDeDocumentos/ArquivoXML.xml");
        String xml = FileUtils.readFileToString(f);

        // Cria um JSONObject a partir do arquivo XML 
        JSONObject protocoloJson = XML.toJSONObject(xml);
        
        //Grava no arquivo
        try {
            try (FileWriter writeFile = new FileWriter("saida.json")) {
                writeFile.write(protocoloJson.toString());
            }
        } catch (IOException e) {
        }
        //Imprime
        System.out.println(protocoloJson);
    }
}