package com.github.taeminlee0601;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import GameData.Legends;
import GameData.LegendsInfo;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void isCharacterPathNotNull() throws URISyntaxException {
        assertTrue("Something", new LegendsInfo().getLegendsList().get(0)!= null);
    }
    @Test
    public void isCharactersPathsNotNUll() throws URISyntaxException {
        List<Legends> legends = new ArrayList<>(new LegendsInfo().getLegendsList());
        assertTrue("Something", legends.iterator()!= null);
    }

    public static void changeValue(int[] turn) {
        turn[0] = 2;
    }
}
