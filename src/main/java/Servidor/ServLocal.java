/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Contenido.Anuncios;
import Contenido.Canciones;
import Servidor.Utils.Token;
import Contenido.Contenido;
import Servidor.Utils.AdminToken;
import Servidor.Utils.ContentNotFoundException;
import Servidor.Utils.InvalidTokenException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author José Miguel
 */
public class ServLocal implements Servidor {

    private final String nombre;
    private final AdminToken admin = AdminToken.getInstance();
    private List<Contenido> catalogo = new ArrayList();
    private List<Contenido> publicidad = new ArrayList();
    private List<Token> validTokens = new ArrayList();

    public ServLocal(String nombre) {
        this.nombre = nombre;

    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public Token alta() {
        Token token = new Token();
        validTokens.add(token);
        return token;
    }

    @Override
    public void baja(Token token) throws InvalidTokenException {
        if (validTokens.contains(token)) {
            validTokens.remove(token);
        } else {
            throw new InvalidTokenException(token);
        }
    }

    @Override
    public void agregar(Contenido contenido, AdminToken token) throws InvalidTokenException {
        if (token.equals(admin)) {
            if(!(contenido instanceof Anuncios)){
                if(catalogo.contains(contenido))
                    catalogo.add(contenido);
            }
        } else {
            throw new InvalidTokenException(token);
        }

    }

    @Override
    public void eliminar(Contenido contenido, AdminToken token) throws InvalidTokenException, ContentNotFoundException {
        if (token.equals(admin)) {
            if (catalogo.contains(contenido)) {
                catalogo.remove(contenido);
            } else {
                throw new ContentNotFoundException(contenido);
            }
        } else {
            throw new InvalidTokenException(token);
        }

    }

    @Override
    public List<Contenido> buscar(String subcadena, Token token) throws InvalidTokenException {
        Random rnd = new Random();
        int counter = 3;
        List<Contenido> lista = new ArrayList();
        if (token == null) {
            if(publicidad.size()>0){
                    lista.add(publicidad.get((int)(rnd.nextDouble()*lista.size())));
            }
            for (Contenido contenido : catalogo) {
                if(counter == 0){
                    lista.add(publicidad.get((int)(rnd.nextDouble()*lista.size())));
                    counter = 3;
                }
                if (contenido.obtenerTitulo().contains(subcadena)) {
                    lista.add(contenido);
                    counter--;
                }
            }
        } else if (validTokens.contains(token)) {
            for (Contenido contenido : catalogo) {
                if (contenido.obtenerTitulo().contains(subcadena)) {
                    lista.add(contenido);
                }
            }
            token.use();
        } else {
            throw new InvalidTokenException(token);
        }
        return lista;
    }

}
