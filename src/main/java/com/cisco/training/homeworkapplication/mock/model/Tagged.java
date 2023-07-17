package com.cisco.training.homeworkapplication.mock.model;

import java.util.Collection;

/**
 * Marker interface
 */
public interface Tagged {

    String getName();

    Collection<? extends Tagged> getSubTags();

}
