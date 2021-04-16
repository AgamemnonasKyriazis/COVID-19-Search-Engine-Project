package indexwriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class _IndexWriter {

	 File indexDir;
	 File dataDir;
	
	public _IndexWriter(String indexPath, String dataPath) {
		indexDir = new File(indexPath);
		dataDir = new File(dataPath);
	}

	public void writeIndex() throws IOException {
		Directory directory = FSDirectory.open(indexDir.toPath());
		
		IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(directory, writerConfig);
		
		File[] files = dataDir.listFiles();
		for(int i = 0; i < files.length; i++) {
			File f = files[i];
	        System.out.println("Indexing file:... " + f.getCanonicalPath());       
	        
	        Document doc = new Document();
	        doc.add(new StringField("filename", f.getCanonicalPath(), Field.Store.YES));
	        FileReader fileReader = new FileReader(f);
	        doc.add(new TextField("contents", fileReader));
	        Scanner scanner = new Scanner(f, "UTF-8");
	        doc.add(new StringField("title", scanner.nextLine(), Field.Store.YES));
	        doc.add(new StringField("author", scanner.nextLine(), Field.Store.YES));
	        scanner.close();
	        writer.addDocument(doc);
		}
		writer.close();
		directory.close();
	}
	
}
