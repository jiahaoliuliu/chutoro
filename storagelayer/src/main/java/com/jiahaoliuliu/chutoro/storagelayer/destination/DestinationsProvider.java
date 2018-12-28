package com.jiahaoliuliu.chutoro.storagelayer.destination;

import com.jiahaoliuliu.chutoro.entity.destination.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to provide a list of destinations and the groups
 * that it belongs
 */
public class DestinationsProvider {

    public DestinationsProvider() {
    }

    public List<PersistentDestinationGroup> providePersistentDestinationGroups() {
        List<PersistentDestinationGroup> persistentDestinationGroupArrayList = new ArrayList<>();

        // TeachMeNow
        persistentDestinationGroupArrayList.add(getPersistentDestinationGroupTeachMeNow());

        // Wikipedia
        persistentDestinationGroupArrayList.add(getPersistentDestinationGroupWikipedia());

        return persistentDestinationGroupArrayList;
    }

    private PersistentDestinationGroup getPersistentDestinationGroupTeachMeNow() {
        PersistentDestinationGroup persistentDestinationGroup =
                new PersistentDestinationGroup(1, "TeachMeNow", Category.EDUCATION.toString());

        PersistentDestination persistentDestination =
                new PersistentDestination(1, 1, "BlueSnap,London-GB", "Main");

        persistentDestinationGroup.persistentDestinations = new ArrayList<>();
        persistentDestinationGroup.persistentDestinations.add(persistentDestination);
        return persistentDestinationGroup;
    }

    private PersistentDestinationGroup getPersistentDestinationGroupWikipedia() {
        PersistentDestinationGroup persistentDestinationGroup =
                new PersistentDestinationGroup(2, "Wikipedia", Category.EDUCATION.toString());

        PersistentDestination persistentDestination =
                new PersistentDestination(2, 2, "Wikimedia8776009454 US", "Main");

        persistentDestinationGroup.persistentDestinations = new ArrayList<>();
        persistentDestinationGroup.persistentDestinations.add(persistentDestination);
        return persistentDestinationGroup;
    }
}
