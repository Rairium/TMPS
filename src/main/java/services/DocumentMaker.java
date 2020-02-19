package services;

import documents.CloneableDocument;

public class DocumentMaker {
    public CloneableDocument getClonedDocument(CloneableDocument documentSample){
        return documentSample.makeCopy();
    }
}
