package servidor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import contenido.Anuncios;
import contenido.Contenido;
import utils.exceptions.tokens.InvalidTokenException;
import servidor.tokens.Token;
import java.util.List;

/**
 * Servidor que implementa un servidor de respaldo para las busquedas.
 *
 * @author Faustino Castro, Victor Blanco y José Miguel del Río
 */
public class ServRespaldo extends ServLocal {

  private Servidor respaldo;

  /**Java Doc.
   * Constructor.
   *
   * @param nombre
   */
  public ServRespaldo(String nombre) {
    super(nombre);
  }

  /**
   * Asigna servidor de respaldo.
   *
   * @param respaldo
   */
  public void setServRespaldo(Servidor respaldo) {
    this.respaldo = respaldo;
  }

  /**
   * Realiza una busqueda entre las canciones almacenadas en el servidor, si no
   * encuentra ninguna, realizará una búsqueda en el servidor de respaldo.
   *
   * @param subcadena
   * @param token
   * @return
   * @throws InvalidTokenException
   */
  @Override
  public List<Contenido> buscar(String subcadena, Token token) throws InvalidTokenException {
    List<Contenido> lista = super.buscar(subcadena, token);
    if (lista.isEmpty() || (lista.get(0) instanceof Anuncios && lista.size() == 1)) {
      respaldo.alta(token);
      lista = respaldo.buscar(subcadena, token);
      respaldo.baja(token);
    }
    return lista;
  }

}
