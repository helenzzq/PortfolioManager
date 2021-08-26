package strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
*  A strategy class for converting LinkedMap Collections to List
 *  Create for handling java.util.LinkedHashMap casting Exception
* */
public class CollectionCast {
    public static <T> List<T> collectionToObjectList(Collection<T> collection, Class<T> tClass) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(collection);
        ObjectMapper MAPPER = new ObjectMapper();
        return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, tClass));
    }

}
