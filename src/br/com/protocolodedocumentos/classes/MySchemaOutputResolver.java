package br.com.protocolodedocumentos.classes;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.stream.StreamResult;

public class MySchemaOutputResolver extends SchemaOutputResolver {

    @Override
    public StreamResult createOutput(String namespaceURI, String suggestedFileName) throws IOException {
        File file = new File("XMLSchema.xsd");
        StreamResult result = new StreamResult(file);
        result.setSystemId(file.toURI().toURL().toString());
        return result;
    }

}
