package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;

import play.data.validation.MaxSize;
import play.data.validation.Range;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="despesas")
public class Despesa extends Model {

    @Required
    @Temporal(TemporalType.DATE)
    public Date data;

    @Required
    @MaxSize(250)
    public String descricao;

    @Required
    @Range(min=-1000000, max=0)
    public BigDecimal valor;
    
    @Column(nullable=true)
    @ManyToMany
    public List<Tag> tags = new ArrayList<Tag>();

    
    public Despesa() {}

    public Despesa(Date data, String descricao, BigDecimal valor) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    public void setTags(String tags) {
        String[] nomesSeparadosPorVirgula = StringUtils.deleteWhitespace(tags).split(",");
        for (String nome : nomesSeparadosPorVirgula) {
            
            String nomeSemCaracteresEspeciais = nome.toLowerCase().replaceAll("[^a-z0-9]", "");
            
            Tag tag = Tag.find("byNome", nomeSemCaracteresEspeciais).first();
            if (tag == null) {
                tag = new Tag(nomeSemCaracteresEspeciais).save();
            }
            
            if (! this.tags.contains(tag)) {
                this.tags.add(tag);
            }
        }
    }
}
