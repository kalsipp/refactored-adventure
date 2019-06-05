package crawl;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import base.Helpers;
import base.InputHandler;
import base.Vector2;
import graphics.Camera;
import graphics.CameraTextureLess;
import graphics.Canvas;
import mapping.Map;
import mapping.MapLoader;

class Crawler
{
    public static void main( String[] args ) throws IOException
    {
        Canvas.initialize();
        Console console = System.console();
        final String baseFilename = "testfile.yeoldemappe";
        String baseMap = 
        		  "########\n"
        		+ "#......#\n"
        		+ "#......#\n"
        		+ "#...#..#\n"
				+ "#...#..#\n"
				+ "#...#..#\n"
				+ "########";
        Helpers.writeDataToFile(baseFilename, baseMap);
		Map activeMap = MapLoader.loadMapFromFile(baseFilename);
		Camera cam = new Camera();
		cam.setCameraPos(new Vector2(2.5,2.5));
		InputHandler.initialize();
		while(true)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			String[] coords = input.split(",");

        	if (coords[0].contentEquals("q"))
        	{
        		cam.rotate(-0.3);
        	}
        	else if (coords[0].contentEquals("e"))
        	{
        		cam.rotate(0.3);
        	}
        	else if(coords[0].contentEquals("w"))
        	{
        		cam.move(0.3);
        	}
        	else if(coords[0].contentEquals("s"))
        	{
        		cam.move(-0.3);
        	}

			cam.renderScreen(activeMap);
	    	Canvas.render();
		}
//		for(int i = 0; i < 20; i++)
//        {
//        	console.readLine();
//        	cam.setCameraDirection(new Vector3(i, 1, 0));
//        }
    }
}	