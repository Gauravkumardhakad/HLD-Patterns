import java.util.ArrayList;
import java.util.List;


// define the common interface
interface FileSystem{
    int getSize();
    void printStructure(String index);
    void delete();
}

// creating the leaf class - File
class File implements FileSystem{
    private final String name;
    private final int size;

    public File(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void printStructure(String indent){
        System.out.println(indent+ "- "+ name +"( "+size+" )" );
    }

    @Override
    public void delete(){
        System.out.println("Deleting file "+name);
    }
}

class Folder implements FileSystem{
    private final String name;
    private final List<FileSystem> children = new ArrayList<>();

    public Folder(String name){
        this.name = name;
    }

    public void addItem(FileSystem item){
        children.add(item);
    }

    @Override
    public int getSize(){
        int total = 0;
        for(FileSystem item: children){
            total+=item.getSize();
        }

        return total;
    }

    @Override
    public void printStructure(String indent){
        System.out.println(indent + "+ "+ "/");
        for(FileSystem item: children){
            item.printStructure(indent + " ");
        }
    }

    @Override
    public void delete(){
        for(FileSystem item: children){
            item.delete();
        }
        System.out.println("deleting folder "+ name);
    }
}

public class Demo {
    public static void main(String[] args) {
        FileSystem file1 = new File("readme.txt", 5);
        FileSystem file2 = new File("photo.jpg", 1500);
        FileSystem file3 = new File("data.csv", 300);

        Folder documents = new Folder("Documents");
        documents.addItem(file1);
        documents.addItem(file3);

        Folder pictures = new Folder("Pictures");
        pictures.addItem(file2);

        Folder home = new Folder("Home");
        home.addItem(documents);
        home.addItem(pictures);

        System.out.println("---- File Structure ----");
        home.printStructure("");

        System.out.println("\nTotal Size: " + home.getSize() + " KB");

        System.out.println("\n---- Deleting All ----");
        home.delete();
    }
}
