import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Ottawa
 * Created by Pixel on 23/11/2015.
 */
public class DataImporter {
    private MongoCollection<Document> mongoCollection;

    /**
     * @param target :  At index 0 : target db
     *               At index 1 : collection db
     * @param source : path to the JSON source file.
     */
    public DataImporter(String[] target, String source) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(target[0]);
        mongoCollection = mongoDatabase.getCollection(target[1]);
        try {
            readFile(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName: path to the JSON source file.
     * @throws IOException
     */
    private void readFile(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(this::importData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param string : Single JSON document to add.
     */
    private void importData(String string) {
        Document document = Document.parse(string);
        mongoCollection.insertOne(document);
    }
}
