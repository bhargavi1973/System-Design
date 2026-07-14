//                                                                                     PHASE 3
// introduce modular design using DIP
class DocumentManager{
    private Storage storage;
    private Exporter exporter;
    private Printer printer;
    private SpellChecker spellChecker;

    // Constructor Injection
    public DocumentManager(Storage storage,
                           Exporter exporter,
                           Printer printer,
                           SpellChecker spellChecker) {

        this.storage = storage;
        this.exporter = exporter;
        this.printer = printer;
        this.spellChecker = spellChecker;
    }

    void saveDocument(Document doc){
        storage.save(doc);
    }
    Document loadDocument(String title){
        return storage.load(title);
    }

    void exportDocument(Document doc){
        exporter.export(doc);
    }

    void printDocument(Document doc){
        printer.print(doc);
    }
    void spellCheckDocument(Document doc){
        spellChecker.checkSpelling(doc);
    }

}
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

public class phasethree {
    public static void main(String[] args) {
        // Create dependencies
        Storage storage = new FileStorage();
        Exporter exporter = new PDFExporter();
        Printer printer = new Printer();
        SpellChecker spellChecker = new SpellChecker();

        // Inject dependencies
        DocumentManager manager =
                new DocumentManager(storage, exporter, printer, spellChecker);

        DocumentEditor editor = new DocumentEditor();

        Document doc = new Document();

        editor.createDocument(doc);
        editor.addText(doc, "\nHello SOLID Principles!");

        manager.saveDocument(doc);
        manager.exportDocument(doc);
        manager.printDocument(doc);
        manager.spellCheckDocument(doc);

        Document loadedDoc = manager.loadDocument("New Document");

        System.out.println("Loaded Document Title: " + loadedDoc.title);
    }
}

