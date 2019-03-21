package Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import Crawl.Canvas;

class CanvasTest {

	@Test
	void initialize_test() throws IOException 
	{
		Canvas.initialize();
	}

}
