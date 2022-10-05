package com.afeni.index20;

import com.afeni.index20.controllers.WindManager;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetWind(){
         String[] index20 = {"0,0°,Tramontana,-N-",
         "11.25,11°15,Aquilone,---",
         "22.5,22°5,Bora,NNE",
         "33.75,33°45,Bacio,---",
         "45,45°,Grecale,NE-",
         "56.25,56°15,Volturno,---",
         "67.5,67°5,Schiavo,ENE",
         "78.75,78°45,Garigliano,---",
         "90,90°,Levante,-E-",
         "101.25,101°15,Goro,---",
         "112.5,112°5,Solano,SSE",
         "123.75,123°45,Altano,---",
         "135,135,Scirocco,SE-",
         "146.25,146°15,Euro,---",
         "157.5,157°5,Africo,SSE",
         "168.75,168°45,Eolo,---",
         "180,180°,Ostro,-S-",
         "191.25,191°15,Garbino,---",
         "202.5,202°5,Gauro,SSO",
         "213.75,213°45,Favonio,---",
         "225,215,Libeccio,SO-",
         "236.25,236°15,Espero,---",
         "247.5,247°5,Etesia,OSO",
         "258.75,158°45,Gallico,---",
         "270,270°,Ponente,-W-",
         "281.25,281°15,Cecia,---",
         "292.5,292°5,Traversone,ONO",
         "303.75,303°45,Vespero,---",
         "315,315,Maestrale,NO-",
         "326.25,326°15,Cauro,---",
         "337.5,337°5,Zefiro,NNO",
         "348.75,348°45,Brunale,---"};
        WindManager wm = new WindManager(index20);
        wm.getWindByRotation(0);
    }
}
