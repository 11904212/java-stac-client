package at.ac.tuwien.ba.stac.client;

import java.util.List;

public interface ItemCollection {

    String getType();

    List<Item> getItems();

    List<Link> getLinks();

}
