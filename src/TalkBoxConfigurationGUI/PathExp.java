package TalkBoxConfigurationGUI;

/*
 * This regard this class, just testing paths and streams in java
 * */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExp implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4427522009162255592L;

	public static void main(String[] args) throws IOException {
		
		Path path  = Paths.get("Sounds");
		System.out.println(path);
		Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
		System.out.println(realPath);
	}
}
