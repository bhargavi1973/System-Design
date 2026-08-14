import java.util.*;

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
    
    void setStorage(Storage storage){
        this.storage = storage;
    }
    void setExporter(Exporter exporter){
        this.exporter = exporter;
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
    private String title;
    private String content;
    private String author;
    List<DocumentObserver> observers = new ArrayList<>();
    

    Document(String title, String author){
        this.title = title;
        this.author = author;
        this.content = "";
    }
     // Register observer
    public void addObserver(DocumentObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    // Remove observer
    public void removeObserver(DocumentObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers
    private void notifyObservers() {
        for (DocumentObserver observer : observers) {
            observer.update(this);
        }
    }

    public void appendText(String text){
        if(text != null && !text.isBlank()) {
            content += text;
            // Document changed → notify observers
            notifyObservers();
        }
    }
    
    public void deleteText(int startIndex, int endIndex){

        if (startIndex >= 0 && endIndex <= content.length() && startIndex < endIndex) {
            content = content.substring(0, startIndex) + content.substring(endIndex);
            // Document changed → notify observers
            notifyObservers();
        }
        
    }
     
    // used by deleteText command before deleting 
    public String getText(int startIndex, int endIndex){
        return content.substring(startIndex, endIndex);
    }

    // used by DeleteTextCommand.undo()
    public void insertText(int index, String text){
        content = content.substring(0, index) + text + content.substring(index);
        // Document changed → notify observers
        notifyObservers();
    }
    
    // used by AddTextCommand.undo()
    public void removeLastCharacters(int len){
        if(len > 0 && len <= content.length()){
            content = content.substring(0, content.length() - len);
            notifyObservers();
        }
    }
    public String getTitle(){
        return title;
    }
    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}

class DocumentEditor{
    private Stack<Command> history = new Stack<>();
    void executeCommand(Command command){
        command.execute();
        history.push(command);
    }

    void undo(){
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        }
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
        return new Document(title, "Author Name");
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
        return new Document(title, "Author Name");
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
class MarkdownExporter implements Exporter{
    @Override
    public void export(Document doc){
        System.out.println("Exporting document as Markdown...");
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

// --- Factory Pattern Implementation ---
enum ExportType{
    PDF, 
    HTML,
    MARKDOWN
}

class ExporterFactory{
    public static Exporter createExporter(ExportType type){
        switch(type){
            case PDF:
                return new PDFExporter();
            case HTML:
                return new HTMLExporter();
            case MARKDOWN:
            return new MarkdownExporter();
            default:
                throw new IllegalArgumentException("Unknown export type: " + type);
        }
    }
}

enum StorageType{
    FILE,
    CLOUD
}
class StorageFactory{
    public static Storage createStorage(StorageType type){
        switch(type){
            case FILE:
                return new FileStorage();
            case CLOUD:
                return new CloudStorage();
            default:
                throw new IllegalArgumentException("Unknown storage type: " + type);
        }
    }
}

// --------- Command Pattern Implementation ----------
interface Command{
    void execute();
    void undo();
}

class AddTextCommand implements Command{
    private Document document;
    private String text;

    public AddTextCommand(Document document, String text){
        this.document = document;
        this.text = text;
    }

    @Override
    public void execute(){
        document.appendText(text);
    }

    @Override
    public void undo(){
        // Implementation for undoing the add text command
        document.removeLastCharacters(text.length());
    }
}

class DeleteTextCommand implements Command{
    private Document document;
    private int startIndex;
    private int endIndex;
    private String deletedText;

    public DeleteTextCommand(Document document, int startIndex, int endIndex){
        this.document = document;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void execute(){
        deletedText = document.getText(startIndex, endIndex);
        document.deleteText(startIndex, endIndex);
    }

    @Override
    public void undo(){
        // Implementation for undoing the delete text command
        document.insertText(startIndex, deletedText);
    }
}

// -----------observer pattern implementation -----------
interface DocumentObserver{
    void update(Document doc);
}
class AutoSaverObserver implements DocumentObserver{
    @Override
    public void update(Document doc){
        System.out.println("Auto-saving document: " + doc.getTitle());
    }
}
class WordCountObserver implements DocumentObserver{
    @Override
    public void update(Document doc){
        String content = doc.getContent();
        int wordCount = content.isBlank() ? 0 : content.trim().split("\\s+").length;
        System.out.println("Word count for document '" + doc.getTitle() + "': " + wordCount);
    }
}

public class observerPattern {
    public static void main(String[] args){
        
       Document doc = new Document("Notes", "Author Name");
       DocumentObserver autoSaver = new AutoSaverObserver();
       DocumentObserver wordCounter = new WordCountObserver();
       doc.addObserver(autoSaver);
       doc.addObserver(wordCounter);

       doc.appendText("hello world");


    }
}
