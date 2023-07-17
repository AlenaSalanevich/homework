package com.cisco.training.homeworkapplication.service;

import com.cisco.training.homeworkapplication.model.Content;

import java.util.Collection;

public interface ContentService {

    Collection<Content> getByTag(String tag);
}
