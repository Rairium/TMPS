package services;

import documents.CloneableDocument;

public class DocumentMaker {
    public CloneableDocument getClone(CloneableDocument documentSample){
        return documentSample.makeCopy();
    }
}
