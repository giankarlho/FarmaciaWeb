package controller;

import dao.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Ubigeo;

@Named(value = "ubigeoC")
@SessionScoped
public class UbigeoC implements Serializable {

    private List<Ubigeo> listUbigeo;
    private UbigeoImpl dao;
    
    public UbigeoC() {
        dao = new UbigeoImpl();
    }
    
    public void listar() throws Exception{
        try {
            listUbigeo = dao.listarUbigeo();
        } catch (Exception e) {
            throw  e;
        }
    }

    public List<Ubigeo> getListUbigeo() {
        return listUbigeo;
    }

    public void setListUbigeo(List<Ubigeo> listUbigeo) {
        this.listUbigeo = listUbigeo;
    }
    
}
