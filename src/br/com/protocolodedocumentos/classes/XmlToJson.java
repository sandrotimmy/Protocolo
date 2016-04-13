package br.com.protocolodedocumentos.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class XmlToJson {

    public static void main(String[] args) throws JSONException, IOException {

        // Monta o objeto em uma string 
        File f = new File("C:/Users/Administrador/Documents/RepositorioGIT/ProtocoloDeDocumentos/ArquivoXML.xml");

        //Passa o Arquivo XML para String
        String xml = FileUtils.readFileToString(f);

        // Cria um JSONObject a partir do arquivo XML em string
        JSONObject protocoloJson = XML.toJSONObject(xml);
        //Identa
        String protocoloJsonIndent = protocoloJson.toString(4);
        System.out.println(protocoloJsonIndent);

        //Grava no arquivo
        try {
            try (FileWriter writeFile = new FileWriter("ArquivoEmJSON.json")) {
                writeFile.write(protocoloJsonIndent);
            }
        } catch (IOException e) {
        }
        //Imprime
//        System.out.println(protocoloJson);
    }
}
