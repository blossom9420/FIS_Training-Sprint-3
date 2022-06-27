package com.pluralsight.persistence.module05.repos;

import com.pluralsight.persistence.module05.model.Author;

import java.util.List;

public interface IAuthorRepo {
    public List<Author> getAll();
}
