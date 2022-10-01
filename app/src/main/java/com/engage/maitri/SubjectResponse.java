package com.engage.maitri;

import java.util.List;

public class SubjectResponse {
    String key,name,subject_type;
    int work_count;
    List<WorksBody> works;


    public SubjectResponse(String key, String name, String subject_type, int work_count, List<WorksBody> works) {
        this.key = key;
        this.name = name;
        this.subject_type = subject_type;
        this.work_count = work_count;
        this.works = works;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }

    public int getWork_count() {
        return work_count;
    }

    public void setWork_count(int work_count) {
        this.work_count = work_count;
    }

    public List<WorksBody> getWorks() {
        return works;
    }

    public void setWorks(List<WorksBody> works) {
        this.works = works;
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
