package controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import models.Despesa;
import models.Tag;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Despesas extends Controller {

    public static void listar() {
        List<Despesa> despesas = Despesa.findAll();
        render(despesas);
    }
    
    public static void editar(Long id) {
        Despesa despesa = Despesa.findById(id);
        
        String tags = "";
        for (Tag tag : despesa.tags) {
            tags += tag.nome + ", ";
        }
        
        render(despesa, tags);
    }
    
    public static void deletar(Long id) {
        Despesa despesa = Despesa.findById(id);
        despesa.delete();
        listar();
    }
    
    public static void salvar(@Valid Despesa despesa) {
        String tags = params.get("tags");
        
        if (! validation.hasErrors()) {
            try {
                despesa.setTags(tags);
                despesa.save();
                flash.success("Despesa adicionada com sucesso");
                listar();
            }
            catch (Exception e) {
                flash.error(e.getMessage());
            }
        }
        renderTemplate("Despesas/cadastrar.html", despesa, tags);
    }
    
    public static void atualizar(@Valid Despesa despesa) {
        if (! validation.hasErrors()) {
            try {
                despesa.setTags(params.get("tags"));
                despesa.save();
                flash.success("Despesa atualizada com sucesso");
                listar();
            }
            catch (Exception e) {
                flash.error(e.getMessage());
            }
        }
        render("@editar", despesa);
    }
}