package at.ac.tuwien.ba.stac.client.search;

import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.core.Link;

import java.util.List;

public interface ItemCollection {

    String getType();

    List<Item> getItems();

    List<Link> getLinks();

}
