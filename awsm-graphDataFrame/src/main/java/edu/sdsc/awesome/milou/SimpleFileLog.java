package edu.sdsc.awesome.milou;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileLog {

	public SimpleFileLog() {
		// TODO Auto-generated constructor stub
		
		
		
	}
	public void updateFile(String fileName, String data) throws IOException{
		
		BufferedWriter bw = null;

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
		bw = new BufferedWriter(fw);
		bw.write(data);
		bw.close();
		
	}
public void updateFile(String fileName, Integer data) throws IOException{
		
		BufferedWriter bw = null;
		
		
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
		bw = new BufferedWriter(fw);
		//bw.write(content);
		bw.write(data.toString());
		bw.close();
		
	}
public void updateFile(String fileName, long data) throws IOException{
	
	BufferedWriter bw = null;
	
	
	File file = new File(fileName);
	if (!file.exists()) {
		file.createNewFile();
	}
	FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
	bw = new BufferedWriter(fw);
	//bw.write(content);
	bw.write(Long.toString(data));
	bw.close();
	
	
}

}
