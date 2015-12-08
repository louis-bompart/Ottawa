package mongodb.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Created by louis on 07/12/2015.
 */
public class SharedVariables {
    public static MongoCollection mongoCollection;
    public static FindIterable<Document> iterable;
}
