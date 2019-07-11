package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;


import base.Vector2;
import graphics.Canvas;
import graphics.Camera;
import mapping.Map;
import mapping.MapLoader;

class CameraTest
{

	void writeDataToFile(String filename, String data) throws IOException
	{
    	try(FileWriter fileWriter = new FileWriter(filename))
    	{
    		fileWriter.write(data);
    	}
	}

	@Test
	void shouldNeverRenderNullTile() throws Exception
	{
		{
			final String baseFilename = "cameraTestfile.yeoldemappe";
			String baseMap = "###\n"
					+ "#.#\n"
					+ "###";
			writeDataToFile(baseFilename, baseMap);
			Map activeMap = MapLoader.loadMapFromFile(baseFilename);
			Camera cam = new Camera();
			Canvas.initialize();
			cam.setCameraPos(new Vector2(1.5, 1.5));
			cam.setCameraDirection(new Vector2(-1, 0));
			cam.setCameraPlane(new Vector2(0, 0.5));
			cam.renderScreen(activeMap);
			assertFalse(cam.renderedNullTile);
		}
		{
			final String baseFilename = "cameraTestfile.yeoldemappe";
			String baseMap =
					  "##^#####\n"
					+ "#......#\n"
					+ "#......#\n"
					+ "#...#..#\n"
					+ "#...#..#\n"
					+ "#...#..#\n"
					+ "########";
			writeDataToFile(baseFilename, baseMap);
			Map activeMap = MapLoader.loadMapFromFile(baseFilename);
			Camera cam = new Camera();
			Canvas.initialize();
			cam.setCameraPos(new Vector2(1.5, 1.5));
			cam.setCameraDirection(new Vector2(-1, 0));
			cam.setCameraPlane(new Vector2(0, 0.5));
			cam.renderScreen(activeMap);
			assertFalse(cam.renderedNullTile);

			cam.rotate(90);
			cam.renderScreen(activeMap);
			assertFalse(cam.renderedNullTile);

			cam.rotate(90);
			cam.renderScreen(activeMap);
			assertFalse(cam.renderedNullTile);

			cam.rotate(90);
			cam.renderScreen(activeMap);
			assertFalse(cam.renderedNullTile);

		}
	}
	
}
