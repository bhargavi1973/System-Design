//                                                     PHASE 2

// Phase 1  violates Single Responsibility Principle (SRP) as it has multilpe responsibilities like creating, adding text, editing, saving, loading, printing, exporting, and spell checking


// In Phase 2, I have seperated those responsibiliies into different classes
// Now, each class is responsible for a single functionality, making code more maintainable, testable and easier to understand
// In Phase 2, I have also implemented OCP by creating Exporter and Storage interfaces. Why?
// These interaface allow us to easily add new storage and export formats without modifyiing existing code. We can simple create a new class that implements the interface


class Document{
    public String title;
    public String content;
    public String author;
    String getContent(){
        return content;
    }
    void setContent(String text){
        this.content = text;
    }
}
class DocumentEditor{
    void createDocument(Document doc){
        doc.title = "New Document";
        doc.author = "Author Name";
        doc.content = " content of document";
    }

    void addText(Document doc, String text){
        doc.content += text;
    }

    void deleteText(Document doc, int startIndex, int endIndex){
        doc.content = doc.content.substring(0, startIndex) + doc.content.substring(endIndex);
    }
}
interface Storage{
    void save(Document doc);
    Document load(String title);
}
class FileStorage implements Storage{
    @Override
    public void save(Document doc){
        System.out.println("saving document in doc file....");
    }

    @Override
    public Document load(String title){
        System.out.println("loading document: " + title);
        return new Document();
    }
}
class CloudStorage implements Storage{
    @Override
    public void save(Document doc){
        System.out.println("saving in cloud storage...");
    }
    @Override
    public Document load(String title){
        System.out.println("loading document: " + title);
        return new Document();
    }
}

// implementing a export interface
interface Exporter{
    void export(Document doc);
}
class PDFExporter implements Exporter{
    @Override
    public void export(Document doc){
        System.out.println("exporting document to PDF...");
    }
}
class HTMLExporter implements Exporter{
    @Override
    public void export(Document doc){
        System.out.println("exporting documnet as HTML markeup file...");
    }
}
class Printer{
    void print(Document doc){
        System.out.println("printing document.....");
    }
}

class SpellChecker{
    void checkSpelling(Document doc){
        System.out.println("chceking spelling in document...");
    }
}
public class phaseTwo {
    public static void main(String[] args){

    }
}

/// Disadvantage of Phase 2:

// It partially implements SRP and OCP.
// Dcument Editor clas is still responsible for craetimg, adding text and deleting text.
// Printer and Spellchecker classes are concrete classes and are not extensible
