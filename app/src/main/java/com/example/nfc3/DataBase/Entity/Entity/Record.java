package com.example.nfc3.DataBase.Entity.Entity;
import androidx.room.PrimaryKey;

import javax.persistence.*;

@Entity
@Table(name = "record")
public class Record {

@Id
@Column(name ="id")
@PrimaryKey(autoGenerate = true)
private  int Id;
@Column(name = "shortFireIdentifier", unique = true)
private String shortFireIdentifier;
@Column(name = "index", unique = true)
private String index;
@Column(name = "value", unique = true)
private String value;


    public int getId() {
        return Id;
    }

    public String getShortFireIdentifier() {
        return shortFireIdentifier;
    }

    public String getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }


    public void setShortFireIdentifier(String shortFireIdentifier) {
        this.shortFireIdentifier = shortFireIdentifier;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
