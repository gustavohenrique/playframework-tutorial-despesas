package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="tags")
public class Tag extends Model {

    @Required
    @Column(unique=true)
    @MaxSize(50)
    public String nome;
    
    public Tag() {}
    
    public Tag(String name) {
        this.nome = name;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}
