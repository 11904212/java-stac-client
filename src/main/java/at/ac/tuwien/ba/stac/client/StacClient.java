package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.core.Catalog;
import at.ac.tuwien.ba.stac.client.core.Collection;
import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.search.ItemCollection;
import at.ac.tuwien.ba.stac.client.search.dto.QueryParameter;

import java.io.IOException;
import java.net.URISyntaxException;

public interface StacClient {

    Catalog getCatalog() throws IOException;

    Collection getCollection(String id) throws IOException;

    Item getItem(String collectionId, String itemId) throws IOException;

    ItemCollection search(QueryParameter parameter) throws IOException, URISyntaxException, InterruptedException;
}
