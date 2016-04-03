package br.com.protocolodedocumentos.classes;


import java.io.File;
import java.io.IOException;
import javax.naming.spi.DirStateFactory.Result;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.stream.StreamResult;

public class MySchemaOutputResolver extends SchemaOutputResolver {

    @Override
    public StreamResult createOutput(String namespaceURI, String suggestedFileName) throws IOException {
        File file = new File("Teste.xsd");
        StreamResult result = new StreamResult(file);
        result.setSystemId(file.toURI().toURL().toString());
        return result;
    }

}