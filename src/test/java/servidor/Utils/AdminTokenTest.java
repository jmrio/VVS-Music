/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.Utils;

import servidor.Tokens.AdminToken;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Faustino Castro, Victor Blanco y José Miguel del Río
 */
public class AdminTokenTest {
    
    /**
     *
     */
    public AdminTokenTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class AdminToken.
     * verificar que funciona el getInstance
     */
    @Test
    public void testGetInstance() {
        AdminToken admin = AdminToken.getInstance();

        assertFalse( admin == null);
      
    }

   


}