package app.greeting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
	
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/greetingpv")
    public Greeting greetingpv(@RequestParam(value="name", defaultValue="World") String name) {
        
	    	System.out.println("*********** PV 0 ************");
	    
	    
	    	Map<String, String> env = System.getenv();
	        System.out.println("TOM'S SECRET:"+env.get("TOM_SECRET"));
	    
	        for (String envName : env.keySet()) {
                System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
	        }
			
		// RESTORE THIS	
	        //System.out.println("*********** PV 1 ************");
	    		    	
	    	//readWriteFile();
	 
	    	//System.out.println("*********** PV 2 ************");
    		
    	
    		return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    //https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
    public void readWriteFile () {

        // This will reference one line at a time
        String line = null;

        
        String lyrics = "I, I will be king And you, you will be queen Though nothing, will drive them away We can beat them, just for one day We can be heroes, just for one day And you, you can be mean And I, I'll drink all the time";
        
        
        String fileNameAndPath =  "/opt/app-root/src/data/temp.txt";
        
        //String fileNameAndPath =  "/data/temp.txt";
        
        File f = new File(fileNameAndPath);

        if (!f.exists()) {
        		System.out.println("Creating new file: "+ f);
        		try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        }
        else {
        		System.out.println(" FILE EXISTS ");
        }
        
        System.out.println("AbsolutePath is "+ f.getAbsolutePath());
        try {
            
        	// Write to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameAndPath));
            writer.write(lyrics);             
            writer.close();

        	
        	// FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileNameAndPath);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		fileNameAndPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileNameAndPath + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }    
    

}
