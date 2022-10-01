package com.engage.maitri;

import java.util.List;

public class WorksBody {
    String key,title;
    int edition_count;
    List<AuthorBody> authors;

    public WorksBody(String key, String title, int edition_count, List<AuthorBody> authors) {
        this.key = key;
        this.title = title;
        this.edition_count = edition_count;
        this.authors = authors;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition_count() {
        return edition_count;
    }

    public void setEdition_count(int edition_count) {
        this.edition_count = edition_count;
    }

    public List<AuthorBody> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorBody> authors) {
        this.authors = authors;
    }
}
//{
//        "key": "/subjects/love",
//        "name": "love"
//        "subject_type": "subject",
//        "work_count": 4918,
//        "works": [
//        {
//        "key": "/works/OL66534W",
//        "title": "Pride and prejudice",
//        "edition_count": 752,
//        "authors": [
//        {
//        "name": "Jane Austen",
//        "key": "/authors/OL21594A"
//        }
//        ],
//        "has_fulltext": true,
//        "ia": "mansfieldparknov03aust",
//        ...
//        },
//        ...
//        ]
//        }
